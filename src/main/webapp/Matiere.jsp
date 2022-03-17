<%@page import="com.aji_t9ra.DAO.EnseignantDAO"%>
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
String niveau = (String) request.getAttribute("niveau");
String categorie = (String) request.getAttribute("categorie");
List<Matiere> listMatieres = (List<Matiere>) request.getAttribute("listMatieres");
%>

<%
session.setAttribute("categories", request.getAttribute("categories"));
session.setAttribute("DUsers", request.getAttribute("DUsers"));
session.setAttribute("DEtudiant", request.getAttribute("DEtudiant"));
session.setAttribute("newEtudiant", request.getAttribute("newEtudiant"));
session.setAttribute("nosEtudiant", request.getAttribute("nosEtudiant"));
session.setAttribute("DEnseignant", request.getAttribute("DEnseignant"));
session.setAttribute("newEnseignant", request.getAttribute("newEnseignant"));
session.setAttribute("nosEnseignant", request.getAttribute("nosEnseignant"));
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
										
										<c:if test="${user.getRole().equals(\"etudiant\")}">
                                        <% final EtudiantDAO etuDao = new EtudiantDAO();
										   Etudiant etu= etuDao.getEtudiantByID(user.getId());
										   if(!etu.getNiveau_scolaire().equals("Universitaire")){ %>			
											<li class="navbar-dropdown"><a
												href="Etudiant?op=MatieresDispo&id<%=user.getId() %>">Matiéres
													Disponible</a></li>
											<%}else{ %>		
											<ul class="navigation navbar-links">
												<li class="navbar-dropdown"><a > Matiéres
													Disponible</a>
													<div class="dropdown">
														<%
														for (String c : categories) {
														%>
														<a href="Etudiant?op=MatieresDispo&id=<%=user.getId()%>&categorie=<%=c%>"
															style="padding: 10px 10px;"><%=c%></a>
														<%
														}
														%>
													</div></li>
											</ul>
										<%} %>	
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
												<li class="navbar-dropdown"><a> Matiéres Disponible</a>
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

	<main>
		<!--? Categories Area Start -->
		<div class="categories-area section-padding15"
			style="background-image: url('./assets/img/blog/back1.jpg'); background-size: cover;">
			<%
			if (user.isAdmin()) {
			%>
			<div class="row">
				<div class="col-lg-2">
					<div class=" text-center mt-60 " style="margin-left: 150px;">
						<%
						if (niveau.equals("Universitaire")) {
						%>
						<a href="Matiere?niveau=<%=niveau%>&categorie=<%=categorie%>"
							class="btn btn-danger btn-sm"
							style="padding: 20px 20px; font-size: 12px; background: green; border-radius: 50px;">
							Matiére Disponible</a>
						<%
						} else {
						%>
						<a href="Matiere?niveau=<%=niveau%>" class="btn btn-danger btn-sm"
							style="padding: 20px 20px; font-size: 12px; background: green; border-radius: 50px;">
							Matiére Disponible</a>
						<%
						}
						%>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="browse-btn2 text-center mt-30">
						<a href="AjouterMatiere.jsp" class="btn">Ajouter une nouvelle
							matiére</a>
					</div>
				</div>
				<div class="col-lg-2">
					<div class=" text-center mt-60 " style="margin-left: -300px;">
						<%
						if (niveau.equals("Universitaire")) {
						%>
						<a
							href="Matiere?niveau=<%=niveau%>&categorie=<%=categorie%>&etat=indisponible"
							class="btn btn-danger btn-sm"
							style="padding: 20px 20px; font-size: 12px; background: #c70e0e; border-radius: 50px;">
							Matiére Indisponible <i class="flaticon-communication"></i>
						</a>
						<%
						} else {
						%>
						<a
							href="Matiere?niveau=<%=niveau%>&etat=indisponible"
							class="btn btn-danger btn-sm"
							style="padding: 20px 20px; font-size: 12px; background: #c70e0e; border-radius: 50px;">
							Matiére Indisponible <i class="flaticon-communication"></i>
						</a>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<%
			}
			%>

			</br>
			<div class="container">

				<div class="row">
					<%
					for (Matiere m : listMatieres) {
					%>
					<div class="col-lg-4 col-md-6 col-sm-6">
						<div class="single-cat mb-50 ">
							<div class="cat-icon">
								<c:if test="${categorie.equals(\"Informatique\")}">
									<span class="flaticon-computing"></span>
								</c:if>
								<c:if test="${categorie.equals(\"Communication\")}">
									<span class="flaticon-education"></span>
								</c:if>
								<c:if test="${categorie.equals(\"Général\")}">
									<span class="flaticon-business"></span>
								</c:if>
								<c:if test="${categorie.equals(\"Economie\")}">
									<span class="flaticon-tools-and-utensils"></span>
								</c:if>
								<c:if test="${categorie.equals(\"Réseaux\")}">
									<span class="flaticon-communication"></span>
								</c:if>
								<c:if test="${categorie==null || categorie.equals(\"null\")}">
									<span class="flaticon-education"></span>
								</c:if>
							</div>
							<div class="cat-cap">
								<h5>
									<a href="Matiere?niveau=<%=niveau%>&matiere=<%=m.getNom()%>"><%=m.getNom() + "  "%></br>
										<span class="flaticon-computing"
										style="font-size: 60%; color: orange;">Enseignants</span> </a>

								</h5>
								<p class="scroller"
									style="text-align: justify; scrollbar-width: thin;"><%=m.getDescription()%></p>
								<%
								if (user.isAdmin()) {
								%>
								<a href="ModifierMatiere.jsp?id=<%=m.getId()%>" type="button"
									class="btn btn-primary btn-sm"
									style="font-size: 12px; background: #87CEEB; padding: 25px 30px;">
									<span style="color: white;">Modifier</span>
								</a>
								<%
								if (m.isAvailable() == true) {
								%>
								<a href="Matiere?op=MatiereUnavailable&id=<%=m.getId()%>"
									class="btn btn-primary btn-sm"
									style="font-size: 12px; background: #c70e0e; padding: 25px 30px;">
									<span style="color: white;">Indisponible</span>
								</a>
								<%
								} else {
								%>
								<a href="Matiere?op=MatiereAvailable&id=<%=m.getId()%>"
									class="btn btn-primary btn-sm"
									style="font-size: 12px; background: green; padding: 25px 30px;">
									<span style="color: white;">Disponible</span>
								</a>
								<%
								}
								}
								%>
								<%
								if (user.getRole().equals("enseignant")) {
									boolean MesMatieres = (boolean) request.getAttribute("MesMatieres");
									if (MesMatieres) {
								%>	
								<a
									href="meeting"
									class="btn btn-primary btn-sm"
								    style="font-size: 12px; background: #87CEEB; padding: 25px 30px;">
									<span style="color: white;">Reunion</span>
								</a>														
								<a
									href="Enseignant?op=MatiereUnavailable&idEnseignant=<%=user.getId()%>&idMatiere=<%=m.getId()%>"
									class="btn btn-primary btn-sm"
									style="font-size: 12px; background: #c70e0e; padding: 25px 30px;">
									<span style="color: white;">Indisponible</span>
								</a>

								<%
								} else {
								%>
								<a
									href="Enseignant?op=MatiereAvailable&idEnseignant=<%=user.getId()%>&idMatiere=<%=m.getId()%>"
									class="btn btn-primary btn-sm"
									style="font-size: 12px; background: green; padding: 25px 30px; margin: 0% 22%;">
									<span style="color: white;">Enseigner</span>
								</a>

								<%
								}
								}
								%>
							</div>
						</div>
					</div>
					<%
					}
					%>
				</div>
				<!-- Section Button -->
			</div>
		</div>
		<!-- Categories Area End -->
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