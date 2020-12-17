<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<!-- Include the above in your HEAD tag -->
<!DOCTYPE html>a
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page session="true" %>

<html lang="es">
<head>
    <title>Inicio</title>

    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="./static/css/style_login.css">

</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header">
                    <h4>Login</h4>
                </div>
                <div class="card-body">
                     <form action="controladoracceso" method="POST">
                        <div class="input-group form-group">

                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                            </div>
                            <input type="text" name="txtusuario" class="form-control" placeholder="username">                            
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-key"></i></span>
                            </div>
                            <input type="password" name="txtcontra" class="form-control" placeholder="password">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="btnIniciar" value="Iniciar Sesion" class="btn float-right login_btn">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div> 

    <%
    //Creamos sesion con nombre y nivel y redireccionamos al index
    HttpSession sesion = request.getSession();
    int nivel = 0;
    if(request.getAttribute("nivel") != null){
        nivel = (Integer)request.getAttribute("nivel");
        if(nivel==1 || nivel==2 || nivel==3 || nivel==4 || nivel==5 || nivel==6 || nivel==7){
            sesion.setAttribute("nombre", request.getAttribute("nombre"));
            sesion.setAttribute("nivel", nivel);
            response.sendRedirect("index.jsp");
        }
    }
    //Pasamos valor de jsp a jsp en este caso seria el valor a la hora de cerrar la sesion y la invalidamos
    if(request.getParameter("cerrar") != null){
        session.invalidate();
    }


        %>

</body>
</html>