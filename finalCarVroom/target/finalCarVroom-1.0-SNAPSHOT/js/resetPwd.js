/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var inputPwd = document.getElementById("retypePass"),
        repeatPwd = document.getElementById("passAgain"),
        inputPwdAlert = document.getElementById("inputPwdValid"),
        repeatPwdAlert = document.getElementById("repeatValid"),
        repeatValid = document.getElementById("repeatPwdValid"),
        repeatInvalid = document.getElementById("repeatPwdInvalid"),
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
            && inputPwd.value == repeatPwd.value) {
        console.log("Success!");
        return true;
    } else
    {
        console.log("Fail!");
        window.scrollTo({top: 0, behavior: 'auto'});
        inputPwd.focus();
        return false;
    }

}
document.addEventListener('DOMContentLoaded', function () {
    //window.onload = function () {}
    // Set all validation to wrong
    requirements.forEach((element) => element.classList.add("wrong"));

    inputPwd.addEventListener("focus", () => {
        inputPwdAlert.classList.remove("d-none");
        if (!inputPwd.classList.contains("is-valid")) {
            inputPwd.classList.add("is-invalid");
        }

    });
    inputPwd.addEventListener("blur", () => {
        inputPwdAlert.classList.add("d-none");
    });

    repeatPwd.addEventListener("focus", () => {
        repeatPwdAlert.classList.remove("d-none");
    });
    repeatPwd.addEventListener("blur", () => {
        repeatPwdAlert.classList.add("d-none");
    });

    inputPwd.addEventListener('input', function () {
        let value = inputPwd.value;

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

        // Check for uppper and lower case letters
        if (value.toLowerCase() == value || value.toUpperCase() == value) {
            capsLetterBoolean = false;
        } else {
            capsLetterBoolean = true;
        }
        // Check if all validations are fulfilled
        if (lengthBoolean == true && numberBoolean == true
                && specialCharBoolean == true && capsLetterBoolean == true) {
            inputPwd.classList.remove("is-invalid");
            inputPwd.classList.add("is-valid");

            requirements.forEach((element) => {
                element.classList.remove("wrong");
                element.classList.add("good");
            });
            inputPwdAlert.classList.remove("alert-danger");
            inputPwdAlert.classList.add("alert-success");
        }
        // If one or more failed, check and display 
        // the appropriate error message
        else {
            inputPwd.classList.remove("is-valid");
            inputPwd.classList.add("is-invalid");

            inputPwdAlert.classList.add("alert-danger");
            inputPwdAlert.classList.remove("alert-success");

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

        if (inputPwd.value == repeatPwd.value && repeatPwd.value != "")
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
        if (inputPwd.value == repeatPwd.value)
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
