<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Carpark Near U - Register</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Link to social media logo -->
        <link rel="stylesheet" href=
              "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

        <style>
            .wrong .fa-check
            {
                display: none;
            }

            .good .fa-times
            {
                display: none;
            }
        </style>
    </head>

    <body class="bg-gradient-light" >

        <div class="container">

            <div class="container d-flex justify-content-center align-items-center" style="min-height: 50vh;">
                <div class="card o-hidden col-xl-7 border-0 shadow-lg my-5">

                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row justify-content-center">
                            <div class="col">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                                    </div>
                                    <form class="user" action="RegisterServlet" method="Get" onsubmit="return checkValidation()">
                                        <%
                                            HttpSession user_session = request.getSession();

                                            if (session.getAttribute("work") == "not_same_password") {
                                                user_session.setAttribute("work", "");
                                        %>

                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Same Password! Please retype.
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        } else if (session.getAttribute("work") == "first_name_blank") {
                                            user_session.setAttribute("work", "");
                                        %>                      
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    First Name should not be blank! Please Retype.
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        } else if (session.getAttribute("work") == "last_name_blank") {
                                            user_session.setAttribute("work", "");
                                        %>                              
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Last Name should not be blank! Please Retype.
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        } else if (session.getAttribute("work") == "pass_req_no") {
                                            user_session.setAttribute("work", "");
                                        %>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Password needs to have the following requirements:<br>
                                                    Minimum of 8 characters<br>
                                                    At least 1 Uppercase, lowercase, digit and special character
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        } else if (session.getAttribute("work") == "phone_no") {
                                            user_session.setAttribute("work", "");
                                        %>            
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Phone Number format is incorrect! <br>
                                                    Follow the example format: 62888888 or 9180 9182
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        }else if (session.getAttribute("work") == "same_email") {
                                            user_session.setAttribute("work", "");
                                        %>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Email already exists! Use another one!
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        }else if (session.getAttribute("work") == "email_exist") {
                                            user_session.setAttribute("work", "");
                                        %>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Email already exists! Use another one!
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        }else if (session.getAttribute("work") == "phone_exist") {
                                            user_session.setAttribute("work", "");
                                        %>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    This phone number already exists! Use another one!
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        }else if (session.getAttribute("work") == "unknown_error") {
                                            user_session.setAttribute("work", "");
                                        %>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    An Unknown Error has occurred. Please contact our staff for more enquiries!
                                                </div>
                                            </div>
                                        </div>

                                        <%
                                        } else {

                                        %>
                                        <%                                            }
                                        %>
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input type="text" class="form-control form-control-user" name="firstName"
                                                       placeholder="First Name" required>
                                            </div>
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control form-control-user" name="lastName"
                                                       placeholder="Last Name" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user" name="inputEmail"
                                                   placeholder="Email Address" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="type" class="form-control form-control-user" name="phoneNumber"
                                                   placeholder="Phone Number" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user" id="inputPassword"
                                                   name="inputPassword" placeholder="Password" required>
                                            <p></p>
                                            <div id="inputPwdValid" class="alert alert-danger px-4 py-3 mb-0 d-none">
                                                <ul class="list-unstyled mb-0">
                                                    <li class="requirements leng">
                                                        <i class="fas fa-check text-success me-2"></i>
                                                        <i class="fas fa-times text-danger me-3"></i>
                                                        Your password must have at least 8 character</li>
                                                    <li class="requirements big-letter">
                                                        <i class="fas fa-check text-success me-2"></i>
                                                        <i class="fas fa-times text-danger me-3"></i>
                                                        Your password must have at least 1 capital letter.</li>
                                                    <li class="requirements num">
                                                        <i class="fas fa-check text-success me-2"></i>
                                                        <i class="fas fa-times text-danger me-3"></i>
                                                        Your password must have at least 1 number.</li>
                                                    <li class="requirements special-char">
                                                        <i class="fas fa-check text-success me-2"></i>
                                                        <i class="fas fa-times text-danger me-3"></i>
                                                        Your password must have at least 1 special character.</li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user" id="repeatPassword"
                                                   name="repeatPassword" placeholder="Repeat Password" required>
                                            <div id="repeatValid" class="d-none">
                                                <p></p>
                                                <div id="repeatPwdValid" class="alert alert-success d-none" role="alert"> 
                                                    <i class="fas fa-check text-success me-2"></i> 
                                                    All's good!
                                                </div>
                                                <div id="repeatPwdInvalid" class="alert alert-danger d-none" role="alert"> 
                                                    <i class="fas fa-times text-danger me-3"></i>
                                                    The Passwords are NOT the SAME!
                                                </div>
                                            </div>
                                        </div>
                                        <h5 class="h5 text-gray-900 mb-4">Security Question 1: What was the name of your first pet?</h5>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user" name="ans1"
                                                   placeholder="Security Question 1 Answer" required>
                                        </div>
                                        <h5 class="h5 text-gray-900 mb-4">Security Question 2: What is your favorite movie?</h5>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user" name="ans2"
                                                   placeholder="Security Question 2 Answer" required>
                                        </div>
                                        <h5 class="h5 text-gray-900 mb-4">Security Question 3: What is your lucky number?</h5>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user" name="ans3"
                                                   placeholder="Security Question 3 Answer" required>
                                        </div>
                                        <input type="submit" value="Register Account" class="btn btn-primary btn-user btn-block">



                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.jsp">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="login.jsp">Already have an account? Login!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer Container Start -->
        <footer class="footer bg-success text-center text-light">
            <!-- Grid container -->
            <div class="container pt-4">
                <!-- Section: Social media -->
                <section class="sm-4">
                    <!-- Social media icons -->
                    <a class="btn btn-link btn-floating btn-lg text-dark m-1" href="#!" role="button" data-mdb-ripple-color="dark">
                        <i class="fa fa-facebook-f"></i>
                    </a>
                    <a class="btn btn-link btn-floating btn-lg text-dark m-1" href="#!" role="button" data-mdb-ripple-color="dark">
                        <i class="fa fa-twitter"></i>
                    </a>
                    <a class="btn btn-link btn-floating btn-lg text-dark m-1" href="#!" role="button" data-mdb-ripple-color="dark">
                        <i class="fa fa-google"></i>
                    </a>
                    <a class="btn btn-link btn-floating btn-lg text-dark m-1" href="#!" role="button" data-mdb-ripple-color="dark">
                        <i class="fa fa-instagram"></i>
                    </a>
                    <a class="btn btn-link btn-floating btn-lg text-dark m-1" href="#!" role="button" data-mdb-ripple-color="dark">
                        <i class="fa fa-linkedin"></i>
                    </a>
                    <a class="btn btn-link btn-floating btn-lg text-dark m-1" href="#!" role="button" data-mdb-ripple-color="dark">
                        <i class="fa fa-github"></i>
                    </a>
                </section>
                <!-- Section: Social media -->
            </div>
            <!-- Grid container -->

            <!-- Copyright -->
            <div class="text-center p-3">
                © 2023 Carpark Near U. All rights reserved.
            </div>
            <!-- Copyright -->
        </footer>
        <!-- Footer Container End -->
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <script src="<%= request.getContextPath()%>/js/register.js"></script>
    </body>

</html>