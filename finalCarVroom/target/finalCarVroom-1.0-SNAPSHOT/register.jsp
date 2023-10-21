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

</head>

<body class="bg-gradient-light" style="padding-bottom: 70px;">

    <div class="container">
        
        <div class="container d-flex justify-content-center align-items-center" style="min-height: 50vh; padding-bottom: 70px;">
            <div class="card o-hidden col-xl-7 border-0 shadow-lg my-5">

            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row justify-content-center">
                    <div class="col">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form class="user" action="RegisterServlet">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="exampleFirstName"
                                            name="firstName" placeholder="First Name" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control form-control-user" id="exampleLastName"
                                           name="lastName" placeholder="Last Name" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" id="exampleInputEmail"
                                        name="inputEmail" placeholder="Email Address" required>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            name="inputPassword" placeholder="Password" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            name="exampleRepeatPassword" placeholder="Repeat Password" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="tel" class="form-control form-control-user" id="phoneNumber"
                                        name="phoneNumber" placeholder="Phone Number" required>
                                </div>
                                <h5 class="h5 text-gray-900 mb-4">Security Question 1: What was the name of your first pet?</h5>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="ans1"
                                        name="ans1" placeholder="Security Question 1 Answer" required>
                                </div>
                                <h5 class="h5 text-gray-900 mb-4">Security Question 2: What is your favorite movie?</h5>
                                    <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="ans2"
                                        name="ans2" placeholder="Security Question 2 Answer" required>
                                </div>
                                    <h5 class="h5 text-gray-900 mb-4">Security Question 3: What is your lucky number?</h5>
                                    <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="ans3"
                                        name="ans3" placeholder="Security Question 3 Answer" required>
                                </div>
                                <input type="submit" value="Register Account" class="btn btn-primary btn-user btn-block">
                                <hr>
                          
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