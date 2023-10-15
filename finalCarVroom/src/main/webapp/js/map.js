/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//initialise SVY21 
//a free open source library taken from https://github.com/cgcai/SVY21#javascript
let cv = new SVY21();
let map;
let marker;
let infoWindow;
let markersArray = [];
let carparkJson;
let allCarparkJson;
let carparkAvailabilityJson;
let userFavouritedCarparks;
//For testing purposes, userID is set to 1 for now
let userID = 1;
let filteredData;

//Function for when user clicks on Get Nearby carparks
async function findMyLocation() {
    const success = async (position) => {
        var markerId = 0;

        //Offset value 
        const offsetTextBox = document.getElementById('offsetTextBox');
        var offset = parseInt(offsetTextBox.value);

        getUserFavouritedCarparks(userID).then(function () {
            const pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            //Convert The current location lat and lon to SVY21 northing and easting (y and x values)
            var result = cv.computeSVY21(pos.lat, pos.lng);

            //Focus the map to the current location
            map.panTo(pos);
            if (map.getZoom() < 16) {
                map.setZoom(16);
            }




            if (offset > 0) {
                filteredData = filterByLocation(allCarparkJson, result.E, result.N, offset);
                clearOverlays();
                clearCarparkCards();

                for (const carpark of filteredData) {
                    console.log("inside findmylocation");
                    let totalCarparkAvailableLot = getTotalCarparkAvailable(carpark.carpark_id);
                    let lastDate = moment(getCarparkLastUpdatedTime(carpark.carpark_id)).format('ddd, HH:mm:ss');
                    initMarker(carpark, map, totalCarparkAvailableLot);
                    createCarparkCards(markerId, carpark, totalCarparkAvailableLot, lastDate);
                    markerId++;


                }
            }


            //Gets the nearby carparks details 


            infoWindow.setPosition(pos);
            infoWindow.setContent("You are here");
            infoWindow.open(map);
        }).catch(function(err){
            console.log(err);
        });

        //Initialise Markers


    };
    const error = () => {
        status.textContent = "Unable to retrieve your location";
    };
    navigator.geolocation.getCurrentPosition(success, error);
}
;



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

    await findMyLocation();
    //Add a button to the map
    const locationButton = document.createElement("button");
    locationButton.textContent = "Get nearby car parks";
    locationButton.classList.add("custom-map-control-button");
    // Request needed libraries.
//@ts-ignore


    // Create the input HTML element, and add it to the map as a custom control.
    const input = document.createElement("input");

    input.id = "pac-input";
    input.placeholder = "Search for a place...";
    //@ts-ignore
    const pac = new google.maps.places.PlaceAutocompleteElement({
        inputElement: input,
        componentRestrictions: {country: ['sg']},
    });
    const card = document.getElementById("pac-card");


    card.appendChild(pac.element);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(card);
    // Create the marker and infowindow
    marker = new google.maps.marker.AdvancedMarkerElement({
        map,
    });

    // Add the gmp-placeselect listener, and display the results on the map.
    // This method happens after a user have clicked on a place on the search result list
    pac.addListener("gmp-placeselect", async ({ place }) => {
        await place.fetchFields({
            fields: ["displayName", "formattedAddress", "location"],
        });
        var markerId = 0;

        clearOverlays();
        clearCarparkCards();
        await getUserFavouritedCarparks(userID).then(function () {
            return fetchCarparkAvailabilityData();
        }).then(function () {
            const offsetTextBox = document.getElementById('offsetTextBox');
            var offset = parseInt(offsetTextBox.value);
            // If the place has a geometry, then present it on a map.
            if (place.viewport) {
                map.fitBounds(place.viewport);
            } else {
                map.setCenter(place.location);
                map.setZoom(17);
            }

            let content =
                    '<div id="infowindow-content">' +
                    '<span id="place-displayname" class="title">' +
                    place.displayName +
                    "</span><br />" +
                    '<span id="place-address">' +
                    place.formattedAddress +
                    "</span>" +
                    "</div>";

            updateInfoWindow(content, place.location);
            let placeJson = place.toJSON();
            //Convert The location lat and lon to SVY21 northing and easting (y and x values)
            var result = cv.computeSVY21(placeJson.location.lat, placeJson.location.lng);
            if (offset > 0) {
                filteredData = filterByLocation(allCarparkJson, result.E, result.N, offset);
                clearOverlays();
                clearCarparkCards();
                for (const carpark of filteredData) {
                    let totalCarparkAvailableLot = getTotalCarparkAvailable(carpark.carpark_id);
                    let lastDate = moment(getCarparkLastUpdatedTime(carpark.carpark_id)).format('ddd, HH:mm:ss');
                    initMarker(carpark, map, totalCarparkAvailableLot);
                    createCarparkCards(markerId, carpark, totalCarparkAvailableLot, lastDate);
                    markerId++;
                }

            }
            marker.position = place.location;
        }).catch(function(err){
            console.log(err);
        });


    });

    map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);

    //Add a click listener to button ,look at findMylocation function
    locationButton.addEventListener("click", findMyLocation);

}

