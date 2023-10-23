/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var currPwd = document.getElementById("currPassword"),
        newPwd = document.getElementById("newPassword"),
        repeatPwd = document.getElementById("repeatPassword"),
        newPwdAlert = document.getElementById("newPwdValid"),
        repeatPwdAlert = document.getElementById("repeatValid"),
        repeatValid = document.getElementById("repeatPwdValid"),
        repeatInvalid = document.getElementById("repeatPwdInvalid"),
        checkSameOldNew = document.getElementById("checkSameCurrentIv"),
        requirements = document.querySelectorAll(".requirements");
var lengthBoolean = false,
        numberBoolean = false,
        specialCharBoolean = false,
        capsLetterBoolean = false;
var length = document.querySelector(".leng"),
        number = document.querySelector(".num"),
        specialCheck = document.querySelector(".special-char"),
        capsLetter = document.querySelector(".big-letter"),
        specialChars = " !@#$%^&*()-_=+[{]}\\|;:'\",<.>/?`~",
        digits = "0123456789";
// Check for validation when clicking on button
function checkValidation() {
    if (lengthBoolean == true && numberBoolean == true
            && specialCharBoolean == true && capsLetterBoolean == true
            && newPwd.value == repeatPwd.value) {
        if (newPwd.value == currPwd.value) {
            checkSameOldNew.classList.remove("d-none");
            return false
        }
        console.log("Success!");
        return true;
    } else
    {
        if (newPwd.value == currPwd.value) {
            checkSameOldNew.classList.remove("d-none");
        } else
        {
            checkSameOldNew.classList.add("d-none");
        }
        console.log("Fail!");
        return false;
    }

}
document.addEventListener('DOMContentLoaded', function () {
    //window.onload = function () {}
    // Set all validation to wrong
    requirements.forEach((element) => element.classList.add("wrong"));

    newPwd.addEventListener("focus", () => {
        newPwdAlert.classList.remove("d-none");
        if (!newPwd.classList.contains("is-valid")) {
            newPwd.classList.add("is-invalid");
        }
        checkSameOldNew.classList.add("d-none");
        
    });
    newPwd.addEventListener("blur", () => {
        newPwdAlert.classList.add("d-none");
    });

    repeatPwd.addEventListener("focus", () => {
        repeatPwdAlert.classList.remove("d-none");
    });
    repeatPwd.addEventListener("blur", () => {
        repeatPwdAlert.classList.add("d-none");
    });

    newPwd.addEventListener('input', function () {
        let value = newPwd.value;

        // CLIENT SIDE VALIDATION OF PASSWORD
        // Check for Length
        if (value.length < 8) {
            lengthBoolean = false;
        } else if (value.length > 7) {
            lengthBoolean = true;
        }

        //Check for digits
        numberBoolean = false;
        for (let i = 0; i < value.length; i++) {
            for (let j = 0; j < digits.length; j++) {
                if (value[i] == digits[j]) {
                    numberBoolean = true;
                }
            }
        }

        // Check for special character
        specialCharBoolean = false;
        for (let i = 0; i < value.length; i++) {
            for (let j = 0; j < specialChars.length; j++) {
                if (value[i] == specialChars[j]) {
                    specialCharBoolean = true;
                }
            }
        }

        // Check for uppper case letters
        if (value.toLowerCase() == value) {
            capsLetterBoolean = false;
        } else {
            capsLetterBoolean = true;
        }
        // Check if all validations are fulfilled
        if (lengthBoolean == true && numberBoolean == true
                && specialCharBoolean == true && capsLetterBoolean == true) {
            newPwd.classList.remove("is-invalid");
            newPwd.classList.add("is-valid");

            requirements.forEach((element) => {
                element.classList.remove("wrong");
                element.classList.add("good");
            });
            newPwdAlert.classList.remove("alert-danger");
            newPwdAlert.classList.add("alert-success");
        }
        // If one or more failed, check and display 
        // the appropriate error message
        else {
            newPwd.classList.remove("is-valid");
            newPwd.classList.add("is-invalid");

            newPwdAlert.classList.add("alert-danger");
            newPwdAlert.classList.remove("alert-success");

            // Display validation for length
            if (lengthBoolean == false) {
                length.classList.add("wrong");
                length.classList.remove("good");
            } else {
                length.classList.add("good");
                length.classList.remove("wrong");
            }
            // Display validation for numbers
            if (numberBoolean == false) {
                number.classList.add("wrong");
                number.classList.remove("good");
            } else {
                number.classList.add("good");
                number.classList.remove("wrong");
            }

            // Display validation for special characters
            if (specialCharBoolean == false) {
                specialCheck.classList.add("wrong");
                specialCheck.classList.remove("good");
            } else {
                specialCheck.classList.add("good");
                specialCheck.classList.remove("wrong");
            }

            // Display validation for capital letters
            if (capsLetterBoolean == false) {
                capsLetter.classList.add("wrong");
                capsLetter.classList.remove("good");
            } else {
                capsLetter.classList.add("good");
                capsLetter.classList.remove("wrong");
            }
        }

        if (newPwd.value == repeatPwd.value && repeatPwd.value != "")
        {
            repeatValid.classList.remove("d-none");
            repeatInvalid.classList.add("d-none");

            repeatPwd.classList.add("is-valid");
            repeatPwd.classList.remove("is-invalid");
        } else {
            repeatValid.classList.add("d-none");
            repeatInvalid.classList.remove("d-none");

            repeatPwd.classList.remove("is-valid");
            repeatPwd.classList.add("is-invalid");
        }
    });
    repeatPwd.addEventListener('input', function () {
        if (newPwd.value == repeatPwd.value)
        {
            repeatValid.classList.remove("d-none");
            repeatInvalid.classList.add("d-none");

            repeatPwd.classList.add("is-valid");
            repeatPwd.classList.remove("is-invalid");
        } else {
            repeatValid.classList.add("d-none");
            repeatInvalid.classList.remove("d-none");

            repeatPwd.classList.remove("is-valid");
            repeatPwd.classList.add("is-invalid");
        }
    });

});
