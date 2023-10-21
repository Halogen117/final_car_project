/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



let d = 48;
let xValues = [];
let yValues = [];
let lineChartInst;

//Get carpark availability data from beta.data.gov.sg
function fetchCarparkAvailabilityData(datetime, carparkID) {
    return new Promise(function (resolve, reject) {

        const xhr = new XMLHttpRequest();
        let params = 'date_time=' + datetime;
        xhr.open("GET", "https://api.data.gov.sg/v1/transport/carpark-availability?" + params, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                if (JSON.parse(xhr.response).items.length !== 0) {
                    let carparkAvailabilityJson = JSON.parse(xhr.response).items[0].carpark_data;
                    let carparkJson = carparkAvailabilityJson.find(item => item.carpark_number === carparkID);
                    resolve(carparkJson);
                }else{
                    resolve();
                }


                
            } else {
                reject(status);
            }

            //console.log(jsonResponse.find(item => item.carpark_number === "PL27"));

        };
        xhr.send();
    });

}
function getCarparkAvailableByID(carparkID, carparkAvailabilityJson) {
    return carparkAvailabilityJson.find(item => item.carpark_number === carparkID);
}
function getTotalCarparkAvailableWithCarparkJSON(carparkJSON) {
    let totalCarparkAvailableLot = 0;
    for (let i = 0; i < carparkJSON.carpark_info.length; i++) {
        totalCarparkAvailableLot += parseInt(carparkJSON.carpark_info[i].lots_available);
    }
    return totalCarparkAvailableLot;
}
async function getPastData(pastHours, carparkID) {
    return new Promise(async function (resolve, reject) {
        xValues = [];
        yValues = [];
        let pastDate = moment().subtract(pastHours+1, 'hours');

        for (let i = 0; i <=pastHours; i++) {
            pastDate=pastDate.add(1,'hours');
            await fetchCarparkAvailabilityData(pastDate.format("YYYY-MM-DDTHH:mm:ss"), carparkID).then(function (carparkJSON) {
                if (carparkJSON === undefined) {
                    xValues.push(pastDate.format("YYYY-MM-DDTHH:mm:ss"));
                    yValues.push(0);
                } else {
                    xValues.push(moment(carparkJSON.update_datetime).format('ddd, HH:mm'));
                    yValues.push(getTotalCarparkAvailableWithCarparkJSON(carparkJSON));
                }

            }).catch(function (err) {
                reject(err);
            });

        }
        console.log('test');
        console.log(xValues);
        console.log(yValues);
        resolve();

    });

}



function plotGraph(xValues, yValues, carparkID) {
    const lineCtx = document.getElementById('lineGraph').getContext('2d');
    if (lineChartInst) {
        lineChartInst.destroy();
    }
    console.log(xValues);
    console.log(yValues);
    lineChartInst = new Chart(lineCtx, {
        type: 'line',
        data: {
            labels: xValues,
            datasets: [{
                    label: carparkID,
                    data: yValues,
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }]
        }, options: {
            responsive: true,
        }

    });
}
const carparkDropdown = document.getElementById('carparkDropdown');
const hoursDropdown = document.getElementById('hoursDropdown');

carparkDropdown.addEventListener('change', onBothDropdownChange);
hoursDropdown.addEventListener('change', onBothDropdownChange);

function onBothDropdownChange() {
    carparkDropdown.disabled = true;
    hoursDropdown.disabled = true;
    $(carparkDropdown).selectpicker('refresh');
    $(hoursDropdown).selectpicker('refresh');
    const loadingGraphAnimation = document.getElementById('loadingGraphAnimation');
    const lineGraph = document.getElementById('lineGraph');
    loadingGraphAnimation.classList.remove('d-none');
    lineGraph.classList.add('d-none');
    console.log(carparkDropdown.selectedIndex);
    if (hoursDropdown.value !== undefined && carparkDropdown.selectedIndex > 0) {

        let carparkID = carparkDropdown.options[carparkDropdown.selectedIndex].value;
        let hours = hoursDropdown.options[hoursDropdown.selectedIndex].value;
        getPastData(hours, carparkID).then(function () {
            loadingGraphAnimation.classList.add('d-none');
            lineGraph.classList.remove('d-none');
            plotGraph(xValues, yValues, carparkID);
            carparkDropdown.disabled = false;
            hoursDropdown.disabled = false;
            $(carparkDropdown).selectpicker('refresh');
            $(hoursDropdown).selectpicker('refresh');
        }).catch(function (err) {
            console.log(err);
        });
    }

}