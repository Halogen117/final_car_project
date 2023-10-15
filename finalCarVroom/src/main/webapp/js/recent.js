/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


let userHistoryCarparks;
let userHistoryCarparksFormatted;
let carparkJson;
let allCarparkJson;
let table = $("#recentTable");
let userID = 1;






function getUserHistory(userID, duration) {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/getHistory';
        let params = 'userID=' + userID + '&duration=' + duration;
        xhr.open("GET", url + "?" + params, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log(xhr.response);
                userHistoryCarparks = JSON.parse(xhr.response);
                console.log(userHistoryCarparks);
                console.log("test");
                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send();
    });
}
function deleteUserHistory(historyID) {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/deleteHistory';
        let params = 'historyID=' + historyID;
        xhr.open("POST", url, true);
        let status = xhr.status;
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send(params);
    });
}
function deleteAllUserHistory(userID) {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/deleteAllHistory';
        let params = 'userID=' + userID;
        xhr.open("POST", url, true);
        let status = xhr.status;
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send(params);
    });
}

function dateFormatter(date) {
    return moment(date).format('DD-MM-YYYY HH:mm:ss');
}

getUserHistory(userID, '7 days').then(function () {

    $(function () {
        table.bootstrapTable({
            data: userHistoryCarparks,
            columns: [{}, {}, {},
                {
                    field: 'operate',
                    title: '',
                    align: 'center',
                    valign: 'middle',
                    clickToSelect: false,
                    formatter: function (value, row, index) {
                        //return '<input name="elementname"  value="'+value+'"/>';
                        curID = row['history_ID'];
                        return `<div class="btn-group btn-group-toggle" data-toggle="buttons">
        <button type="button" class="btn btn-danger" onclick="deleteItem(${curID})">Delete</button><button class="btn btn-success" onclick="redirect('${row.carpark_ID}')">Go</button></div>`;

                    }
                }
            ]
        });




    });
});
function deleteItem(curID) {
    const id = [curID];
    deleteUserHistory(id);
    table.bootstrapTable('remove', {
        field: "history_ID",
        values: id,
    });
    alert("Successfully Deleted");
}
function redirect(carparkID){
    window.location.href='/finalCarVroom/maps.html?carparkID='+carparkID;
}
document.getElementById("deleteAllButton").onclick = function () {
    deleteAllUserHistory(userID);
    table.bootstrapTable('removeAll');
    alert("Successfully Deleted all history");
}

