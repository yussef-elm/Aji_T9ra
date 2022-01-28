<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="com.aji_t9ra.Models.User"%>
<%@page import="com.aji_t9ra.Models.Enseignant"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>


<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}
if (user.isAdmin() == false) {
	response.sendRedirect("index.jsp");
	return;
}
%>
<%
int nosEnseignant = (int) request.getAttribute("nosEnseignant");
int newEnseignant = (int) request.getAttribute("newEnseignant");
int DEnseignant = (int) request.getAttribute("DEnseignant");
int nosEtudiant = (int) request.getAttribute("nosEtudiant");
int newEtudiant = (int) request.getAttribute("newEtudiant");
int DEtudiant = (int) request.getAttribute("DEtudiant");
int DUsers = (int) request.getAttribute("DUsers");

%>
<%
// get the students from the request object (sent by servlet)
List<Enseignant> listEnseignants = (List<Enseignant>) request.getAttribute("listEnseignants");
%>



<!doctype html>
<html class="no-js" lang="zxx">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Education |Accueil</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="manifest" href="site.webmanifest">
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/favicon.ico">

<!-- CSS here -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="assets/css/slicknav.css">
<link rel="stylesheet" href="assets/css/flaticon.css">
<link rel="stylesheet" href="assets/css/gijgo.css">
<link rel="stylesheet" href="assets/css/animate.min.css">
<link rel="stylesheet" href="assets/css/magnific-popup.css">
<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="assets/css/themify-icons.css">
<link rel="stylesheet" href="assets/css/slick.css">
<link rel="stylesheet" href="assets/css/nice-select.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="assets/css/navbar.css">
<link rel="stylesheet" href="assets/css/navbar.css">

