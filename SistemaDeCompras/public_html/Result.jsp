<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<%
//Verificar el acceso de los uausarios
HttpSession sesion = request.getSession();

    if(sesion.getAttribute("nivel") == null){
        response.sendRedirect("indexLogin.jsp");
    }else{
        String nivel = sesion.getAttribute("nivel").toString();
        if(nivel.equals("1") || nivel.equals("7")){
            
        }else{
            response.sendRedirect("index.jsp");
        }
    }

%>

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
                    <figcaption class="text-center text-titles"><%= sesion.getAttribute("nombre") %></figcaption>
                </figure>
                <ul class="full-box list-unstyled text-center">
                    <li>
                        <a href="indexLogin.jsp?cerrar=true" class="btn-exit-system">
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
                
                <%  String nivel = sesion.getAttribute("nivel").toString();
                if (!nivel.equals("6")){ %>                
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
                <% } %>

                <% if (nivel.equals("1") || nivel.equals("2") || nivel.equals("3") || nivel.equals("7")){ %>
                    <li>
                        <a href="#!" class="btn-sideBar-SubMenu">
                            <i class="zmdi zmdi-assignment-o zmdi-hc-fw"></i> Requisici&oacute;n <i class="zmdi zmdi-caret-down pull-right"></i>
                        </a>
                        <ul class="list-unstyled full-box">
                            <li>
                                <a href="controladorrequisicion"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Requerir Compra</a>
                            </li>
                    <% if (nivel.equals("1") || nivel.equals("2")){ %>
                            <li>
                                <a href="controladorautorizarrequisicion"><i class="zmdi zmdi-spellcheck zmdi-hc-fw"></i> Autorizar Requisici&oacute;n</a>
                            </li>
                    <% } %>
                        </ul>
                    </li>
                <% } %>

                <% if (nivel.equals("1") || nivel.equals("7")){ %>              
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
                <% } %>

                <% if (nivel.equals("1") || nivel.equals("6")){ %>              
                <li>
                    <a href="#!" class="btn-sideBar-SubMenu">
                        <i class="zmdi zmdi zmdi-spellcheck zmdi-hc-fw"></i> Gestionar Art&iacute;culo <i class="zmdi zmdi-caret-down pull-right"></i>
                    </a>
                    <ul class="list-unstyled full-box">
                        <li>
                            <a href="controladorvigencia"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Ingresar Vigencia</a>
                        </li>
                    </ul>
                </li>
                <% } %>

                <% if (nivel.equals("1") || nivel.equals("5")){ %>              
                <li>
                    <a href="#!" class="btn-sideBar-SubMenu">
                        <i class="zmdi zmdi zmdi-collection-plus zmdi-hc-fw"></i> Inventario <i class="zmdi zmdi-caret-down pull-right"></i>
                    </a>
                    <ul class="list-unstyled full-box">
                        <li>
                            <a href="controladormovimiento"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Realizar Movimiento</a>
                        </li>
                    </ul>
                </li>
                <% } %>

                <% if (nivel.equals("1") || nivel.equals("2") || nivel.equals("3") || nivel.equals("7")){ %>              
                <li>
                    <a href="#!" class="btn-sideBar-SubMenu">
                        <i class="zmdi zmdi zmdi-receipt zmdi-hc-fw"></i> Reportes <i class="zmdi zmdi-caret-down pull-right"></i>
                    </a>
                    <ul class="list-unstyled full-box">
                        <li>
                            <a href="controladorreportedepto"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Articulos por Departamento</a>
                        </li>
                        <li>
                            <a href="controladorreporteempleado"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Articulos por Empleado</a>
                        </li>  
                        <li>
                            <a href="controladorreportearticulosprov"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Articulos por Proveedor</a>
                        </li> 
                        <li>
                            <a href="controladorreporteordenesprov"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Ordenes por Proveedor</a>
                        </li> 
                        <li>
                            <a href="controladorexistencias"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Stock Inventario</a>
                        </li>    
                        <li>
                            <a href="controladorstockprecios"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Stock Precios Inventario</a>
                        </li>   
                        <li>
                            <a href="controladorreporteinventario"><i class="zmdi zmdi-collection-item zmdi-hc-fw"></i> Inventario</a>
                        </li>                                                                       
                    </ul>
                </li>  
                <% } %>
                                 
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
              <h1 class="text-titles"><strong>Resultado</strong></h1>
            </div>
            <div class="full-box text-center" style="padding: 30px 10px;">
                    <h3><%= request.getAttribute("Message")%></h3>
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