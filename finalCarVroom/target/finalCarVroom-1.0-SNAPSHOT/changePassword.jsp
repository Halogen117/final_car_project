<%-- 
    Document   : changePassword
    Created on : Oct 19, 2023, 1:39:15 PM
    Author     : Sk37chy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    } else if (session.getAttribute("security_qn") != "pass") {
        response.sendRedirect("securityQn.jsp");
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
        <script src="js/jquery-3.7.1.min.js" type="text/javascript" ></script>
        <script src="js/jquery_cookie.js" type="text/javascript"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
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
                                    <a class="dropdown-item" href="profile.jsp">
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

                        <p class="mb-4 ml-3">Change Password</p>

                        <!-- Content Row -->
                        <div class="row">
                            <div class="col-xl-12 order-xl-1">
                                <%
                                    HttpSession user_session = request.getSession();

                                    if (session.getAttribute("update_pwd") == "wrong_pwd") {
                                        user_session.setAttribute("update_pwd", "");
                                %>
                                <div class="d-flex align-items-center justify-content-center">
                                    <div class="alert alert-danger d-flex align-items-center" role="alert" style="border-radius: 20px;">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="red" class="bi bi-x-circle" viewBox="0 0 16 16" style="margin-right: 10px;" >
                                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                        </svg>
                                        <div>
                                            Current password is wrong. Try again!
                                        </div>
                                    </div>
                                </div>
                                <%                                    }
                                %>
                                <div class="card bg-light shadow">
                                    <div class="card-header bg-white border-0">
                                        <div class="row align-items-center">
                                            <div class="col-8">
                                                <h3 class="mb-0">Change your password!</h3>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <form action="ChangePasswordServlet" onsubmit="return checkValidation()">

                                            <div class="pl-lg-4">
                                                <h5 class="h5 text-gray-900 mb-4">Current Password</h5>
                                                <div class="form-group">
                                                    <input type="password" class="form-control form-control-user" id="currPassword"
                                                           name="currPassword" placeholder="Current Password" required>
                                                </div>
                                                <h5 class="h5 text-gray-900 mb-4">New Password</h5>
                                                <div class="form-group">
                                                    <input type="password" class="form-control form-control-user" id="newPassword"
                                                           name="newPassword" placeholder="New Password" required>
                                                    <br>
                                                    <div id="checkSameCurrentIv" class="alert alert-danger d-none" role="alert"> 
                                                        <i class="fas fa-times text-danger me-3"></i>
                                                        New password should not be same as the current one!
                                                    </div>

                                                    <div id="newPwdValid" class="alert alert-danger px-4 py-3 mb-0 d-none">
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
                                                <h5 class="h5 text-gray-900 mb-4">Repeat Password</h5>
                                                <div class="form-group">
                                                    <input type="password" class="form-control form-control-user" id="repeatPassword"
                                                           name="repeatPassword" placeholder="Repeat Password" required>
                                                    <br>
                                                    <div id="repeatValid" class="d-none">
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

                                            </div>
                                            <hr class="my-4">
                                            <div class="mt-5 text-center">
                                                <input type="submit" value="Change Password" class="btn btn-primary">
                                            </div>
                                        </form>
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
                        <a class="btn btn-primary" href="login.jsp">Logout</a>
                    </div>
                </div>
            </div>
        </div>




        <!-- Bootstrap JavaScript -->
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>


        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <script src="<%= request.getContextPath()%>/js/changePwd.js"></script>
        
    </body>

</html>
