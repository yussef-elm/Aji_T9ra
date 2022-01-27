<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="com.aji_t9ra.Models.User"%>
<%@page import="com.aji_t9ra.Models.Enseignant"%>

<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<%
Enseignant enseignant = (Enseignant) request.getAttribute("enseignant");
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
					<!-- Logo -->
					<div class="logo d-none d-lg-block"
						style="background-color: white; height: 40px; width: 100px; text-align: center;">
						<a href="index.html"><img src="assets/img/logo/logo1.png"
							alt=""></a>
					</div>
					<div class="container">
						<div class="menu-wrapper">
							<!-- Logo -->
							<div class="logo logo2 d-block d-lg-none"
								style="background-color: white; height: 40px; width: 100px; text-align: center;">
								<a href="index.html"><img src="assets/img/logo/logo1.png"
									alt=""></a>
							</div>
							<!-- Main-menu -->
							<div class="main-menu d-none d-lg-block">
								<nav>
									<ul id="navigation">
										<li><a href="index.jsp">Accueil</a></li>
										<li><a href="about.jsp">À propos</a></li>
										<li><a href="Enseignant?op=newEnseignants">Nouveaux
												Enseignants</a></li>
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
	</br> </br>  
		<div class="container">
			<div class="">
				<div class="card">
					<div class="card-body text-center">
						<img class="avatar rounded-circle"
							src="assets/img/elements/prof.jpg" alt="Bologna">
						<h4 class="card-title"><%=enseignant.getNom()%><%=" "%><%=enseignant.getPrenom()%></h4>
						<h6 class="card-subtitle mb-2 text-muted">
							<%=enseignant.getOrganisme() +" ("+enseignant.getNiveau()+")"%> </h6>
						<h7> <%=enseignant.getEmail() %></h7>
						<p class="card-text"><%=enseignant.getDescription()%></p>
						<a href="#" type="button" class="btn btn-success"
							style="background: green;">Approver</a> <a href="#" type="button"
							class="btn btn-danger" style="background: #c70e0e;">Supprimer</a>
					</div>
				</div>
			</div>
		</div>

		</br> </br> 
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
	<style>
body {
	padding: 2rem 0rem;
}

.avatar {
	border: 0.3rem solid rgba(#fff, 0.3);
	margin-top: -6rem;
	margin-bottom: 1rem;
	max-width: 9rem;
}
</style>
</body>
</html>