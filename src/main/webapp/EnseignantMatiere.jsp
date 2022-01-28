<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.aji_t9ra.Models.Matiere"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@page import="java.util.*"%>


<%
List<Matiere> matieres = (List<Matiere>) session.getAttribute("matieres");
%>
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
				<h3 class="text-center mb-4">Inscrivez-vous : Compte Enseignant</h3>
				<form action="Enseignant?op=addenseignant" method="post"
					onsubmit="return handleData()" class="login-form"
					style="text-align: center;">



					<div class="p-2">
					Choisissez votre matiére :
						<div style="visibility: hidden; color: red;font-size: 12px;" id="chk_option_error">						
						Choisissez en moins une matiére </div>

						<%
						for (Matiere e : matieres) {
						%>
						<label class="container"><%=e.getNom() %> <input type="checkbox" name="matiere[]" value="<%=e.getNom()%>">
							<span class="checkmark"></span>
						</label>
		
						<%
						}
						%>

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
<script> 
function handleData()
{
    var form_data = new FormData(document.querySelector("form"));
    if(!form_data.has("matiere[]"))
    {
        document.getElementById("chk_option_error").style.visibility = "visible";
      return false;      
    }
    else
    {
        document.getElementById("chk_option_error").style.visibility = "hidden";
      return true;
    }

}
</script>

<!-- Jquery Plugins, main Jquery -->
<script src="./assets/js/plugins.js"></script>
<script src="./assets/js/main.js"></script>
<style>
.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
  background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}

</style>
</body>
</html>

