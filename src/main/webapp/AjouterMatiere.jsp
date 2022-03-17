<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="com.aji_t9ra.Models.*"%>
<%@page import="com.aji_t9ra.DAO.*" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<%
int nosEnseignant = (int) session.getAttribute("nosEnseignant");
int newEnseignant = (int) session.getAttribute("newEnseignant");
int DEnseignant = (int) session.getAttribute("DEnseignant");
int nosEtudiant = (int) session.getAttribute("nosEtudiant");
int newEtudiant = (int) session.getAttribute("newEtudiant");
int DEtudiant = (int) session.getAttribute("DEtudiant");
int DUsers = (int) session.getAttribute("DUsers");
List<String> categories = (List<String>) session.getAttribute("categories");
List<String> niveaux = (List<String>) session.getAttribute("niveaux");
%>


<%

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
<link rel="stylesheet" href="assets/css/scroll.css">



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
										<li><a href="User?op=profile&id=<%=user.getId()%>"><i
												class="ti-user"></i> Mon Profil</a></li>
										<li><a href="Logout"><i class="ti-shift-right"></i>Deconnexion</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="header-bottom header-sticky">

					<div class="container">
						<div class="menu-wrapper">

							<div class="main-menu d-none d-lg-block">
								<nav>
									<ul class=" navigation navbar-links">
										<li class="navbar-dropdown"><a style="padding-top: 30px;"
											href="index.jsp"><img src="assets/img/logo/logo1.png"></a></li>
										<li class="navbar-dropdown"><a href="index.jsp">Acceuil</a></li>
										<c:if test="${user.isAdmin() == true}">

											<li class="navbar-dropdown"><a>Enseignants</a>
												<div class="dropdown">
													<a href="Enseignant?op=Enseignants"
														style="padding: 10px 10px;">Nos Enseignants <%="(" + nosEnseignant + ")"%></a>
													<a href="Enseignant?op=newEnseignants"
														style="padding: 10px 10px;">Nouveaux Enseignants <%="(" + newEnseignant + ")"%></a>
												</div></li>
											<li class="navbar-dropdown"><a>Etudiants</a>
												<div class="dropdown">
													<a href="Etudiant?op=Etudiants" style="padding: 10px 10px;">Nos
														Etudiants <%="(" + nosEtudiant + ")"%></a> <a
														href="Etudiant?op=newEtudiants"
														style="padding: 10px 10px;">Nouveaux Etudiants <%="(" + newEtudiant + ")"%></a>
												</div></li>
											<li class="navbar-dropdown"><a>Matiéres</a>
												<div class="dropdown">
													<ul class="navigation navbar-links-second">
														<li class="navbar-dropdown-second"><a
															style="padding: 10px 10px;"> Universitaire</a>
															<div class="dropdown-second">
																<%
																for (String c : categories) {
																%>
																<a href="Matiere?niveau=Universitaire&categorie=<%=c%>"
																	style="padding: 10px 10px;"><%=c%></a>
																<%
																}
																%>
															</div></li>
													</ul>
													<a href="Matiere?niveau=Lycee" style="padding: 10px 10px;">
														Lycée </a> <a href="Matiere?niveau=College"
														style="padding: 10px 10px;"> Collége </a>
												</div></li>
											<li><a href="User?op=ComptesDesactiver">Comptes
													Desactivés <%="(" + DUsers + ")"%></a></li>
										</c:if>
										
										<c:if test="${user.getRole().equals(\"enseignant\")}">
											<li class="navbar-dropdown"><a
												href="Enseignant?op=mesMatieres&id=<%=user.getId()%>">Mes
													Matiéres</a></li>
											<%
											final EnseignantDAO eDao = new EnseignantDAO();
											Enseignant e = eDao.getEnseignantByID(user.getId());
											if (!e.getNiveau().equals("Universitaire")) {
											%>
											<li class="navbar-dropdown"><a
												href="Enseignant?op=MatieresDispo&id=<%=user.getId()%>">Matiéres
													Disponible</a></li>
											<%
											} else {
											%>
											<ul class="navigation navbar-links">
												<li class="navbar-dropdown"><a> Matiéres
														Disponible</a>
													<div class="dropdown">
														<%
														for (String c : categories) {
														%>
														<a
															href="Enseignant?op=MatieresDispo&id=<%=user.getId()%>&categorie=<%=c%>"
															style="padding: 10px 10px;"><%=c%></a>
														<%
														}
														%>
													</div></li>
											</ul>
											<%
											}
											%>
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
	<main
		style="background-image: url('./assets/img/blog/back1.jpg'); background-size: cover;">
		<div class="row justify-content-center">
			<div class="col-md-6 col-lg-8">
				<div class=""
					style="margin-top: 40px; padding: 0px 140px; padding-bottom: 80px;">
					<h3 class="text-center mb-4">Ajouter Une Nouvelle Matiére</h3>
					<form action="Matiere?op=ajouterMatiere" method="post"
						class="login-form" style="text-align: center;">
						<div class="form-group">
							<%
							for (String n : niveaux) {
							%>
							<input type="radio" name="niveau" value="<%=n%>" required>
							<label style="padding: 0 10px;" for="<%=n%>"> <%=n%>
							</label>
							<%
							}
							%>
						</div>
						<div class="form-group">
							<input type="text" name="Matiere" id="matiere"
								class="form-control rounded-left" placeholder="Matiére" required>
						</div>
						<div class="form-group">
							<%
							for (String c : categories) {
							%>
							<input type="radio" name="categorie" value="<%=c%>" required>
							<label style="padding: 0 10px; font-family:" for="<%=c%>">
								<%=c%>
							</label>
							<%
							}
							%>
						</div>
						<div class="form-group">
							<textarea name="description" id="description"
								class="form-control rounded-left" placeholder="Description"
								rows="4" required></textarea>
						</div>

						<div class="form-group"
							style="font-size: 50%; text-align: center;">
							<button type="submit" value="sinscrire"
								class="form-control btn btn-primary rounded submit px-3">Ajouter</button>
						</div>
					</form>
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