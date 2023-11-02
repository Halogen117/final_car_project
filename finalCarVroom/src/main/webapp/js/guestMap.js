/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//initialise SVY21 
//a free open source library taken from https://github.com/cgcai/SVY21#javascript
let cv = new SVY21();
let map;
let marker;
let draggableMarker;
let infoWindow;
let markersArray = [];
let carparkJson;
let allCarparkJson;
let carparkAvailabilityJson;
let offset = 500;
//For testing purposes, userID is set to 1 for now

let filteredData;
let redirectCarparkID;




//Function for when user clicks on Get Nearby carparks
async function findMyLocation() {
    getCurrentPosition().then(function (position) {
        let lat = position.coords.latitude;
        let lng = position.coords.longitude;

        initCarparks(lat, lng);


    });



}
;
let getCurrentPosition = function (options) {
    return new Promise(function (resolve, reject) {
        navigator.geolocation.getCurrentPosition(resolve, reject, options);
    });
}

function getCarparkRates(carpark) {
    let carparkRate;
    if (carpark.is_central) {
        let curDate = moment();
        let dayOfWeek = curDate.weekday();

        if (dayOfWeek !== 0 && (curDate.isAfter(moment('07:00', 'HH:mm')) && curDate.isBefore(moment('17:00', 'HH:mm')))) {
            carparkRate = "$1.20";
        } else {
            carparkRate = "$0.60";
        }


    } else {
        carparkRate = "$0.60 per half-hour";
    }
    return carparkRate;
}

//Initialise the map
async function initMap() {
    // Request libraries when needed, not in the script tag.
    const [{Map}, {AdvancedMarkerElement}] = await Promise.all([
        google.maps.importLibrary("marker"),
        google.maps.importLibrary("places")
    ]);

    // Short namespaces can be used.
    map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: 1.3521, lng: 103.8198},
        zoom: 8,
        mapId: '8a715e13e7d9ce06',
        mapTypeControl: false,
    });
    //Initialise Infowindow
    infoWindow = new google.maps.InfoWindow();

    //Checks if user has entered a carpark parameter in url
    if (redirectCarparkID === undefined || redirectCarparkID === null) {
        await findMyLocation();
    } else {
        getCarpark(redirectCarparkID).then((value) => {
            let carparkRate;
            clearCarparkCards();
            clearOverlays();
            var markerId = 0;
            let carpark = value;
            var resultLatLon = cv.computeLatLon(parseFloat(carpark.y_coord), parseFloat(carpark.x_coord));

            let totalCarparkAvailableLot = getTotalCarparkAvailable(carpark.carpark_id);
            let lastDate = moment(getCarparkLastUpdatedTime(carpark.carpark_id)).format('ddd, HH:mm:ss');
            initMarker(carpark, map);
            carparkRate = getCarparkRates(carpark);
            createCarparkCards(markerId, carpark, totalCarparkAvailableLot, lastDate, carparkRate);
            map.setZoom(18);
            map.panTo({lat: resultLatLon.lat, lng: resultLatLon.lon});


        });
    }


    draggableMarker = new google.maps.marker.AdvancedMarkerElement({
        map,
        position: {lat: 1.3521, lng: 103.8198},
        gmpDraggable: true,
        title: "Drag me",
    });
    draggableMarker.addListener("dragend", (event) => {
        const position = draggableMarker.position;
        let lat = position.lat;
        let lng = position.lng;
        initCarparks(lat, lng);


    });




    //Add a button to the map
    const locationButton = document.createElement("button");
    locationButton.textContent = "Jump to my location";
    locationButton.classList.add("custom-map-control-button");
    locationButton.classList.add("btn");
    locationButton.classList.add("btn-success");
    // Request needed libraries.
