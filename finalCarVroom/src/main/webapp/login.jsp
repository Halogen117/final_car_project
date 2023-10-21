<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Carpark Near U- Login</title>

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



    </head>

    <body class="bg-gradient-light">

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
                                            <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                        </div>
                                        <form action="LoginServlet" class="user">
                                            <div class="alert alert-danger" role="alert" hidden>
                                                ${error}
                                            </div>
                                            <div class="form-group">
                                                <input type="email" class="form-control form-control-user"
                                                       id="exampleInputEmail" aria-describedby="emailHelp"
                                                       name="userEmail" placeholder="Enter Email Address..." required>
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-user"
                                                       name="userPassword" id="exampleInputPassword" placeholder="Password" required>
                                            </div>
                                            <div class="form-group">
                                                <div class="custom-control custom-checkbox small">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck">
                                                    <label class="custom-control-label" for="customCheck">Remember
                                                        Me</label>
                                                </div>
                                            </div>
                                            <input type="submit" value="Login" class="btn btn-primary btn-user btn-block">
                                            <hr>

                                        </form>
                                        <hr>
                                        <div class="text-center">
                                            <a class="small" href="forgot-password.jsp">Forgot Password?</a>
                                        </div>
                                        <div class="text-center">
                                            <a class="small" href="register.jsp">Create an Account!</a>
                                        </div>
                                        <div class="text-center">
                                            <a class="small" href="guestpage.jsp">Continue as Guest!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
            <%
                if (session.getAttribute("work") == "worked") {
            %>
            <p>Successful Registration!</p>
            <%
            } else if (session.getAttribute("work") == "failed") {
            %>
            <p>Failed Registration!</p>
            <%
            } else {

            %>
            <p></p>
            <%            }
            %>
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

    </body>

</html>