/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let map;
async function initMap() {
    const [{Map}, {AdvancedMarkerElement}] = await Promise.all([
        google.maps.importLibrary("marker"),
        google.maps.importLibrary("places")
    ]);
    map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: 1.3521, lng: 103.8198},
        zoom: 8,
        mapId: '8a715e13e7d9ce06',
        mapTypeControl: false,
    });
}

initMap();
