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
                        <li class="active"><a href="#new" data-toggle="tab">Cotizar Art&iacute;culo</a></li>
                        <li><a href="#list" data-toggle="tab">Detalle Cotizaci&oacute;n</a></li>
                        <li><a href="#list1" data-toggle="tab">Enviar orden</a></li>
                    </ul>
                    
                    <div id="myTabContent" class="tab-content">

                        <div class="tab-pane fade active in" id="new">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-xs-12 col-md-10 col-md-offset-1">
                                        
                                        <div class="row">
                                            <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                
                                                <form method="get" action="controladorcotizararticulo" >
                                                    <input type="hidden" name="instruccion" value="filtrar">
                                                    
                                                    <div class="form-group">
                                                        <label class="control-label">Departamento</label>
                                                        <select class="form-control" name="departamento" required="required">
                                                        
                                                            <option value=" ">Seleccione el Departamento a Consultar...</option>
                                                            
                                                            <c:forEach var="dep" items="${LISTADEPARTAMENTOS}">
                                                                <option value="${dep.codigoDepartamento}">${dep.nombreDepartamento}</option>
                                                            </c:forEach>
                                                            
                                                        </select>
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label class="control-label">Fecha de Filtro</label>
                                                        <input class="form-control" name="fecha" type="date" required="required">
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
                                                    <table class="table table-hover text-center">
                                                        
                                                    
                                                        <thead>
                                                            <tr>
                                                                <th class="text-center">Departamento</th>
                                                                <th class="text-center">C&oacute;digo Art&iacute;culo</th>
                                                                <th class="text-center">Nombre Art&iacute;culo</th>
                                                                <th class="text-center">Cantidad</th>
                                                                <th class="text-center">Fecha Pedido</th>
                                                                <th class="text-center">Cotizar</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            
                                                            <c:forEach var="req" items="${LISTAREQUISICIONES}">
                                                            <tr>
                                                                <td>${req.nombreDepartamento}</td>
                                                                <td>${req.codArticulo}</td>
                                                                <td>${req.nombreArticulo}</td>
                                                                <td>${req.cantArt}</td>
                                                                <td>${req.fechaPedidoReq}</td>
                                                    
                                                                <td>
                                                                    <form method="get" action="controladorproveedorarticulo" >
                                                                    <input type="hidden" name="instruccion" value="cotizar">
                                                                        <p class="text-center">
                                                                            <input type="hidden" name="articulo" value="${req.codArticulo}">
                                                                            <input type="hidden" name="fecha" value="${req.fechaPedidoReq}">
                                                                            <input type="hidden" name="cantidad" value="${req.cantArt}">
                                                                            <input type="hidden" name="departamento" value="${req.codigoDepartamento}">
                                                                            <button href="controladorproveedorarticulo" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-assignment-o" ></i></button>
                                                                        </p>
                                                                    </form>
                                                                </td>
                                                            
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





                        <div class="tab-pane fade" id="list">
                            <div class="table-responsive">
                                
                                <form method="get" action="controladorcotizararticulo" >
                                    <input type="hidden" name="instruccion" value="detalle">
                                
                                    <div class="row">
                                        <div class="col-xs-12 col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                            
                                            
                                                <label class="control-label">Departamento</label>
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
                                        <button href="controladorcotizararticulo" class="btn btn-info btn-raised btn-lg"><i class="zmdi zmdi-save"></i>  Consultar</button>
                                    </p>
                                    
                                </form>
                                
                                </br></br>
                                <table class="table table-hover text-center">
                                    <thead>
                                        <tr>
                                            <th class="text-center">C&oacute;digo Proveedor</th>
                                            <th class="text-center">Nombre Proveedor</th>
                                            <th class="text-center">C&oacute;digo Art&iacute;culo</th>
                                            <th class="text-center">Nombre Art&iacute;culo</th>
                                            <th class="text-center">Cantidad</th>
                                            <th class="text-center">Descuento</th>
                                            <th class="text-center">Precio Unitario</th>
                                            <th class="text-center">Per&iacute;odo Gracia</th>
                                            <th class="text-center">Tiempo de Entrega</th>
                                            <th class="text-center">Precio Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="ord" items="${LISTAORDENES}">
                                            <tr>
                                                <td>${ord.codProveedor}</td>
                                                <td>${ord.nombreProv}</td>
                                                <td>${ord.codArticulo}</td>
                                                <td>${ord.nombreArt}</td>
                                                <td>${ord.cantArt}</td>
                                                <td>${ord.descuento} %</td>
                                                <td>$ ${ord.precio}</td>
                                                <td>${ord.perGra}</td>
                                                <td>${ord. entregaInmediata} d&iacute;as</td>
                                                <td>$ ${ord.precioTotal}</td>
                                            </tr>
                                            </c:forEach>
                            
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        
                        
                        
                        <div class="tab-pane fade" id="list1">
                            <div class="table-responsive">
                                
                                <form method="get" action="controladorcotizararticulo" >
                                    <input type="hidden" name="instruccion" value="enviar">
                                
                                    <div class="row">
                                        <div class="col-xs-12 col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                            
                                            
                                                <label class="control-label">Departamento</label>
                                                <select class="form-control" name="departamento" required="required">
                                                
                                                    <option>Seleccione el Departamento a Consultar...</option>
                                                
                                                    <c:forEach var="dep" items="${LISTADEPARTAMENTOS}">
                                                        <option value="${dep.codigoDepartamento}">${dep.nombreDepartamento}</option>
                                                    </c:forEach>
                                                    
                                                </select>
                                                        <label class="control-label">Fecha de Filtro</label>
                                                        <input class="form-control" name="fecha2" type="date" required="required">
                                            </div>
                                        </div>
                                    </div>
                                
                                    <p class="text-center">
                                        <button href="controladorcotizararticulo" class="btn btn-info btn-raised btn-lg"><i class="zmdi zmdi-save"></i>  Consultar</button>
                                    </p>
                                    
                                </form>
                                
                                </br></br>

                                <table class="table table-hover text-center">
                                    <thead>
                                        <tr>
                                            
                                            <th class="text-center">C&oacute;digo Art&iacute;culo</th>
                                            <th class="text-center">Nombre Art&iacute;culo</th>
                                            <th class="text-center">Cantidad</th>
                                            <th class="text-center">Nombre Proveedor</th>
                                            <th class="text-center">Precio Total</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                        <c:forEach var="ord2" items="${LISTAORDENES2}">
                                            <tr>
                                                <td>${ord2.codArticulo}</td>
                                                <td>${ord2.nombreArt}</td>
                                                <td>${ord2.cantArt}</td>
                                                <td>${ord2.nombreProv}</td>
                                                <td>$ ${ord2.precioTotal}</td>
                                                
                                                <td>
                                                    <form method="post" action="EmailSendingServlet" >
                                                        <p class="text-center">
                                                            <input type="hidden" name="recipient" value="chavez98victor@gmail.com"/>
                                                            <input type="hidden" name="subject" value="Orden de Compra"/>
                                                            <input type="hidden" name="codarticulo" value="${ord2.codArticulo}">
                                                            <input type="hidden" name="nomarticulo" value="${ord2.nombreArt}">
                                                            <input type="hidden" name="cantidad" value="${ord2.cantArt}">
                                                            <input type="hidden" name="preciototal" value="${ord2.precioTotal}">
                                                            <input type="submit" class="btn btn-success btn-raised btn-xs">
                                                        </p>
                                                    </form>
                                                </td>
                                            
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