//@ts-ignore


    //Get the input element
    const input = document.getElementById("pac-input");
    
    //get the card element for the automcomplete search box
    const card = document.getElementById("pac-card");






    const options = {

        componentRestrictions: {country: "sg"},
        
    };
    const autocomplete = new google.maps.places.Autocomplete(input, options);
    autocomplete.bindTo("bounds", map);
    autocomplete.addListener("place_changed", () => {
        infoWindow.close();
        //marker.setVisible(false);

        const place = autocomplete.getPlace();

        if (!place.geometry || !place.geometry.location) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            createErrorAlert(`${place.name} is an invalid place!`,4000);
            
            return;
        } else {
            const lat = place.geometry.location.lat();
            const lng = place.geometry.location.lng();
            initCarparks(lat, lng);
        }






    });
    


    //card.appendChild(pac.element);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(card);



    // Add the gmp-placeselect listener, and display the results on the map.
    // This method happens after a user have clicked on a place on the search result list
//    pac.addListener("gmp-placeselect", async ({ place }) => {
//        await place.fetchFields({
//            fields: ["displayName", "formattedAddress", "location"],
//        });
//        let placeJson = place.toJSON();
//        let lat = placeJson.location.lat;
//        let lng = placeJson.location.lng;
//        initCarparks(lat, lng);
//        let content =
//                '<div id="infowindow-content">' +
//                '<span id="place-displayname" class="title">' +
//                place.displayName +
//                "</span><br />" +
//                '<span id="place-address">' +
//                place.formattedAddress +
//                "</span>" +
//                "</div>";
//        infoWindow.close();
//        infoWindow.setContent(content);
//        infoWindow.setPosition({lat: lat, lng: lng});
//        infoWindow.open({
//            map,
//            anchor: draggableMarker,
//            shouldFocus: false,
//        });
//
//    });

    map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);

    //Add a click listener to button ,look at findMylocation function
    locationButton.addEventListener("click", findMyLocation);

}
//If url has a parameter
const url = new URL(window.location.href);
if (url.searchParams.has("carparkID")) {
    redirectCarparkID = url.searchParams.get("carparkID");

}

function initCarparks(lat, lng) {
    let markerId = 0;
    let infoWindowContentString;
    let refreshBtn = document.getElementById("refreshBtn");
    draggableMarker.position = {lat: lat, lng: lng};
    clearOverlays();
    clearCarparkCards();
    fetchCarparkAvailabilityData().then(function () {


        // If the place has a geometry, then present it on a map.


        map.setCenter({lat: lat, lng: lng});
        if (map.getZoom() < 15) {
            map.setZoom(16);
        }



        //Convert The location lat and lon to SVY21 northing and easting (y and x values)
        let result = cv.computeSVY21(lat, lng);
        if (offset > 0) {
            filteredData = filterByLocation(allCarparkJson, result.E, result.N, offset);

            clearOverlays();
            clearCarparkCards();

            if (filteredData.length !== 0) {
                refreshBtn.disabled = false;
                for (const carpark of filteredData) {
                    let carparkRate;
                    let totalCarparkAvailableLot = getTotalCarparkAvailable(carpark.carpark_id);
                    let lastUpdatedDate = getCarparkLastUpdatedTime(carpark.carpark_id);
                    let lastUpdatedDateFormatted;
                    if (lastUpdatedDate === -1) {
                        lastUpdatedDateFormatted = -1;
                    } else {
                        lastUpdatedDateFormatted = moment(lastUpdatedDate).format('ddd, HH:mm:ss');
                    }
                    carparkRate = getCarparkRates(carpark);
                    initMarker(carpark, map);
                    createCarparkCards(markerId, carpark, totalCarparkAvailableLot, lastUpdatedDateFormatted, carparkRate);
                    markerId++;
                }
                //MarkerID also acts a counter for number of carparks
                infoWindowContentString = `Found ${markerId} nearby carparks`;
            } else {
                let carparkCardsRow = document.getElementById("carpark");
                carparkCardsRow.innerHTML = `<p class="h4 ml-4">No nearby carparks, choose a new location!</p>`;
                refreshBtn.disabled = true;
                infoWindowContentString = `No carparks nearby`;
            }



            infoWindow.close();
            infoWindow.setContent(infoWindowContentString);
            infoWindow.open(draggableMarker.map, draggableMarker);

        }



    }).catch(function (err) {
        console.log(err);
    });
}


