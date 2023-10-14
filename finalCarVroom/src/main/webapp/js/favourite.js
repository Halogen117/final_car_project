/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
//global variables
let cv = new SVY21();
let userFavouritedCarparks;
let carparkJson;
let map;
let carparkAvailabilityJson;
let markersArray = [];
let userID = 1;
let infoWindow;

//Initialise the map
async function initMap() {
    let markerId = 0;
    const {Map} = await google.maps.importLibrary("maps");

    map = new Map(document.getElementById("map"), {
        center: {lat: 1.3521, lng: 103.8198},
        zoom: 11,
        mapId: '8a715e13e7d9ce06',
        mapTypeControl: false,
    });
    infoWindow = new google.maps.InfoWindow();
    clearOverlays();
    clearCarparkCards();
    for (let i = 0; i < userFavouritedCarparks.length; i++) {
        let filteredData = carparkJson.filter(function (a) {
            let carparkNo = a.car_park_no;
            return carparkNo === userFavouritedCarparks[i];
        });
        if (filteredData.length !== 0) {
            let carpark = filteredData[0];
            let totalCarparkAvailableLot = getTotalCarparkAvailable(carpark.car_park_no);
            let lastDate = moment(getCarparkLastUpdatedTime(carpark.car_park_no)).format('ddd, HH:mm:ss');
            initMarker(filteredData[0], map, totalCarparkAvailableLot);
            createCarparkCards(markerId, carpark, totalCarparkAvailableLot, lastDate);
            markerId++;

        }

    }

}
//Calls these two functions then initmap is called
fetchCarparkAvailabilityData().then(function () {
    return getCarparkInformation();
    //Change the parameter value to userID retrieved from Httpsession

}).then(function () {
    return getUserFavouritedCarparks(userID);
}).then(function () {
    initMap();
}).catch(function (err) {
    console.log(err);
});


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

//--using jquery to open carparkinformation csv file
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


//--Getter Methods--
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
                console.log(userFavouritedCarparks);
                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send();
    });


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




//--Delete methods--
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
        document.getElementById(carpark.car_park_no).focus();

    });
    //Push markers to an array which can be used later to clear array
    markersArray.push(marker);




}

//Create the carparkcards html and buttons
//Call this multiple times for multiple carparks
function createCarparkCards(id, carpark, lotsAvailable, lastUpdatedDatetime) {
    const carparkCard = document.createElement("div");
    carparkCard.classList.add("col-xl-3");
    carparkCard.classList.add("col-md-6");
    carparkCard.classList.add("mb-4");
    carparkCard.innerHTML = `<div tabindex="-1" id="${carpark.car_park_no}" class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-s font-weight-bold text-primary text-uppercase mb-1">${carpark.address}</div>
                                                <div id="lots_${carpark.car_park_no}" class="h5 mb-0 font-weight-bold text-gray-800">Lots Available: ${lotsAvailable} </div>
                                                <div id="lastUpdated_${carpark.car_park_no}" class="h5 mb-0 font-weight-bold text-gray-800">Last Updated: ${lastUpdatedDatetime} </div>
                                            </div>
                                            <div class="btn-group">
                                                <button id="btn_${carpark.car_park_no}" class="btn btn-success">Go</button>
                                                 <button type="button" id="fav_${carpark.car_park_no}" class="btn btn-primary"><i class="fa-solid fa-heart"></i></button>
                                                 
                                            </div>
                                        </div>
                                    </div>
                                </div>`;

    document.getElementById("carpark").appendChild(carparkCard);
    document.getElementById('btn_' + carpark.car_park_no).onclick = function () {
        console.log(markersArray);
        google.maps.event.trigger(markersArray[id], 'click');
    };
    var favButton = document.getElementById('fav_' + carpark.car_park_no);
    let foundCarpark = userFavouritedCarparks.indexOf(carpark.car_park_no);
    console.log(foundCarpark);
    //if there exists a carpark it will be more than or equals to 0
    //hence we can use this logic to manipulate the favourite button 
    if (foundCarpark >= 0) {
        favButton.style.color = "#ff0000";
    }
    favButton.addEventListener("click", function () {
        if (foundCarpark >= 0) {
            deleteFavDB(userID, carpark.car_park_no);
            favButton.style.color = "#ffffff";
            foundCarpark = -1;
            alert("Successfully unfavourited Carpark!");
            carparkCard.remove();
            markersArray[id].setMap(null);
            markersArray[id] = null;
        }

    });

}

document.getElementById("refreshBtn").onclick = function () {
    fetchCarparkAvailabilityData().then(function () {
        for (let i = 0; i < userFavouritedCarparks.length; i++) {
            let filteredData = carparkJson.filter(function (a) {
                let carparkNo = a.car_park_no;
                return carparkNo === userFavouritedCarparks[i];
            });
            if (filteredData.length !== 0) {
                let carpark = filteredData[0];
                console.log(carpark.car_park_no);
                let totalLotsAvailable = getTotalCarparkAvailable(carpark.car_park_no);
                let lastDate = moment(getCarparkLastUpdatedTime(carpark.car_park_no)).format('ddd, HH:mm:ss');
                document.getElementById("lots_" + carpark.car_park_no).textContent = "Lots Available: " + totalLotsAvailable;
                document.getElementById("lastUpdated_" + carpark.car_park_no).textContent = "Last Updated: " + lastDate;
            }
        }

    }).catch(function (err) {
        console.log(err);
    });





};