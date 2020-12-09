<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<html lang="es">
<head>
    <title>Inicio</title>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="./static/css/main.css">
</head>
<body>
    <!-- SideBar -->
   

    <!-- Content page-->
    <section class="full-box dashboard-contentPage">
        <!-- NavBar -->
        <nav class="full-box dashboard-Navbar">
            <ul class="full-box list-unstyled text-right">
                <li class="pull-left">
                    <a href="#!" class="btn-menu-dashboard"><i class="zmdi zmdi-more-vert"></i></a>
                </li>
            </ul>
        </nav>

        <!-- Content page -->
        <div class="container-fluid">
            <div class="page-header">
              <h1 class="text-titles"><strong>Sistema de Compras.</strong></h1>
            </div>
            <div class="full-box text-center" style="padding: 30px 10px;">
            <form action="loginservlet" method="POST">
            Usuario:<input type="text" name="userName">
            Contrase&ntilde;a:<input type="text" name="passW">
            <br>${message}<br><br>
            <input type="submit" value="Ïniciar">
            </form>
            </div>
        </div>
        
<!-- *********************************************************************************************************************************************************** -->

<!-- *********************************************************************************************************************************************************** -->
            
        </section>
		
	<!--====== Scripts -->
	<script src="./static/js/jquery-3.1.1.min.js"></script>
	<script src="./static/js/sweetalert2.min.js"></script>
	<script src="./static/js/bootstrap.min.js"></script>
	<script src="./static/js/material.min.js"></script>
	<script src="./static/js/ripples.min.js"></script>
	<script src="./static/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="./static/js/main.js"></script>
	<script>
		$.material.init();
	</script>
</body>
</html>