//Getter methods

//getTotalCarparkAvailable adds up the different lot type of a specific carpark as given by carparkNo and returns the total available lots
function getTotalCarparkAvailable(carparkNo) {
    let totalCarparkAvailableLot = 0;

    if (carparkAvailabilityJson !== null || typeof carparkAvailabilityJson !== "undefined") {
        let carparkJson = carparkAvailabilityJson.find(item => item.carpark_number === carparkNo);
        if (carparkJson === undefined || carparkJson === null) {
            totalCarparkAvailableLot = -1;
        } else {
            for (let i = 0; i < carparkJson.carpark_info.length; i++) {
                totalCarparkAvailableLot += parseInt(carparkJson.carpark_info[i].lots_available);
            }
        }

    }

    return totalCarparkAvailableLot;
}

function getCarparkLastUpdatedTime(carparkNo) {
    let carparkJson = carparkAvailabilityJson.find(item => item.carpark_number === carparkNo);
    if (carparkJson === undefined || carparkJson === null) {
        return -1;
    }
    return carparkJson.update_datetime;
}




function getCarpark(carparkID) {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/getCarpark';
        let params = 'carparkID=' + carparkID;
        xhr.open("GET", url + "?" + params, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {

                let carpark = JSON.parse(xhr.response);
                resolve(carpark);
            } else {
                reject(status);
            }
        }
        xhr.send();
    });


}

//Get carpark availability data from beta.data.gov.sg
function fetchCarparkAvailabilityData() {
    return new Promise(function (resolve, reject) {

        const xhr = new XMLHttpRequest();

        xhr.open("GET", "https://api.data.gov.sg/v1/transport/carpark-availability", true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                carparkAvailabilityJson = JSON.parse(xhr.response).items[0].carpark_data;
                resolve("it works");
            } else {
                reject(status);
            }

            //console.log(jsonResponse.find(item => item.carpark_number === "PL27"));

        };
        xhr.send();
    });

}
//Open CSV file using jQuery
function getCarparkInformation() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "GET",
            url: "resources/HDBCarparkInformation.csv",
            dataType: "text",
            success: function (data) {
                carparkJson = csvToJSON(data);
                resolve("success get csv");
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}
function getAllCarparks() {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/getAllCarpark';
        xhr.open("GET", url, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                allCarparkJson = JSON.parse(xhr.response);

                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send();
    });


}
//---End of getter methods---









//Calls these two functions then initmap is called
fetchCarparkAvailabilityData().then(function () {
    return getAllCarparks();
    //Change the parameter value to userID retrieved from Httpsession

}).then(function () {
//call initmap to initalise the map
    initMap();
}).catch(function (err) {
    console.log(err);
});






// Helper function to create an info window.
function updateInfoWindow(content, center) {
    infoWindow.close();
    infoWindow.setContent(content);
    infoWindow.setPosition(center);
    infoWindow.open({
        map,
        anchor: marker,
        shouldFocus: false,
    });
}


//Filter the carpark CSV data by current location using an offset value
function filterByLocation(data, x, y, offset) {
    var filteredData = data.filter(function (a) {
        var xCoords = a.x_coord;
        var yCoords = a.y_coord;
        return xCoords >= x - offset && xCoords <= x + offset && yCoords >= y - offset && yCoords <= y + offset;
    });
    return filteredData;

}
;

