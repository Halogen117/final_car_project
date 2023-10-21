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
let userFavouritedCarparks;
//For testing purposes, userID is set to 1 for now
let userID = $.cookie("userId");
let filteredData;
let redirectCarparkID;
let offset = 500;
let xValues = [];
let yValues = [];
let barColors;
var i = 0;




//Function for when user clicks on Get Nearby carparks
async function findMyLocation() {
    navigator.permissions.query({name: 'geolocation'}).then((permissionStatus) => {

        if (permissionStatus.state === "granted") {
            getCurrentPosition().then(function (position) {
                let lat = position.coords.latitude;
                let lng = position.coords.longitude;
                initCarparks(lat, lng);
            });
        } else if (permissionStatus.state === "prompt") {
            createErrorAlert('Please enable location!', 4000);
        } else if (permissionStatus.state === "denied") {
            createErrorAlert('Please enable location!', 4000);
        }
        permissionStatus.addEventListener("change", () => {
            if (permissionStatus.state === "granted") {
                getCurrentPosition().then(function (position) {
                    let lat = position.coords.latitude;
                    let lng = position.coords.longitude;
                    initCarparks(lat, lng);
                });
            } else {
                createErrorAlert('Please enable location!', 4000);
            }
        });
    });




}
;
let getCurrentPosition = function (options) {
    return new Promise(function (resolve, reject) {
        navigator.geolocation.getCurrentPosition(resolve, reject, options);
    });
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
        mapTypeControl: true,
        mapTypeControlOptions: {position: google.maps.ControlPosition.BOTTOM_LEFT},
        streetViewControl: false,
    });
    //Initialise Infowindow
    infoWindow = new google.maps.InfoWindow();

    //Checks if user has entered a carpark parameter in url
    if (redirectCarparkID === undefined || redirectCarparkID === null) {
        await findMyLocation();
    } else {
        getCarpark(redirectCarparkID).then((value) => {
            xValues = [];
            yValues = [];
            clearCarparkCards();
            clearOverlays();
            var markerId = 0;
            let carpark = value;
            var resultLatLon = cv.computeLatLon(parseFloat(carpark.y_coord), parseFloat(carpark.x_coord));

            let totalCarparkAvailableLot = getTotalCarparkAvailable(carpark.carpark_id);
            let lastDate = moment(getCarparkLastUpdatedTime(carpark.carpark_id)).format('ddd, HH:mm:ss');
            filteredData = [];
            filteredData.push(allCarparkJson.find(item => item.carpark_id === carpark.carpark_id));
            populateCarparkDropdown(filteredData);


            initMarker(carpark, map);
            createCarparkCards(markerId, carpark, totalCarparkAvailableLot, lastDate);
            xValues.push(carpark.carpark_id);
            yValues.push(totalCarparkAvailableLot);
            barColors = generateDynamicColors(xValues.length);
            updateChart();
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
    //This method is fired when user moves the draggable marker
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



    // Add the gmp-placeselect listener, and display the results on the map.
    // This method happens after a user have clicked on a place on the search result list
    pac.addListener("gmp-placeselect", async ({ place }) => {
        await place.fetchFields({
            fields: ["displayName", "formattedAddress", "location"],
        });
        let placeJson = place.toJSON();
        let lat = placeJson.location.lat;
        let lng = placeJson.location.lng;
        initCarparks(lat, lng);
        let content =
                '<div id="infowindow-content">' +
                '<span id="place-displayname" class="title">' +
                place.displayName +
                "</span><br />" +
                '<span id="place-address">' +
                place.formattedAddress +
                "</span>" +
                "</div>";
        infoWindow.close();
        infoWindow.setContent(content);
        infoWindow.setPosition({lat: lat, lng: lng});
        infoWindow.open({
            map,
            anchor: draggableMarker,
            shouldFocus: false,
        });

    });

    map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);

    //Add a click listener to button ,look at findMylocation function
    locationButton.addEventListener("click", findMyLocation);

}
//If url has a parameter
const url = new URL(window.location.href);
if (url.searchParams.has("carparkID")) {
    redirectCarparkID = url.searchParams.get("carparkID");

}

//Gets the user favourited carparks and fetch new carpark's available lots
//Initialise the carpark cards and markers and charts
function initCarparks(lat, lng) {
    let markerId = 0;
    let infoWindowContentString;
    let refreshBtn = document.getElementById("refreshBtn");
    draggableMarker.position = {lat: lat, lng: lng};
    clearOverlays();
    clearCarparkCards();
    getUserFavouritedCarparks(userID).then(function () {
        return fetchCarparkAvailabilityData();
    }).then(function () {

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
                xValues = [];
                yValues = [];
                populateCarparkDropdown(filteredData);
                refreshBtn.disabled = false;
                for (const carpark of filteredData) {
                    let totalCarparkAvailableLot = getTotalCarparkAvailable(carpark.carpark_id);
                    let lastUpdatedDate = getCarparkLastUpdatedTime(carpark.carpark_id);
                    let lastUpdatedDateFormatted;
                    if (lastUpdatedDate === -1) {
                        lastUpdatedDateFormatted = -1;
                    } else {
                        lastUpdatedDateFormatted = moment(lastUpdatedDate).format('ddd, HH:mm:ss');
                    }
                    xValues.push(carpark.carpark_id);
                    yValues.push(totalCarparkAvailableLot);
                    initMarker(carpark, map);
                    createCarparkCards(markerId, carpark, totalCarparkAvailableLot, lastUpdatedDateFormatted);
                    markerId++;
                }
                barColors = generateDynamicColors(xValues.length);
                updateChart();
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


//Insert userid and carparkid into database using xmlhttprequest
function insertFavDB(userID, carparkID) {
    return new Promise(function (resolve, reject) {


        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/insertFavourite';
        let params = 'userID=' + userID + '&carparkID=' + carparkID;
        xhr.open('POST', url, true);

//Send the proper header information along with the request
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        xhr.onload = function () {//Call a function when the state changes.
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve("Successfully favourited Carpark!");
            } else {
                reject({status: xhr.status,
                    statusText: xhr.statusText});
            }
        };
        xhr.send(params);
    });
}
//Delete from favourite_db with userid and carparkid
function deleteFavDB(userID, carparkID) {
    return new Promise(function (resolve, reject) {


        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/deleteFavourite';
        let params = 'userID=' + userID + '&carparkID=' + carparkID;
        xhr.open('POST', url, true);

//Send the proper header information along with the request
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        xhr.onload = function () {//Call a function when the state changes.
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve("Successfully unfavourited carpark!");
            } else {
                reject({status: xhr.status,
                    statusText: xhr.statusText});
            }
        };
        xhr.send(params);
    });
}
function insertHistDB(userID, carparkID) {
    return new Promise(function (resolve, reject) {


        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/insertHistory';
        let params = 'userID=' + userID + '&carparkID=' + carparkID;
        xhr.open('POST', url, true);

//Send the proper header information along with the request
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        xhr.onload = function () {//Call a function when the state changes.
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve("Successfully inserted to History_db");
            } else {
                reject("Error in inserting to history_db");
            }
        };
        xhr.send(params);
    });
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
        title: carpark.carpark_id,

    });



    //add a click listener when user clicks on marker and display the info window
    marker.addListener("click", function () {


        //Creating content html for the infowindow which is when user clicks on the marker, a info window will popup
        let lotsAvailable = getTotalCarparkAvailable(carpark.carpark_id);
        let lastUpdatedDateTime = getCarparkLastUpdatedTime(carpark.carpark_id);

        lotsAvailable = `${lotsAvailable} (Last updated on : ${moment(lastUpdatedDateTime).format('ddd, HH:mm:ss')})`;
        if (lotsAvailable === -1 && lastUpdatedDateTime === -1) {
            lotsAvailable = 'No data';
        }
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
        infoWindow.close();
        infoWindow.setContent(content);
        infoWindow.open(marker.map, marker);
        document.getElementById(carpark.carpark_id).focus();
        insertHistDB(userID, carpark.carpark_id);
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
function createCarparkCards(id, carpark, _lotsAvailable, _lastUpdatedDatetime) {
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

        google.maps.event.trigger(markersArray[id], 'click');
        if (map.getZoom() < 16) {
            map.setZoom(16);
        }
    };
    var favButton = document.getElementById('fav_' + carpark.carpark_id);
    let foundCarpark = userFavouritedCarparks.indexOf(carpark.carpark_id);

    //if there exists a carpark it will be more than or equals to 0
    //hence we can use this logic to manipulate the favourite button 
    if (foundCarpark >= 0) {
        favButton.style.color = "#ff0000";
    }
    favButton.addEventListener("click", function () {
        if (foundCarpark >= 0) {
            deleteFavDB(userID, carpark.carpark_id).then(function (resolve) {
                favButton.style.color = "#ffffff";
                foundCarpark = -1;
                createSuccessAlert(resolve, 3000);
            }).catch(function (err) {
                createErrorAlert(err, 4000);
            });


        } else {
            insertFavDB(userID, carpark.carpark_id).then(function (resolve) {
                foundCarpark = 1;
                favButton.style.color = "#ff0000";
                createSuccessAlert(resolve, 3000);
            }).catch(function (err) {
                createErrorAlert(err, 4000);
            });

        }

    });

}
var barChartInst;
var pieChartInst;
function updateChart() {
    //removeCanvas('barChart');
    plotChart();
    plotPChart();
}
function plotChart() {

    var filteredData = [];
    for (var i = 0; i < xValues.length; i++) {
        if (yValues[i] >= 0) {
            filteredData.push({x: xValues[i], y: yValues[i]});
        }
    }

    // Sort the filtered data array by y values in descending order
    filteredData.sort((a, b) => b.y - a.y);

    xValues = filteredData.map(item => item.x);
    yValues = filteredData.map(item => item.y);

    var barCanvas = document.getElementById('barChart');

    // Destroy the existing chart instance if it exists
    if (barChartInst) {
        barChartInst.destroy();
    }
    var barCtx = barCanvas.getContext('2d');

    barChartInst = new Chart(barCtx, {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [{
                    label: 'Carparks Available Lots',
                    backgroundColor: barColors,
                    data: yValues
                }]
        },
        options: {
            onClick(event, clickedElements) {
                if (clickedElements.length !== 0) {
                    if (clickedElements[0]._model.label !== undefined) {
                        let carparkID = clickedElements[0]._model.label;
                        document.getElementById(carparkID).focus();
                        for (const marker of markersArray) {
                            if (marker.title === carparkID) {
                                google.maps.event.trigger(marker, 'click');
                                if (map.getZoom() < 16) {
                                    map.setZoom(16);
                                }
                            }
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true, // Start the y-axis scale at 0
                }
            },
            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {
                        var label = data.labels[tooltipItem.index];
                        var value = data.datasets[0].data[tooltipItem.index];
                        return label + ": " + value;
                    }
                }
            }, responsive: true
        }
    });
}

