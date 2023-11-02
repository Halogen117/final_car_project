/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


let userHistoryCarparks;
let userHistoryCarparksFormatted;
let carparkJson;
let allCarparkJson;
let table = $("#recentTable");
let userID = $.cookie("userId");






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
                resolve("Successfully deleted history!");
            } else {
                reject({status: status, statusText: xhr.statusText});
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
                resolve("Successfully deleted all history records!");
            } else {
                reject({status: status, statusText: xhr.statusText});
            }
        };
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
    deleteUserHistory(id).then(function (successMessage) {
        table.bootstrapTable('remove', {
            field: "history_ID",
            values: id,
        });
        createSuccessAlert(successMessage,3000);
    }).catch(function (err) {
        createErrorAlert(err.statusText,4000);
    });

}
function redirect(carparkID) {
    window.location.href = '/finalCarVroom/index.html?carparkID=' + carparkID;
}
document.getElementById("deleteAllButton").onclick = function () {
    if (table.bootstrapTable('getData').length !== 0) {
        deleteAllUserHistory(userID).then(function (successMessage) {
            table.bootstrapTable('removeAll');
            createSuccessAlert(successMessage,3000);
        }).catch(function (err) {
            createErrorAlert(err,4000);
        });

    }

}

//Functions for creating Alert Message by appending to id="alertsContainer"
function createSuccessAlert(alertMessage,timeToFade) {
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
        setTimeout(function(){
           $(alert).remove(); 
        },2000);
    }, timeToFade);

}

function createErrorAlert(alertMessage,timeToFade) {
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
        setTimeout(function(){
           $(alert).remove(); 
        },2000);
    }, timeToFade);


}
//end of functions for creating alert messages