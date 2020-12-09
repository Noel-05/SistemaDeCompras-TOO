<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="es">
<head>
    <title>Requisiciones Art&iacute;culos</title>
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
              <h1 class="text-titles"><i class="zmdi zmdi-assignment-o zmdi-hc-fw"></i> <strong>Reporte de Precios Vigentes de Articulos por Proveedor</strong></h1>
            </div>
        </div>
        
<!-- *********************************************************************************************************************************************************** -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                        <li><a href="#list" data-toggle="tab">Reporte</a></li>
                    </ul>
                    
                    <div id="myTabContent" class="tab-content">

                        <div class="tab-pane fade active in" id="new">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-xs-12 col-md-10 col-md-offset-1">
                                        
                                        <div class="row">
                                            <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                
                                                <form method="get" action="controladorreportearticulosprov">

                                                    <input type="hidden" name="instruccion" value="consulta">

                                                    <div class="form-group">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Seleccione el Proveedor a consultar</label>
                                               
                                                            <select class="form-control" name="proveedor" required="required">
                                                                <option>Seleccione el Proveedor a Consultar...</option>
                                                                <c:forEach var="prov" items="${LISTAPROVEEDORES}">
                                                                    <option value="${prov.codigoProv}">${prov.nomEmpresa}</option>
                                                                </c:forEach>
                                                                
                                                            </select>
                                                        </div>
                                                    </div>                                                    
                                                    <p class="text-center">
                                                        <button href="#!" class="btn btn-info btn-raised btn-lg">Comenzar</button>
                                                    </p>
                                                </form>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                <div class="form-group">
                                                    <div class="form-group label-floating">
                                                        <label class="control-label">Proveedor Consultado</label>
                                           
                                                        <select class="form-control" name="proveedor" required="required" disabled>
                                                            <c:forEach var="prov" items="${LISTAPROVEEDORES1}">
                                                                <option value="${prov.codigoProv}">${prov.codigoProv} - ${prov.nomEmpresa}</option>
                                                            </c:forEach>
                                                            
                                                        </select>
                                                    </div>
                                                </div>

                                                </br>

                                                <table class="table table-hover text-center">

                                                    <thead>
                                                        <tr>

                                                            <th class="text-center">Codigo Articulo</th>
                                                            <th class="text-center">Nombre Articulo</th>
                                                            <th class="text-center">Fecha Desde</th>
                                                            <th class="text-center">Fecha Hasta</th>
                                                            <th class="text-center">Descuento (%)</th>
                                                            <th class="text-center">Precio ($)</th>
                                                            <th class="text-center">Tiempo de Entrega (Dias)</th>
                                                            <th class="text-center">Periodo de Gracia</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="vig" items="${LISTAVIGENCIAS}">
                                                        <tr>
                                                            <td>${vig.codArticulo}</td>
                                                            <td>${vig.nombreArticulo}</td>
                                                            <td>${vig.fechaDesde}</td>
                                                            <td>${vig.fechaHasta}</td>
                                                            <td>${vig.descuento}</td>
                                                            <td>${vig.precio}</td>
                                                            <td>${vig.tiempoEspera}</td>
                                                            <td>${vig.perGracia}</td>
                                                        </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        
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