//Initialise the markers on the map given the filtered data which is based on the user's current location
async function initMarker(carpark, map) {


    //import advancedmarkerelement and pinelement library
    const {AdvancedMarkerElement, PinElement} = await google.maps.importLibrary(
            "marker",
            );

    //Creating a custom icon for the marker
    const icon = document.createElement("div");
    icon.innerHTML = '<i class="fa-solid fa-car"></i>';
    const faPin = new PinElement({
        glyph: icon,
        glyphColor: "#ff0300",
        background: "#FFD514",
        borderColor: "#ff8300",

    });
    //Converting the xy coords to lat lon to be used to place the markers
    var resultLatLon = cv.computeLatLon(parseFloat(carpark.y_coord), parseFloat(carpark.x_coord));


    //Create the marker using advancedmarkerelement
    //passing in the map, the pinElement with the custom icon the position and the title(hover on the marker to display the title)
    const marker = new google.maps.marker.AdvancedMarkerElement({
        map,
        content: faPin.element,
        position: {lat: resultLatLon.lat, lng: resultLatLon.lon},
        title: carpark.address,
    });



    //add a click listener when user clicks on marker and display the info window
    marker.addListener("click", function () {

        let carparkRate;
        //Creating content html for the infowindow which is when user clicks on the marker, a info window will popup
        let lotsAvailable = getTotalCarparkAvailable(carpark.carpark_id);
        let lastUpdatedDateTime = getCarparkLastUpdatedTime(carpark.carpark_id);

        lotsAvailable = `${lotsAvailable} (Last updated on : ${moment(lastUpdatedDateTime).format('ddd, HH:mm:ss')})`;
        if (lotsAvailable === -1 && lastUpdatedDateTime === -1) {
            lotsAvailable = 'No data';
        }
        if (carpark.is_central) {
            carparkRate = "$1.20 per half-hour (7:00am to 5:00pm, Mondays to Saturdays)\n$0.60 per half-hour (other hours)";
        } else {
            carparkRate = "$0.60 per half-hour";
        }
        const content = document.createElement("div");
        content.classList.add("carparkDetail");
        content.innerHTML = `
                <div id="content">
                    <div id="firstHeading" class="address h4 font-weight-bold">${carpark.address}</div>
                    <div class="d-flex flex-column" id="bodyContent">
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Free Parking : </p>
                            <p>${carpark.free_parking}</p>
                        </div>
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Lots Available : </p>
                            <p>${lotsAvailable}</p>
                        </div>
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Night Parking : </p>
                            <p>${carpark.night_parking}</p>
                        </div>
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Car park Rates : </p>
                            <p id='hourlyRate'>${carparkRate}</p>
                        </div>
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Type of Parking System : </p>
                            <p>${carpark.type_of_parking_system}</p>
                        </div>
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Short-term Parking : </p>
                            <p>${carpark.short_term_parking}</p>
                        </div>
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Car Park Type : </p>
                            <p>${carpark.car_park_type}</p>
                        </div>                                                                                                                        
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Car Park Decks : </p>
                            <p>${carpark.car_park_decks}</p>
                        </div>                                                                                                                        
                        <div class="d-flex justify-content-start">
                            <p class="mr-2">Gantry Height(m) : </p>
                            <p>${carpark.gantry_height}</p>
                        </div>                                                                                                                        
                                                                        
                    </div>
                </div>
        </div>
                `;
        infoWindow.close();
        infoWindow.setContent(content);
        infoWindow.open(marker.map, marker);
        document.getElementById(carpark.carpark_id).focus();

        if (map.getZoom() < 15) {
            map.setZoom(16);
        }



    });
    //Push markers to an array which can be used later to clear array
    markersArray.push(marker);




}
//Clear markers
function clearOverlays() {
    for (var i = 0; i < markersArray.length; i++) {
        markersArray[i].setMap(null);
    }
    markersArray = [];
}

//Clear carpark Cards
function clearCarparkCards() {
    document.getElementById("carpark").innerHTML = "";
}





