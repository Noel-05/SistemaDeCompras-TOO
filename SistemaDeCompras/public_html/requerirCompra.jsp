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
                        <li>
                            <a href="generarOrdenCompra.jsp"><i class="zmdi zmdi-file-plus zmdi-hc-fw"></i> Comprar</a>
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
              <h1 class="text-titles"><i class="zmdi zmdi-assignment-o zmdi-hc-fw"></i> <strong>Requisici&oacute;n de Art&iacute;culos.</strong></h1>
            </div>
        </div>
        
<!-- *********************************************************************************************************************************************************** -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                        <li class="active"><a href="#new" data-toggle="tab">Nueva Requisici&oacute;n</a></li>
                        <li><a href="#list" data-toggle="tab">Consultar Requisiciones</a></li>
                    </ul>
                    
                    <div id="myTabContent" class="tab-content">

                        <div class="tab-pane fade active in" id="new">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-xs-12 col-md-10 col-md-offset-1">
                                        
                                        <div class="row">
                                            <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                
                                                <form method="get" action="controladorrequisicion">

                                                    <input type="hidden" name="instruccion" value="filtrarSol">

                                                    <div class="form-group">
                                                        <label class="control-label">Carnet Jefe de Departamento</label>
                                                        <input class="form-control" name="empleado" type="text" required="required" >
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

                                        <div class="row">
                                            <div class="col-xs-12 col-md-10 col-md-offset-1">
                                                <form method="get" action="controladorrequisicion">

                                                    <input type="hidden" name="instruccion" value="insertarBBDD">                
                                                    <div class="form-group">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Nombre del Departamento al que pertenece</label>
                                               
                                                            <select class="form-control" name="departamento" required="required" disabled>
                                                                <c:forEach var="dep" items="${LISTADEPARTAMENTOS}">
                                                                    <option value="${dep.codigoDepartamento}">${dep.nombreDepartamento}</option>
                                                                </c:forEach>
                                                                
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Carnet del Jefe de Departamento</label>
                                               
                                                            <select class="form-control" name="carnet" required="required">
                                                                <c:forEach var="emp" items="${LISTAEMPLEADOS}">
                                                                    <option value="${emp.carnetEmpleado}">${emp.carnetEmpleado}</option>
                                                                </c:forEach>
                                                                
                                                            </select>
                                                        </div>
                                                    </div>  

                                                    <div class="form-group">
                                                        <div class="form-group label-floating">
                                                            <label class="control-label">Numero de Requisicion</label>
                                               
                                                            <select class="form-control" name="numero" required="required">
                                                                <c:forEach var="num" items="${LISTAREQUISICIONES}">
                                                                    <option value="${num.numReq}">${num.numReq}</option>
                                                                </c:forEach>
                                                                
                                                            </select>
                                                        </div>
                                                    </div> 

                                                    </br>
                                                    <label class="control-label">Listado de Solicitudes de Articulos</label>

                                                    <table class="table table-hover text-center">

                                                        <thead>
                                                            <tr>
                                                                <th class="text-center">Carnet</th>
                                                                <th class="text-center">Empleado</th>
                                                                <th class="text-center">C&oacute;digo Art&iacute;culo</th>
                                                                <th class="text-center">Art&iacute;culo</th>
                                                                <th class="text-center">Cantidad</th>
                                                                <th class="text-center">Fecha Solicitud</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="sol" items="${LISTASOLICITUDESART}">
                                                            <tr>
                                                                <td>${sol.carnetEmpleado}</td>
                                                                <td>${sol.nombreEmpleado}</td>
                                                                <td>${sol.codArticulo}</td>
                                                                <td>${sol.nombreArticulo}</td>
                                                                <td>${sol.cantArticulo}</td>
                                                                <td>${sol.fechaSol}</td>
                                                            </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>

                                                    <div class="form-group">
                                                        <label class="control-label">Fecha de Pedido</label>
                                                        <input class="form-control" type="date" name="fechaPedido">
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="control-label">Fecha de Entrega</label>
                                                        <input class="form-control" type="date" name="fechaEntrega">
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="control-label">Articulo</label>
                                                        <select class="form-control" name="articulo" required="required">
                                                            <option>Seleccione el Articulo para la Requisicion...</option>
                                                            <c:forEach var="art" items="${LISTAARTICULOS}">
                                                                <option value="${art.codArticulo}">${art.nombreArticulo}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="form-group label-floating">
                                                        <label class="control-label">Cantidad</label>
                                                        <input class="form-control" type="number" name="cantidad">
                                                    </div>

                                                    <p class="text-center">
                                                        <button href="#!" class="btn btn-info btn-raised btn-lg"><i class="zmdi zmdi-save"></i>Registrar</button>
                                                    </p>
                                                </form>
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>





                        <div class="tab-pane fade" id="list">
                            <div class="table-responsive">

                            <form method="get" action="controladorrequisicion" >
                                <input type="hidden" name="instruccion" value="consultarDepto">

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
                                            <th class="text-center">Departamento</th>
                                            <th class="text-center">Codigo Articulo</th>                                            
                                            <th class="text-center">Articulo</th>    
                                            <th class="text-center">Descripcion</th>                                                
                                            <th class="text-center">Cantidad</th>    
                                            <th class="text-center">Actualizar</th>
                                            <th class="text-center">Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                        <c:forEach var="req" items="${LISTAREQUISICION}">

                                        <!-- Link para Actualizar Solicitud -->
                                        <c:url var="linkActualizar" value="controladorrequisicion">
                                            <c:param name="instruccion" value="recuperar"></c:param>
                                            <c:param name="numReq" value="${req.numReq}"></c:param>
                                        </c:url>

                                        <!-- Link para Eliminar Solicitud -->
                                        <c:url var="linkEliminar" value="controladorrequisicion">
                                            <c:param name="instruccion" value="eliminar"></c:param>
                                            <c:param name="numReq" value="${req.numReq}"></c:param>
                                        </c:url>
                                        <tr>                                        
                                            <td>${req.nombreDepartamento}</td>                                 
                                            <td>${req.codArticulo}</td>
                                            <td>${req.nombreArticulo}</td>
                                            <td>${req.unidadMedida}</td>                                            
                                            <td>${req.cantArt}</td>                                            
                                            <td><a href="${linkActualizar}" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i></a></td>
                                            <td><a href="${linkEliminar}" class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i></a></td>
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