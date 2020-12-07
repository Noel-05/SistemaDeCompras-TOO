<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="es">
<head>
    <title>Cotizaci&oacute;n Art&iacute;culos</title>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="./static/css/main.css">
</head>
<body>
    <!-- SideBar -->
    <section class="full-box cover dashboard-sideBar">
        <div class="full-box dashboard-sideBar-bg btn-menu-dashboard"></div>

        <div class="full-box dashboard-sideBar-ct">
        
            <!--SideBar Title -->
            <div class="full-box text-uppercase text-center text-titles dashboard-sideBar-title">
                    Sistema de Compras <i class="zmdi zmdi-close btn-menu-dashboard visible-xs"></i>
            </div>
            
            <!-- SideBar User info -->
            <div class="full-box dashboard-sideBar-UserInfo">
                <figure class="full-box">
                    <img src="./static/assets/img/usuario.png" alt="UserIcon">
                    <figcaption class="text-center text-titles">Usuario</figcaption>
                </figure>
                <ul class="full-box list-unstyled text-center">
                    <li>
                        <a href="#!" class="btn-exit-system">
                            <i class="zmdi zmdi-power"></i>
                        </a>
                    </li>
                </ul>
            </div>
            
            <!-- SideBar Menu -->
            <ul class="list-unstyled full-box dashboard-sideBar-Menu">
                <li>
                    <a href="index.jsp">
                        <i class="zmdi zmdi-home zmdi-hc-fw"></i> Inicio
                    </a>
                </li>
                <li>
                    <a href="#!" class="btn-sideBar-SubMenu">
                        <i class="zmdi zmdi-shopping-cart zmdi-hc-fw"></i> Solicitud <i class="zmdi zmdi-caret-down pull-right"></i>
                    </a>
                    <ul class="list-unstyled full-box">
                        <li>
                            <a href="controladorsolicitudarticulo"><i class="zmdi zmdi-shopping-cart-add zmdi-hc-fw"></i>Solicitar Articulo</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#!" class="btn-sideBar-SubMenu">
                        <i class="zmdi zmdi-assignment-o zmdi-hc-fw"></i> Requisici&oacute;n <i class="zmdi zmdi-caret-down pull-right"></i>
                    </a>
                    <ul class="list-unstyled full-box">
                        <li>
                            <a href="controladorrequisicion"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Requerir Compra</a>
                        </li>
                        <li>
                            <a href="controladorautorizarrequisicion"><i class="zmdi zmdi-spellcheck zmdi-hc-fw"></i> Autorizar Requisici&oacute;n</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#!" class="btn-sideBar-SubMenu">
                        <i class="zmdi zmdi-card zmdi-hc-fw"></i> Art&iacute;culo <i class="zmdi zmdi-caret-down pull-right"></i>
                    </a>
                    <ul class="list-unstyled full-box">
                        <li>
                            <a href="controladorcotizararticulo"><i class="zmdi zmdi-money zmdi-hc-fw"></i> Cotizar</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#!" class="btn-sideBar-SubMenu">
                        <i class="zmdi zmdi zmdi-spellcheck zmdi-hc-fw"></i> Gestionar Art&iacute;culo <i class="zmdi zmdi-caret-down pull-right"></i>
                    </a>
                    <ul class="list-unstyled full-box">
                        <li>
                            <a href="gestionarArticulo.jsp"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Ingresar Vigencia</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </section>

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
              <h1 class="text-titles"><i class="zmdi zmdi-card zmdi-hc-fw"></i> <strong>Cotizaci&oacute;n de Art&iacute;culos.</strong></h1>
            </div>
        </div>
        
<!-- *********************************************************************************************************************************************************** -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                        <li class="active"><a href="#new" data-toggle="tab">Cotizar Proveedor Art&iacute;culo</a></li>
                    </ul>
                    
                    <div id="myTabContent" class="tab-content">

                        <div class="tab-pane fade active in" id="new">
                            <div class="container-fluid">
                                <div class="row">
                                    <div >
                
                                        <form action="">
                                            <div class="table-responsive">
                                                <table class="table table-hover text-center">
                                                    <thead>
                                                        <tr>
                                                            <th class="text-center">C&oacute;digo Proveedor</th>
                                                            <th class="text-center">Nombre Proveedor</th>
                                                            <th class="text-center">C&oacute;digo Art&iacute;culo</th>
                                                            <th class="text-center">Nombre Art&iacute;culo</th>
                                                            <th class="text-center">Descuento</th>
                                                            <th class="text-center">Precio</th>
                                                            <th class="text-center">Tiempo Entrega</th>
                                                            <th class="text-center">Per&iacute;odo Gracia</th>
                                                            <th class="text-center">Fecha Desde</th>
                                                            <th class="text-center">Fecha Hasta</th>
                                                            <th class="text-center">Seleccionar</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="vig" items="${LISTAVIGENCIAS}">
                                                            <tr>
                                                                <td>${vig.codProveedor}</td>
                                                                <td>${vig.nombreEmpresa}</td>
                                                                <td>${vig.codArticulo}</td>
                                                                <td>${vig.nombreArticulo}</td>
                                                                <td>${vig.descuento} %</td>
                                                                <td>$ ${vig.precio}</td>
                                                                <td>${vig.tiempoEspera} d&iacute;as</td>
                                                                <td>${vig.perGracia}</td>
                                                                <td>${vig.fechaDesde}</td>
                                                                <td>${vig.fechaHasta}</td>
                                                    
                                                                <td>
                                                                    <form method="get" action="controladorproveedorarticulo" >
                                                                        <input type="hidden" name="instruccion" value="seleccionar">
                                                                        <p class="text-center">
                                                                            <input type="hidden" name="articulo" value="${vig.codArticulo}">
                                                                            <input type="hidden" name="proveedor" value="${vig.codProveedor}">
                                                                            <input type="hidden" name="departamento" value="${vig.codDepartamento}">
                                                                            <input type="hidden" name="cantidad" value="${vig.cantidad}">
                                                                            <input type="hidden" name="precioTotal" value="${vig.precioTotal}">
                                                                            <input type="hidden" name="fechaPedido" value="${vig.fechaPedido}">
                                                                            <button href="controladorproveedorarticulo" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-spellcheck"></i></button>
                                                                        </p>
                                                                    </form>
                                                                </td>
                                                            
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>

                                                </br>
                                        
                                            </div>
                                        </form>

                                        <form method="get" action="controladorcotizararticulo" >
                                        <p class="text-center">
                                            <button href="controladorcotizararticulo" class="btn btn-info btn-raised btn-lg">Volver</button>
                                        </p>
                                        </form>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

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