//Create the carparkcards html and buttons
//Call this multiple times for multiple carparks
function createCarparkCards(id, carpark, _lotsAvailable, _lastUpdatedDatetime, carparkRate) {
    let lastUpdatedDatetime = _lastUpdatedDatetime;
    if (lastUpdatedDatetime === -1) {
        lastUpdatedDatetime = "No data";
    }
    let lotsAvailable = _lotsAvailable;
    if (lotsAvailable === -1) {
        lotsAvailable = "No data";
    }
    const carparkCard = document.createElement("div");
    carparkCard.classList.add("col-xl-3");
    carparkCard.classList.add("col-md-6");
    carparkCard.classList.add("mb-4");
    carparkCard.innerHTML = `<div tabindex="-1" id="${carpark.carpark_id}" class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-s font-weight-bold text-primary text-uppercase mb-1">${carpark.address}</div>
                                                <div id="lots_${carpark.carpark_id}" class="h6 mb-0 font-weight-bold text-gray-800">Lots Available: ${lotsAvailable} </div>
                                                <div id="lastUpdated_${carpark.carpark_id}" class="h6 mb-0 font-weight-bold text-gray-800">Last Updated: ${lastUpdatedDatetime} </div>
                                                <div id="rates_${carpark.carpark_id}" class="h6 mb-0 font-weight-bold text-gray-800">Rates (per half-hour) : ${carparkRate} </div>
                                            </div>
                                            <div class="btn-group">
                                                <button id="btn_${carpark.carpark_id}" class="btn btn-success">Go</button>
                                                 
                                            </div>
                                        </div>
                                    </div>
                                </div>`;

    document.getElementById("carpark").appendChild(carparkCard);
    document.getElementById('btn_' + carpark.carpark_id).onclick = function () {

        google.maps.event.trigger(markersArray[id], 'click');
        if (map.getZoom() < 16) {
            map.setZoom(16);
        }
    };



    //if there exists a carpark it will be more than or equals to 0
    //hence we can use this logic to manipulate the favourite button 


}


document.getElementById("refreshBtn").onclick = function () {
    infoWindow.close();
    let refreshBtn = document.getElementById("refreshBtn");
    let refreshIcon = document.getElementById("refreshIcon");
    refreshBtn.disabled = true;
    refreshIcon.setAttribute("class", "fa-spin btnIcon fa-solid fa-rotate-right");
    fetchCarparkAvailabilityData().then(function () {
        if (filteredData !== null || typeof filteredData !== "undefined") {
            for (const carpark of filteredData) {

                let totalLotsAvailable = getTotalCarparkAvailable(carpark.carpark_id);
                let lastDate = moment(getCarparkLastUpdatedTime(carpark.carpark_id)).format('ddd, HH:mm:ss');
                document.getElementById("lots_" + carpark.carpark_id).textContent = "Lots Available: " + totalLotsAvailable;
                document.getElementById("lastUpdated_" + carpark.carpark_id).textContent = "Last Updated: " + lastDate;
            }
        }
    }).then(function () {
        refreshBtn.disabled = false;
        refreshIcon.setAttribute("class", "btnIcon fa-solid fa-rotate-right");
    }).catch(function (err) {
        console.log(err);
    });

};



//Functions for creating Alert Message by appending to id="alertsContainer"
function createSuccessAlert(alertMessage) {
    let alert = document.createElement("div");
    alert.classList.add('alert', 'alert-success', 'alert-dismissible', 'fade', 'in', 'out', 'd-flex', 'align-items-center');
    alert.setAttribute('style', 'border-radius : 20px');
    alert.setAttribute('id', 'favouriteAlertSuccess');
    alert.setAttribute('role', 'alert');
    alert.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="green" class="bi bi-check-circle" viewBox="0 0 16 16" style="margin-right: 10px;">
                                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
                                        </svg>
                                        <div>
                                            ${alertMessage}
                                        </div>
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>`;
    $(alert).appendTo("#alertsContainer");
    $(alert).addClass("show");
    setTimeout(function () {
        $(alert).removeClass('show');
        alert.remove();
    }, 2000);

}
function createErrorAlert(alertMessage) {
    let alert = document.createElement("div");
    alert.classList.add('alert', 'alert-danger', 'd-flex', 'align-items-center', 'alert-dismissible', 'fade', 'in', 'out');
    alert.setAttribute('style', 'border-radius : 20px');
    alert.setAttribute('id', 'favouriteAlertError');
    alert.setAttribute('role', 'alert');
    alert.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
            </svg>
            <div>
                ${alertMessage}
            </div>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>`;
    $(alert).appendTo("#alertsContainer");
    $(alert).addClass("show");
    setTimeout(function () {
        $(alert).removeClass('show');
        alert.remove();
    }, 2000);


}
//end of functions for creating alert messages