</head>
<body>
	<!--? Preloader Start -->
	<div id="preloader-active">
		<div
			class="preloader d-flex align-items-center justify-content-center">
			<div class="preloader-inner position-relative">
				<div class="preloader-circle"></div>
				<div class="preloader-img pere-text">
					<img src="assets/img/logo/loder.png" alt="">
				</div>
			</div>
		</div>
	</div>
	<!-- Preloader Start -->
	<header>
		<!-- Header Start -->
		<div class="header-area">
			<div class="main-header ">
				<div class="header-top d-none d-lg-block">
					<div class="header-left-social">
						<div class="wrapper" style="padding: 10px;">
							<img src="assets/img/elements/eleve.jpg"
								style="width: 30px; height: 30px; border-radius: 50%; object-fit: cover; object-position: center right;">
						</div>
					</div>
					<div class="container">
						<div class="col-xl-12">
							<div
								class="row d-flex justify-content-between align-items-center">
								<div class="header-info-left">
									<ul>
										<li><%=user.getNom()%></li>
										<li><%=user.getPrenom()%></li>
										<li><%=user.getEmail()%></li>
									</ul>
								</div>
								<div class="header-info-right">
									<ul>
										<li><a href="Logout"><i class="ti-user"></i>Deconnexion</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="header-bottom header-sticky">

					<div class="container">
						<div class="menu-wrapper">

							<!-- Main-menu -->
							<div class="main-menu d-none d-lg-block">
								<nav>

									<ul class=" navigation navbar-links">
										<li class="navbar-dropdown"><a style="padding-top: 30px;"
											href="index.html"><img src="assets/img/logo/logo1.png"></a></li>
										<li class="navbar-dropdown"><a href="#">Acceuil</a></li>
										<c:if test="${user.isAdmin() == true}">

											<li class="navbar-dropdown"><a href="#">Enseignants</a>
												<div class="dropdown">
													<a href="Enseignant?op=Enseignants">Nos Enseignants <%="(" + nosEnseignant + ")"%></a>
													<a href="Enseignant?op=newEnseignants">Nouveaux
														Enseignants <%="(" + newEnseignant + ")"%></a>
												</div></li>
											<li class="navbar-dropdown"><a href="#">Etudiants</a>
												<div class="dropdown">
													<a href="Etudiant?op=Etudiants">Nos Etudiants <%="(" + nosEtudiant + ")"%></a>
													<a href="Etudiant?op=newEtudiants">Nouveaux
														Etudiants <%="(" + newEtudiant + ")"%></a>
												</div></li>
											<li><a href="User?op=ComptesDesactiver">Comptes
													Desactivés <%="(" + DUsers + ")"%></a></li>
										</c:if>

									</ul>
								</nav>
							</div>
							<!-- Header-btn -->

						</div>
						<!-- Mobile Menu -->
						<div class="col-12">
							<div class="mobile_menu d-block d-lg-none"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Header End -->
	</header>

	<main>

		<!-- Hero End -->
		<div class="team-area ">
			<div class="container">
				<div class="row">

					<%
					for (Enseignant e : listEnseignants) {
					%>
					<div class="col-lg-3 col-md-6 col-sm-6">
						<div class="single-team mb-30">
							<div class="team-img">
								<img src="assets/img/elements/prof.jpg" alt=""
									style="width: 140px; height: 140px; display: block; margin-left: auto; margin-right: auto;">
							</div>
							<div class="team-caption">
								<h3>
									<a href="profile.jsp"><%=e.getNom()%> <%=" "%><%=e.getPrenom()%></a>
								</h3>
								<p><%=e.getOrganisme()%></p>
								<a href="Enseignant?op=profile&id=<%=e.getId()%>" type="button"
									class="btn btn-info">Profile</a>
							</div>
						</div>
					</div>

					<%
					}
					%>


				</div>
			</div>
		</div>


	</main>

	<footer>
		<!--? Footer Start-->
		<div class="footer-area footer-bg">
			<div class="container">

				<!-- Footer Bottom -->
				<div class="footer-bottom">
					<div class="row d-flex align-items-center">
						<div class="col-lg-12">
							<div class="footer-copy-right text-center">
								<p>
									<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
									Copyright &copy;
									<script>
										document
												.write(new Date().getFullYear());
									</script>
									Tous les droits sont réservés.
									<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer End-->
	</footer>
	<!-- Scroll Up -->
	<div id="back-top">
		<a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
	</div>

	<!-- JS here -->

	<script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>
	<!-- Jquery, Popper, Bootstrap -->
	<script src="./assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script src="./assets/js/popper.min.js"></script>
	<script src="./assets/js/bootstrap.min.js"></script>
	<!-- Jquery Mobile Menu -->
	<script src="./assets/js/jquery.slicknav.min.js"></script>

	<!-- Jquery Slick , Owl-Carousel Plugins -->
	<script src="./assets/js/owl.carousel.min.js"></script>
	<script src="./assets/js/slick.min.js"></script>
	<!-- One Page, Animated-HeadLin -->
	<script src="./assets/js/wow.min.js"></script>
	<script src="./assets/js/animated.headline.js"></script>
	<script src="./assets/js/jquery.magnific-popup.js"></script>

	<!-- Date Picker -->
	<script src="./assets/js/gijgo.min.js"></script>
	<!-- Nice-select, sticky -->
	<script src="./assets/js/jquery.nice-select.min.js"></script>
	<script src="./assets/js/jquery.sticky.js"></script>

	<!-- counter , waypoint -->
	<script src="./assets/js/jquery.counterup.min.js"></script>
	<script src="./assets/js/waypoints.min.js"></script>

	<!-- contact js -->
	<script src="./assets/js/contact.js"></script>
	<script src="./assets/js/jquery.form.js"></script>
	<script src="./assets/js/jquery.validate.min.js"></script>
	<script src="./assets/js/mail-script.js"></script>
	<script src="./assets/js/jquery.ajaxchimp.min.js"></script>

	<!-- Jquery Plugins, main Jquery -->
	<script src="./assets/js/plugins.js"></script>
	<script src="./assets/js/main.js"></script>
</body>
</html>