function plotPChart() {
    var pieCanvas = document.getElementById('pieChart');

    // Destroy the existing chart instance if it exists
    if (pieChartInst) {
        pieChartInst.destroy();
    }
    var pieCtx = pieCanvas.getContext('2d');
    pieChartInst = new Chart(pieCtx, {
        type: "pie",
        data: {
            labels: xValues,
            datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
        },
        options: {
            onClick(event, clickedElements) {

                if (clickedElements.length !== 0) {
                    if (clickedElements[0]._model.label !== undefined) {
                        let carparkID = clickedElements[0]._model.label;
                        document.getElementById(carparkID).focus();
                        for (const marker of markersArray) {
                            if (marker.title === carparkID) {
                                google.maps.event.trigger(marker, 'click');
                                if (map.getZoom() < 16) {
                                    map.setZoom(16);
                                }
                            }
                        }
                    }
                }
            },
            tooltips: {
                callbacks: {
                    label: function (tooltipItem, data) {
                        var label = data.labels[tooltipItem.index];
                        var value = data.datasets[0].data[tooltipItem.index];
                        return label + ": " + value;
                    }
                }
            }, responsive: true,
        }
    });
}
function generateDynamicColors(count) {
    var dynamicColors = [];
    for (var i = 0; i < count; i++) {
        // Generate a random color
        var color = "#" + ((1 << 24) * Math.random() | 0).toString(16);
        dynamicColors.push(color);
    }
    return dynamicColors;
}



