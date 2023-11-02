<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    } else if (request.getAttribute("CurrentUserData") == null) {
        response.sendRedirect("ProfileServlet");
    }

%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Carpark Near U - Profile</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!--Jquery and JqueryCookie reference-->
        <script src="js/jquery-3.7.1.min.js" type="text/javascript" ></script>
        <script src="js/jquery_cookie.js" type="text/javascript"></script>
        <!-- Link to social media logo -->
        <link rel="stylesheet" href=
              "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script type="module" src="js/userHelper.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-success sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-car-front" viewBox="0 0 16 16">
                        <path d="M4 9a1 1 0 1 1-2 0 1 1 0 0 1 2 0Zm10 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0ZM6 8a1 1 0 0 0 0 2h4a1 1 0 1 0 0-2H6ZM4.862 4.276 3.906 6.19a.51.51 0 0 0 .497.731c.91-.073 2.35-.17 3.597-.17 1.247 0 2.688.097 3.597.17a.51.51 0 0 0 .497-.731l-.956-1.913A.5.5 0 0 0 10.691 4H5.309a.5.5 0 0 0-.447.276Z"/>
                        <path d="M2.52 3.515A2.5 2.5 0 0 1 4.82 2h6.362c1 0 1.904.596 2.298 1.515l.792 1.848c.075.175.21.319.38.404.5.25.855.715.965 1.262l.335 1.679c.033.161.049.325.049.49v.413c0 .814-.39 1.543-1 1.997V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.338c-1.292.048-2.745.088-4 .088s-2.708-.04-4-.088V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.892c-.61-.454-1-1.183-1-1.997v-.413a2.5 2.5 0 0 1 .049-.49l.335-1.68c.11-.546.465-1.012.964-1.261a.807.807 0 0 0 .381-.404l.792-1.848ZM4.82 3a1.5 1.5 0 0 0-1.379.91l-.792 1.847a1.8 1.8 0 0 1-.853.904.807.807 0 0 0-.43.564L1.03 8.904a1.5 1.5 0 0 0-.03.294v.413c0 .796.62 1.448 1.408 1.484 1.555.07 3.786.155 5.592.155 1.806 0 4.037-.084 5.592-.155A1.479 1.479 0 0 0 15 9.611v-.413c0-.099-.01-.197-.03-.294l-.335-1.68a.807.807 0 0 0-.43-.563 1.807 1.807 0 0 1-.853-.904l-.792-1.848A1.5 1.5 0 0 0 11.18 3H4.82Z"/>
                        </svg>
                    </div>
                    <div class="sidebar-brand-text mx-3">Carpark Near U</div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item">
                    <a class="nav-link" href="index.html">
                        <i class="fas fa-fw fa-home"></i>
                        <span>Home</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Pages
                </div>


                <!-- Divider -->
                <hr class="sidebar-divider">





                <!-- Nav Item - Favourites -->
                <li class="nav-item">
                    <a class="nav-link" href="favourites.html">
                        <i class="fas fa-fw fa-heart"></i>
                        <span>Favourites</span></a>
                </li>

                <!-- Nav Item - History -->
                <li class="nav-item">
                    <a class="nav-link" href="recent.jsp">
                        <i class="fas fa-fw fa-history"></i>
                        <span>Recent</span></a>
                </li>


                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>


                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">

                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                            <li class="nav-item dropdown no-arrow d-sm-none">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                     aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto w-100 navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small"
                                                   placeholder="Search for..." aria-label="Search"
                                                   aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <!-- Nav Item - Alerts -->



                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%= session.getAttribute("username")%></span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" id="profileBtn">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->


                        <div class="d-flex align-items-center">
                            <img class="img-profile rounded-circle mb-3" style="width: 100px; height: 100px;" src="img/undraw_profile.svg">
                            <div class="ml-3 d-none d-lg-inline text-gray-600 small" style="font-size: xxx-large;">Hi <%= session.getAttribute("username")%></div>
                        </div>

                        <%
                            HttpSession user_session = request.getSession();

                            if (session.getAttribute("update_prof") == "success") {
                                user_session.setAttribute("update_prof", "");
                        %>
                        <div class="d-flex align-items-center justify-content-center">
                            <div class="alert alert-success d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="green" class="bi bi-check-circle" viewBox="0 0 16 16" style="margin-right: 10px;">
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
                                </svg>
                                <div>
                                    Profile successfully updated!
                                </div>
                            </div>
                        </div>
                        <%
                        } else if (session.getAttribute("update_pwd") == "success") {
                            user_session.removeAttribute("update_pwd");
                        %>
                        <div class="d-flex align-items-center justify-content-center">
                            <div class="alert alert-success d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="green" class="bi bi-check-circle" viewBox="0 0 16 16" style="margin-right: 10px;">
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
                                </svg>
                                <div>
                                    Password successfully updated!
                                </div>
                            </div>
                        </div>
                        <%
                        } else if (session.getAttribute("update_prof") == "user_fail") {
                            user_session.removeAttribute("update_prof");
                        %>
                        <div class="d-flex align-items-center justify-content-center">
                            <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                </svg>
                                <div>
                                    Account Deletion Failed!
                                </div>
                            </div>
                        </div>
                        <%}%>

                        <!-- Content Row -->
                        <div class="row">
                            <div class="col-xl-12 order-xl-1">

                                <div class="card bg-light shadow">
                                    <div class="card-header bg-white border-0">
                                        <div class="row align-items-center">
                                            <div class="col-8">
                                                <h3 class="mb-0">My Account</h3>
                                            </div>
                                            <div class="col-4 text-right">
                                                <form action="ProfileServlet">
                                                    <input type="submit" name="updatePassword" value="Change Password" 
                                                           class="btn btn-sm btn-primary" id="updatePassword"/>
                                                    <input type="submit" name="updateProfile" value="Manage Profile" 
                                                           class="btn btn-sm btn-primary" id="updateProfile"/>
                                                </form>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h6 class="heading-small text-muted mb-4">User information</h6>
                                        <div class="pl-lg-4">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group focused">
                                                        <label class="form-control-label" for="input-username">Username</label>
                                                        <input type="text" id="input-username" class="form-control form-control-alternative" 
                                                               value='<%= session.getAttribute("username")%>' disabled>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label" for="input-email">Email Address</label>
                                                        <input type="email" id="input-email" class="form-control form-control-alternative" 
                                                               value='${CurrentUserData.email}' disabled>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group focused">
                                                        <label class="form-control-label" for="input-first-name">First Name</label>
                                                        <input type="text" id="input-first-name" class="form-control form-control-alternative" disabled
                                                               value='${CurrentUserData.first_name}' >
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group focused">
                                                        <label class="form-control-label" for="input-last-name">Last Name</label>
                                                        <input type="text" id="input-last-name" class="form-control form-control-alternative" disabled
                                                               placeholder="Last name" value='${CurrentUserData.last_name}' >
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr class="my-4">
                                        <!-- Address -->
                                        <h6 class="heading-small text-muted mb-4">Contact information</h6>
                                        <div class="pl-lg-4">
                                            <div class="row">
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-4">
                                                    <div class="form-group focused">
                                                        <label class="form-control-label" for="input-moblie">Mobile No.</label>
                                                        <input disabled type="text" id="input-city" class="form-control form-control-alternative" placeholder="Mobile No."  value='${CurrentUserData.phoneNum}' >
                                                    </div>
                                                </div>
                                            </div>
                                            <hr class="my-4">
                                        </div>
                                        <h6 class="heading-small text-muted mb-4">Delete Account</h6>
                                        <div class="pl-lg-4">

                                            <div class="row">
                                                <div class="col-lg-4">
                                                    <div class="form-group focused">

                                                        <input type="submit" value="Delete Account" class="btn btn-danger btn-user btn-block" data-toggle="modal" data-target="#deleteModal">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr class="my-4">
                                        <!-- Description -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- /.container-fluid -->

                <!-- Mission Statement -->
                <div class="bg-light mt-5">
                    <div class="card mb-4 py-3 border-bottom-primary">
                        <div class="row justify-content-center align-items-center text-center">
                            <div class="col">
                                <h2><strong>Our Mission</strong></h2>
                                <p class="lead text-muted">Empowering Parking Choices in Singapore.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <!-- Footer Container Start -->
                <footer class="sticky-footer  bg-white text-center text-dark">
                    <!-- Grid container -->
                    <div class="container my-auto">
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
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Delete Modal-->
        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteLabel">Are you sure you want to delete your account? This action is irreversible.</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Delete Account" below if you are ready to delete your account.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <form action="DeleteProfileServlet">

                            <!-- <button id="deleteBtn" class="btn btn-primary">Delete Account</button>-->
                            <input type="submit" value="Delete Account" class="btn btn-danger btn-user btn-block">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <button id="logoutBtn" class="btn btn-primary">Logout</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Thank You Modal -->
        <div class="modal fade" id="thankyouModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thank You!</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">Ã—</span>
                        </button>
                    </div>
                    <div class="modal-body mx-auto">Submission Received!</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Exit</button>
                    </div>
                </div>
            </div>
        </div>



        <!-- Bootstrap JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.5.0/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>


        <script>
            /*document.getElementById("submitFeedbackBtn").addEventListener("click", function () {
             $("#feedbackModal").modal("hide"); // Close the feedback modal
             $("#thankyouModal").modal("show"); // Show the thank you modal
             });*/
        </script>

    </body>

</html>