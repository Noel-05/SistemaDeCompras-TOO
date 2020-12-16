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
        if(nivel.equals("1") || nivel.equals("2")){
            
        }else{
            response.sendRedirect("index.jsp");
        }
    }

%>

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
              <h1 class="text-titles"><i class="zmdi zmdi-assignment-o zmdi-hc-fw"></i> <strong>Autorizar Requisici&oacute;n de Art&iacute;culos.</strong></h1>
            </div>
        </div>
        
<!-- *********************************************************************************************************************************************************** -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                        <li class="active"><a href="#new" data-toggle="tab">Autorizar Requisici&oacute;n</a></li>
                        <li><a href="#list" data-toggle="tab">Requisiciones Autorizadas</a></li>
                    </ul>
                    
                    <div id="myTabContent" class="tab-content">

                        <div class="tab-pane fade active in" id="new">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-xs-12 col-md-10 col-md-offset-1">
                                        
                                        <div class="row">
                                            <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                
                                                <form action="controladorautorizarrequisicion">
                                                
                                                    <input type="hidden" name="instruccion" value="filtrar">
                                                    
                                                    <div class="form-group label-floating">
                                                        <label class="control-label">Carnet</label>
                                                        <input class="form-control" type="text" name="carnet" required="required">
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label class="control-label">Fecha de Filtro</label>
                                                        <input class="form-control" type="date" name="fecha" required="required">
                                                    </div>
                                                    
                                                    <p class="text-center">
                                                        <button href="#!" class="btn btn-info btn-raised btn-lg">Comenzar</button>
                                                    </p>
                                                    
                                                </form>
                                                
                                            </div>
                                        </div>

                                        </br>

                                        <div class="row">
                                            <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                <div class="form-group">
                                                    <table name="tabla" class="table table-hover text-center">
                                                        <thead>
                                                            <tr>
                                                                <th class="text-center">Carnet</th>
                                                                <th class="text-center">Nombre</th>
                                                                <th class="text-center">Departamento</th>
                                                                <th class="text-center">Fecha Pedido</th>
                                                                <th class="text-center">Fecha Entrega</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>                                                                                                                
                                                            <c:forEach var="aut" items="${LISTAREQUISICIONESNOAUTORIZADAS}">
                                                                <tr>                                                                   
                                                                    <td>${aut.carnetEmpleado}</td>
                                                                    <td>${aut.nombreEmpleado}&nbsp;${aut.apellidoEmpleado}</td>
                                                                    <td>${aut.nombreDepartamento}</td>
                                                                    <td>${aut.fechaPedidoReq}</td>
                                                                    <td>${aut.fechaEntregaReq}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <form action="controladorautorizarrequisicion">
                                        
                                            <input type="hidden" name="instruccion" value="autorizar">                                        

                                            <div class="form-group">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Carnet del Jefe de Departamento</label>
                                       
                                                    <select class="form-control" name="carnetJ" required="required">
                                                        <c:forEach var="emp" items="${LISTAEMPLEADOS}">
                                                            <option value="${emp.carnetEmpleado}">${emp.carnetEmpleado}</option>
                                                        </c:forEach>
                                                        
                                                    </select>
                                                </div>
                                            </div>  

                                            <div class="form-group">
                                                <label class="control-label">Introduzca desde que fecha de Pedido desea hacer la requisicion</label>
                                                <input class="form-control" type="date" name="fecha1" required="required">
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label">Estado</label>
                                                <select class="form-control" name="estadoReq" required="required">
                                                    <option>Seleccione el Estado de la Requisicion...</option>
                                                    <option value="denegado">Denegado</option>
                                                    <option value="aceptado">Aceptado</option>
                                                </select>
                                            </div>
                                            <p class="text-center">
                                                <button href="#!" class="btn btn-info btn-raised btn-lg">Guardar</button>
                                            </p>
                                            
                                        </form>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>





                        <div class="tab-pane fade" id="list">
                            <div class="table-responsive">

                            <form method="get" action="controladorautorizarrequisicion" >
                                <input type="hidden" name="instruccion" value="filtrarDepto">

                                <div class="row">
                                    <div class="col-xs-12 col-md-10 col-md-offset-1">
                                        <div class="form-group">
                                            <label class="control-label"><strong>Departamento</strong></label>
                                            <select class="form-control" name="departamento" required="required">
                                                <option>Seleccione el Departamento a Consultar...</option>

                                                <c:forEach var="dep" items="${LISTADEPARTAMENTOS}">
                                                    <option value="${dep.codigoDepartamento}">${dep.nombreDepartamento}</option>
                                                </c:forEach>
                                                
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <p class="text-center">
                                    <button href="#list" class="btn btn-info btn-raised btn-lg"><i class="zmdi zmdi-save"></i>  Consultar</button>
                                </p>
                            </form>
                                
                                </br></br>
                                <table class="table table-hover text-center">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Carnet</th>
                                            <th class="text-center">Nombre</th>
                                            <th class="text-center">Departamento</th>
                                            <th class="text-center">Fecha Pedido</th>
                                            <th class="text-center">Fecha Entrega</th>
                                            <th class="text-center">Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="aut" items="${LISTAREQUISICIONESAUTORIZADAS}">
                                            <tr>
                                                <td>${aut.carnetEmpleado}</td>
                                                <td>${aut.nombreEmpleado}&nbsp;${aut.apellidoEmpleado}</td>
                                                <td>${aut.nombreDepartamento}</td>
                                                <td>${aut.fechaPedidoReq}</td>
                                                <td>${aut.fechaEntregaReq}</td>                                               
                                                <c:choose>
                                                    <c:when test="${aut.autorizado==0}">
                                                        <td>Autorizado</td>
                                                    </c:when>
                                                    <c:when test="${aut.autorizado==1}">
                                                        <td>Sin Autorizar</td>
                                                    </c:when>
                                                </c:choose>
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