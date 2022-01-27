<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html class="no-js" lang="zxx">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Education | Inscription</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/favicon.ico">

<!-- CSS here -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="assets/css/slicknav.css">
<link rel="stylesheet" href="assets/css/animate.min.css">
<link rel="stylesheet" href="assets/css/magnific-popup.css">
<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="assets/css/themify-icons.css">
<link rel="stylesheet" href="assets/css/themify-icons.css">
<link rel="stylesheet" href="assets/css/slick.css">
<link rel="stylesheet" href="assets/css/nice-select.css">
<link rel="stylesheet" href="assets/css/style.css">
</head>
<!--? Preloader Start -->
<div id="preloader-active">
	<div class="preloader d-flex align-items-center justify-content-center">
		<div class="preloader-inner position-relative">
			<div class="preloader-circle"></div>
			<div class="preloader-img pere-text">
				<img src="assets/img/logo/loder.png" alt="">
			</div>
		</div>
	</div>
</div>
<!-- Preloader Start -->
<!-- Header Start -->
<div class="header-area">
	<div class="main-header ">
		<div class="header-top d-none d-lg-block">
			<!-- Left Social -->

			<div class="container">
				<div class="col-xl-12">
					<div class="row d-flex justify-content-between align-items-center">
						<div class="header-info-left">
							<ul>
								<li></li>
								<li></li>
							</ul>
						</div>
						<div class="header-info-right">
							<ul>
								<li><a href="login.jsp"><i class="ti-user"></i>Connexion</a></li>
								<li><a href="sinscrire.jsp"><i class="ti-lock"></i>Inscription
								</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="header-bottom header-sticky">
			<!-- Logo -->
			<div class="logo d-none d-lg-block"
				style="background-color: white; height: 40px; width: 50px; text-align: center;">
				<a href="index.html"><img src="assets/img/logo/logo1.png" alt=""></a>
			</div>
			<div class="container">
				<div class="menu-wrapper">
					<!-- Logo -->
					<div class="logo logo2 d-block d-lg-none"
						style="background-color: white; height: 40px; width: 50px; text-align: center;">
						<a href="index.html"><img src="assets/img/logo/logo1.png"
							alt=""></a>
					</div>
					<!-- Main-menu -->
					<div class="main-menu d-none d-lg-block">
						<nav>
							<ul id="navigation">
								<li></li>
								<li><a href="index.jsp">Accueil</a></li>

							</ul>
						</nav>
					</div>

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

	<div class="row justify-content-center">
		<div class="col-md-6 col-lg-4">
			<div class="login-wrap p-4 p-md-5" style="margin-top: -100px;">
				<div class="icon d-flex align-items-center justify-content-center">
					<span class="fa fa-user-o"></span>
				</div>
				<h3 class="text-center mb-4">Inscrivez-vous : Compte Etudiant</h3>

				<form action="Etudiant?op=addetudiant" method="post"
					class="login-form" style="text-align: center;">
					<div class="form-group">
						<input type="radio" name="niveau" value="Universitaire"
							style="margin-right: 10px;" checked> <label
							for="Universitaire" > Université
						</label> <input type="radio" name="niveau" value="Lycée"
							style="margin-left: 20px;"> <label for="lycée"
							> Lycée </label> <input type="radio"
							name="niveau " value="Collège " style="margin-left: 20px;">
						<label for="Collège " > Collège
						</label>
					</div>
					<div class="form-group">
						<input type="text" name="ecole" id="ecole"
							class="form-control rounded-left" placeholder="ecole"
							required>
					</div>
					<div class="form-group">
						<textarea  name="description" id="description"
							class="form-control rounded-left" placeholder="Description"
							rows="4" required></textarea>
					</div>

					<div class="form-group">
						<button type="submit" value="sinscrire"
							class="form-control btn btn-primary rounded submit px-3">S’inscrire</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br> <br> <br> <br>
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
									document.write(new Date().getFullYear());
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

<!-- Nice-select, sticky -->
<script src="./assets/js/jquery.nice-select.min.js"></script>
<script src="./assets/js/jquery.sticky.js"></script>
<script src="./assets/js/jquery.magnific-popup.js"></script>

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

