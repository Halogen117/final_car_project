<%-- 
    Document   : reset-password
    Created on : 15 Oct 2023, 8:14:54 pm
    Author     : Halogen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Base64" %>
<%@page import="com.mycompany.finalcarvroom.Authentication" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Carpark Near U - Reset Password</title>

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
    <%
        // Acquire the base64 ID
        HttpSession user_session = request.getSession();

        // If not valid, put say "U suck and you can't login"
        Authentication auth = new Authentication();

        if (auth.decoder_link(request.getParameter("id"))) {
            user_session.setAttribute("authenticator", request.getParameter("id"));

    %>
    <body class="bg-gradient-light">

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-5 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">

                                <div class="col">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-2">Reset your password!</h1>
                                            <p class="mb-4">Reset your password below</p>
                                        </div>
                                        <form class="user" action="ResetPasswordServlet" method="Get" onsubmit="return checkValidation()">
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-user"
                                                       name="retypePass" aria-describedby="emailHelp" id="retypePass"
                                                       placeholder="Enter Password">
                                                <p></p>
                                                <div id="inputPwdValid" class="alert alert-danger px-4 py-3 mb-0 d-none">
                                                    <ul class="list-unstyled mb-0">
                                                        <li class="requirements leng">
                                                            <i class="fas fa-check text-success me-2"></i>
                                                            <i class="fas fa-times text-danger me-3"></i>
                                                            Must contain at least 8 character</li>
                                                        <li class="requirements big-letter">
                                                            <i class="fas fa-check text-success me-2"></i>
                                                            <i class="fas fa-times text-danger me-3"></i>
                                                            Must contain at least 1 upper and 1 lower letter.</li>
                                                        <li class="requirements num">
                                                            <i class="fas fa-check text-success me-2"></i>
                                                            <i class="fas fa-times text-danger me-3"></i>
                                                            Must contain at least 1 number.</li>
                                                        <li class="requirements special-char">
                                                            <i class="fas fa-check text-success me-2"></i>
                                                            <i class="fas fa-times text-danger me-3"></i>
                                                            Must contain at least 1 special character.</li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-user"
                                                       name="passAgain" aria-describedby="emailHelp" id="passAgain"
                                                       placeholder="Re-enter Password">
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
                                            <input type="submit" value="Reset Password" class="btn btn-primary btn-user btn-block">

                                        </form>
                                        <hr>
                                        <%                                    if (session.getAttribute("verify_reset") == "weak_pass") {
                                                user_session.setAttribute("verify_reset", "");
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
                                        } else if (session.getAttribute("verify_reset") == "same_pass") {
                                            user_session.setAttribute("verify_reset", "");
                                        %>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Please type a different password as your using the same password as before!
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                        } else if (session.getAttribute("verify_reset") == "not_same_password") {
                                            user_session.setAttribute("verify_reset", "");
                                        %>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                                <div>
                                                    Different Passwords! Please retype.
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                            }
                                        %>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

        <!-- Footer Container Start -->
        <footer class="footer fixed-bottom bg-success text-center text-light">
            <!-- Grid container -->
            <div class="container pt-4">
                <!-- Section: Social media -->
                <section class="mb-4">
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
        <%} else {
        %>

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-6 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">

                                <div class="col">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">INVALID LINK!</h1>
                                        </div>
                                        <hr>
                                        <div class="text-center">
                                            <a class="small" href="login.jsp">Return to Login Page</a>
                                        </div>
                                        <div class="text-center">
                                            <a class="small" href="register.jsp">Create an Account!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer Container Start -->
        <footer class="footer fixed-bottom bg-success text-center text-light">
            <!-- Grid container -->
            <div class="container pt-4">
                <!-- Section: Social media -->
                <section class="mb-4">
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
                Â© 2023 Carpark Near U. All rights reserved.
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
        <script src="<%= request.getContextPath()%>/js/resetPwd.js"></script>
        <%
            }%>
    </body>

</html>