//Getter methods

//getTotalCarparkAvailable adds up the different lot type of a specific carpark as given by carparkNo and returns the total available lots
function getTotalCarparkAvailable(carparkNo) {
    let totalCarparkAvailableLot = 0;
    if (carparkAvailabilityJson !== null || typeof carparkAvailabilityJson !== "undefined") {
        let carparkJson = carparkAvailabilityJson.find(item => item.carpark_number === carparkNo);
        for (let i = 0; i < carparkJson.carpark_info.length; i++) {
            totalCarparkAvailableLot += parseInt(carparkJson.carpark_info[i].lots_available);
        }
    }

    return totalCarparkAvailableLot;
}

function getCarparkLastUpdatedTime(carparkNo) {
    let carparkJson = carparkAvailabilityJson.find(item => item.carpark_number === carparkNo);
    return carparkJson.update_datetime;
}

function getUserFavouritedCarparks(userID) {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/getFavourite';
        let params = 'userID=' + userID;
        xhr.open("GET", url + "?" + params, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                userFavouritedCarparks = JSON.parse(xhr.response);
                resolve("it works");
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
                console.log(allCarparkJson);
                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send();
    });


}
//---End of getter methods---


//Insert userid and carparkid into database using xmlhttprequest
function insertFavDB(userID, carparkID) {
    const xhr = new XMLHttpRequest();
    let url = '/finalCarVroom/insertFavourite';
    let params = 'userID=' + userID + '&carparkID=' + carparkID;
    xhr.open('POST', url, true);

//Send the proper header information along with the request
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {//Call a function when the state changes.
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.send(params);
}
//Delete from favourite_db with userid and carparkid
function deleteFavDB(userID, carparkID) {
    const xhr = new XMLHttpRequest();
    let url = '/finalCarVroom/deleteFavourite';
    let params = 'userID=' + userID + '&carparkID=' + carparkID;
    xhr.open('POST', url, true);

//Send the proper header information along with the request
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {//Call a function when the state changes.
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.send(params);
}
function insertHistDB(userID, carparkID) {
    const xhr = new XMLHttpRequest();
    let url = '/finalCarVroom/insertHistory';
    let params = 'userID=' + userID + '&carparkID=' + carparkID;
    xhr.open('POST', url, true);

//Send the proper header information along with the request
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {//Call a function when the state changes.
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.send(params);
}





//Calls these two functions then initmap is called
fetchCarparkAvailabilityData().then(function () {
    return getAllCarparks();
    //Change the parameter value to userID retrieved from Httpsession

}).then(function () {
    return getUserFavouritedCarparks(userID);

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
async function initMarker(carpark, map, lotsAvailable) {


    //import advancedmarkerelement and pinelement library
    const {AdvancedMarkerElement, PinElement} = await google.maps.importLibrary(
            "marker",
            );

    //Clear the all the markers before placing new ones



    //Loop thru the filteredData and create a marker for each entry(carpark)



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
    console.log(resultLatLon);

    //Create the marker using advancedmarkerelement
    //passing in the map, the pinElement with the custom icon the position and the title(hover on the marker to display the title)
    const marker = new google.maps.marker.AdvancedMarkerElement({
        map,
        content: faPin.element,
        position: {lat: resultLatLon.lat, lng: resultLatLon.lon},
        title: carpark.address,
    });


    //Creating content html for the infowindow which is when user clicks on the marker, a info window will popup
    const content = document.createElement("div");
    content.classList.add("carparkDetail");
    content.innerHTML = `
    <div id="content">
        <div id="firstHeading" class="address">${carpark.address}</div>
        <div id="bodyContent">
            <div>Free Parking : ${carpark.free_parking}</div>
            <div>Lots Available : ${lotsAvailable}</div>
            <div>Night Parking : ${carpark.night_parking}</div>
            <div>Type of Parking System : ${carpark.type_of_parking_system}</div>
            <div>Short-term Parking : ${carpark.short_term_parking}</div>
            <div>Car Park Type : ${carpark.car_park_type}</div>
            <div>Car park decks	 : ${carpark.car_park_decks}</div>
            <div>Gantry Height(m) : ${carpark.gantry_height}</div>
            
        </div>
    </div>
    `;
    //add a click listener when user clicks on marker and display the info window
    marker.addListener("click", function () {
        console.log(marker);
        infoWindow.close();
        infoWindow.setContent(content);
        infoWindow.open(marker.map, marker);
        document.getElementById(carpark.carpark_id).focus();
        insertHistDB(userID, carpark.carpark_id);
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



//Function to convert CSV to JSON 
function csvToJSON(csvDataString) {
    const rowsHeader = csvDataString.split('\r').join('').split('\n')
    const headers = rowsHeader[0].split(',');
    const content = rowsHeader.filter((_, i) => i > 0);
    console.log('Headers: ', headers);
    const jsonFormatted = content.map(row => {
        const columns = row.split(',');
        return columns.reduce((p, c, i) => {
            p[headers[i]] = c;
            return p;
        }, {});
    });
    console.log('jsonFormatted:', jsonFormatted);
    // here you have the JSON formatted
    return jsonFormatted;
}

//Create the carparkcards html and buttons
//Call this multiple times for multiple carparks
function createCarparkCards(id, carpark, lotsAvailable, lastUpdatedDatetime) {
    const carparkCard = document.createElement("div");
    carparkCard.classList.add("col-xl-3");
    carparkCard.classList.add("col-md-6");
    carparkCard.classList.add("mb-4");
    carparkCard.innerHTML = `<div tabindex="-1" id="${carpark.carpark_id}" class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-s font-weight-bold text-primary text-uppercase mb-1">${carpark.address}</div>
                                                <div id="lots_${carpark.carpark_id}" class="h5 mb-0 font-weight-bold text-gray-800">Lots Available: ${lotsAvailable} </div>
                                                <div id="lastUpdated_${carpark.carpark_id}" class="h5 mb-0 font-weight-bold text-gray-800">Last Updated: ${lastUpdatedDatetime} </div>
                                            </div>
                                            <div class="btn-group">
                                                <button id="btn_${carpark.carpark_id}" class="btn btn-success">Go</button>
                                                 <button type="button" id="fav_${carpark.carpark_id}" class="btn btn-primary"><i class="fa-solid fa-heart"></i></button>
                                                 
                                            </div>
                                        </div>
                                    </div>
                                </div>`;

    document.getElementById("carpark").appendChild(carparkCard);
    document.getElementById('btn_' + carpark.carpark_id).onclick = function () {
        console.log(markersArray);
        google.maps.event.trigger(markersArray[id], 'click');
        map.setZoom(14);
    };
    var favButton = document.getElementById('fav_' + carpark.carpark_id);
    let foundCarpark = userFavouritedCarparks.indexOf(carpark.carpark_id);
    console.log(foundCarpark);
    //if there exists a carpark it will be more than or equals to 0
    //hence we can use this logic to manipulate the favourite button 
    if (foundCarpark >= 0) {
        favButton.style.color = "#ff0000";
    }
    favButton.addEventListener("click", function () {
        if (foundCarpark >= 0) {
            deleteFavDB(userID, carpark.carpark_id);
            favButton.style.color = "#ffffff";
            foundCarpark = -1;
            alert("Successfully unfavourited Carpark!");

        } else {
            insertFavDB(userID, carpark.carpark_id);
            foundCarpark = 1;
            favButton.style.color = "#ff0000";
            alert("Successfully Favourited Carpark!");
        }

    });

}


document.getElementById("refreshBtn").onclick = function () {
    fetchCarparkAvailabilityData();
    if (filteredData !== null || typeof filteredData !== "undefined") {
        for (const carpark of filteredData) {
            console.log(carpark.carpark_id);
            let totalLotsAvailable = getTotalCarparkAvailable(carpark.carpark_id);
            let lastDate = moment(getCarparkLastUpdatedTime(carpark.carpark_id)).format('ddd, HH:mm:ss');
            document.getElementById("lots_" + carpark.carpark_id).textContent = "Lots Available: " + totalLotsAvailable;
            document.getElementById("lastUpdated_" + carpark.carpark_id).textContent = "Last Updated: " + lastDate;
        }
    }
};


