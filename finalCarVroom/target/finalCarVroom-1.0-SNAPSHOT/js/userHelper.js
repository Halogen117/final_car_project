/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let userID;

if ($.cookie("userId") === undefined || $.cookie("userId") === null) {
    window.location.href = '/finalCarVroom/login.jsp';
} else {
    userID = $.cookie("userId");

    getUser(userID).then(function (returnVal) {
        $("#username").html(returnVal.name);
        if ($('#welcomeUserText').length) {
            $("#welcomeUserText").html(returnVal.name);
        }
    }).catch(function (err) {
        console.log(err);
    });
}




function logout() {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/LogoutServlet';
        xhr.open("GET", url, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                $.removeCookie('userId');
                window.location.href = '/finalCarVroom/login.jsp';
                resolve();
            } else {
                reject(status);
            }
        }
        xhr.send();
    });
}
var logoutBtn = document.getElementById('logoutBtn');
logoutBtn.addEventListener('click', logout);
var profileBtn = document.getElementById('profileBtn');
profileBtn.addEventListener('click', getProfile);
var deleteBtn = document.getElementById('deleteBtn');
deleteBtn.addEventListener('click', getDeleteUser);
function getProfile() {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/ProfileServlet';
        xhr.open("GET", url, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send();
    });

}

function getDeleteUser() {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/DeleteProfileServlet';
        xhr.open("GET", url, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                resolve("it works");
            } else {
                reject(status);
            }
        }
        xhr.send();
    });

}
function getUser(userID) {
    return new Promise(function (resolve, reject) {
        const xhr = new XMLHttpRequest();
        let url = '/finalCarVroom/getUser';
        let params = 'userID=' + userID;
        xhr.open("GET", url + "?" + params, true);
        let status = xhr.status;
        xhr.onload = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let userJson = JSON.parse(xhr.responseText);
                resolve(userJson);
            } else {
                reject(status);
            }
        }
        xhr.send();
    });


}