//Refresh button
document.getElementById("refreshBtn").onclick = function () {
    infoWindow.close();
    let refreshBtn = document.getElementById("refreshBtn");
    let refreshIcon = document.getElementById("refreshIcon");
    refreshBtn.disabled = true;
    refreshIcon.setAttribute("class", "fa-spin btnIcon fa-solid fa-rotate-right");
    fetchCarparkAvailabilityData().then(function () {
        if (filteredData !== null || typeof filteredData !== "undefined") {

            xValues = [];
            yValues = [];
            for (const carpark of filteredData) {

                let totalLotsAvailable = getTotalCarparkAvailable(carpark.carpark_id);
                let lastDate = moment(getCarparkLastUpdatedTime(carpark.carpark_id)).format('ddd, HH:mm:ss');
                document.getElementById("lots_" + carpark.carpark_id).textContent = "Lots Available: " + totalLotsAvailable;
                document.getElementById("lastUpdated_" + carpark.carpark_id).textContent = "Last Updated: " + lastDate;
                xValues.push(carpark.carpark_id);
                yValues.push(totalLotsAvailable); ///////////////////////////////////////////////////////////////////////
                i++;
            }
            barColors = generateDynamicColors(xValues.length);
            updateChart();
        }
    }).then(function () {
        refreshBtn.disabled = false;
        refreshIcon.setAttribute("class", "btnIcon fa-solid fa-rotate-right");
    }).catch(function (err) {
        console.log(err);
    });

};



//Functions for creating Alert Message by appending to id="alertsContainer"
function createSuccessAlert(alertMessage, timeToFade) {
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
        setTimeout(function () {
            $(alert).remove();
        }, 2000);
    }, timeToFade);

}
function createErrorAlert(alertMessage, timeToFade) {
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
        setTimeout(function () {
            $(alert).remove();
        }, 2000);
    }, timeToFade);



}
//end of functions for creating alert messages



//lineGraph functions

const carparkDropdown = document.getElementById('carparkDropdown');

function populateCarparkDropdown(filteredData) {
    const lineGraph = document.getElementById('lineGraph');
    lineGraph.classList.add('d-none');
    $("#carparkDropdown").find('option').remove();
    for (const carpark of filteredData) {
        let option = document.createElement("option");
        option.setAttribute('value', carpark.carpark_id);

        let optionText = document.createTextNode(carpark.address);
        option.appendChild(optionText);

        carparkDropdown.appendChild(option);
    }
    $('#carparkDropdown').selectpicker('refresh');
}

