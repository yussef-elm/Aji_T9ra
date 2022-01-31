<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="com.aji_t9ra.Models.*"%>
<%@page import="com.aji_t9ra.DAO.*"%>


<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>


<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("login.jsp");
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
List<String> categories = (List<String>) request.getAttribute("categories");

String p = (String) request.getAttribute("profile");

Enseignant enseignant = (Enseignant) request.getAttribute("enseignant");
Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
User admin = (User) request.getAttribute("admin");
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

							<!-- Main-menu -->
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
		<c:if test="${enseignant!=null}">
			</br>
			</br>
			<div class="container">
				<div class="">
					<div class="card" style="border: solid;">
						<div class="card-body text-center">
							<c:if test="${enseignant.isApproved()==true}">
								<img class="avatar rounded-circle"
									style="max-width: 2rem; margin-top: -2rem"
									src="assets/img/elements/certif.png" alt="certif">
							</c:if>
							<img class="avatar rounded-circle" style="border: solid;"
								src="assets/img/elements/prof.jpg" alt="">
							<c:if test="${enseignant.isActive()==true}">
								<img class="avatar rounded-circle"
									style="max-width: 2rem; margin-top: -2rem"
									src="assets/img/elements/act.png" alt="">
							</c:if>
							<h4 class="card-title"><%=enseignant.getNom()%><%=" "%><%=enseignant.getPrenom()%></h4>
							<h6 class="card-subtitle mb-2 text-muted">
								<%=enseignant.getOrganisme() + " (" + enseignant.getNiveau() + ")"%>
							</h6>
							<h6 class="card-subtitle mb-2 text-muted">
								<%=enseignant.getEmail()%></h6>
							<c:if test="${enseignant.getListMatieres() !=null }">
								<h6>
									Matiéres :<%
								for (Matiere m : enseignant.getListMatieres()) {
								%><%=m.getNom() + "/"%>
									<%
									}
									%>
								</h6>
							</c:if>
							<p class="card-text"><%=enseignant.getDescription()%></p>

							<c:if test="${user.isAdmin()==true}">
								<c:if
									test="${enseignant.isApproved() == false && enseignant.isActive()==false}">
									<a href="Enseignant?op=approver&id=<%=enseignant.getId()%>"
										type="button" class="btn btn-success"
										style="background: green;">Approuver</a>
									<a href="Enseignant?op=supprimer&id=<%=enseignant.getId()%>"
										type="button" class="btn btn-danger"
										style="background: #c70e0e;">Supprimer</a>
								</c:if>

								<c:if
									test="${enseignant.isApproved() == true && enseignant.isActive()==true}">
									<a href="Enseignant?op=desactiver&id=<%=enseignant.getId()%>"
										type="button" class="btn btn-danger"
										style="background: #c70e0e;">Desactiver</a>
								</c:if>

								<c:if
									test="${enseignant.isApproved() == true && enseignant.isActive()==false}">
									<a href="Enseignant?op=activer&id=<%=enseignant.getId()%>"
										type="button" class="btn btn-danger"
										style="background: green;">Activer</a>
								</c:if>
							</c:if>
							<c:if
								test="${enseignant.getId() == user.getId() && user.isAdmin()==false}">
								<a
									href="ModifierProfile.jsp?profile=Enseignant&id=<%=enseignant.getId()%>"
									type="button" class="btn btn-info btn-danger"
									style="background: #87CEEB; padding: 25px 30px;">Modifier</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			</br>
			</br>
		</c:if>
		<c:if test="${etudiant!=null}">
			</br>
			</br>
			<div class="container">
				<div class="">
					<div class="card" style="border: solid;">
						<div class="card-body text-center">
							<c:if test="${etudiant.isApproved()==true}">
								<img class="avatar rounded-circle"
									style="max-width: 2rem; margin-top: -2rem"
									src="assets/img/elements/certif.png" alt="certif">
							</c:if>
							<img class="avatar rounded-circle" style="border: solid;"
								src="assets/img/elements/etup.png" alt="">
							<c:if test="${etudiant.isActive()==true}">
								<img class="avatar rounded-circle"
									style="max-width: 2rem; margin-top: -2rem"
									src="assets/img/elements/act.png" alt="">
							</c:if>
							<h4 class="card-title"><%=etudiant.getNom()%><%=" "%><%=etudiant.getPrenom()%></h4>
							<h6 class="card-subtitle mb-2 text-muted">
								<%=etudiant.getEcole() + " (" + etudiant.getNiveau_scolaire() + ")"%>
							</h6>
							<h6 class="card-subtitle mb-2 text-muted">
								<%=etudiant.getEmail()%></h6>
							<p class="card-text"><%=etudiant.getDescription()%></p>

							<c:if test="${user.isAdmin()==true}">
								<c:if
									test="${etudiant.isApproved() == false && etudiant.isActive()==false}">
									<a href="Etudiant?op=approver&id=<%=etudiant.getId()%>"
										type="button" class="btn btn-success"
										style="background: green;">Approuver</a>
									<a href="Etudiant?op=supprimer&id=<%=etudiant.getId()%>"
										type="button" class="btn btn-danger"
										style="background: #c70e0e;">Supprimer</a>
								</c:if>

								<c:if
									test="${etudiant.isApproved() == true && etudiant.isActive()==true}">
									<a href="Etudiant?op=desactiver&id=<%=etudiant.getId()%>"
										type="button" class="btn btn-danger"
										style="background: #c70e0e;">Desactiver</a>
								</c:if>

								<c:if
									test="${etudiant.isApproved() == true && etudiant.isActive()==false}">
									<a href="Etudiant?op=activer&id=<%=etudiant.getId()%>"
										type="button" class="btn btn-danger"
										style="background: green;">Activer</a>
								</c:if>
							</c:if>
							<c:if
								test="${etudiant.getId() == user.getId() && user.isAdmin()==false}">
								<a
									href="ModifierProfile.jsp?profile=Etudiant&id=<%=etudiant.getId()%>"
									type="button" class="btn btn-info btn-danger"
									style="background: #87CEEB; padding: 25px 30px;">Modifier</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>

			</br>
			</br>
		</c:if>

		<c:if test="${admin!=null}">
			</br>
			</br>
			<div class="container">
				<div class="">
					<div class="card" style="border: solid;">
						<div class="card-body text-center">
							<c:if test="${admin.isApproved()==true}">
								<img class="avatar rounded-circle"
									style="max-width: 2rem; margin-top: -2rem"
									src="assets/img/elements/certif.png" alt="certif">
							</c:if>
							<img class="avatar rounded-circle" style="border: solid;"
								src="assets/img/elements/admin.jpg" alt="">
							<c:if test="${admin.isActive()==true}">
								<img class="avatar rounded-circle"
									style="max-width: 2rem; margin-top: -2rem"
									src="assets/img/elements/act.png" alt="">
							</c:if>
							<h4 class="card-title"><%=admin.getNom()%><%=" "%><%=admin.getPrenom()%></h4>
							<h6 class="card-subtitle mb-2 text-muted">
								<%="Admin"%>
							</h6>
							<h6 class="card-subtitle mb-2 text-muted">
								<%=admin.getEmail()%></h6>
						</div>
					</div>
				</div>
			</div>

			</br>
			</br>
		</c:if>

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