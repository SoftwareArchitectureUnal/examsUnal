<%-- 
    Document   : index
    Created on : Feb 24, 2016, 7:02:15 PM
    Author     : AndresGutierrez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exámenes</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login/login.css">
    </head>
    
    <body style="background-image: url('${pageContext.request.contextPath}/resources/images/home-bg.jpg'); background-repeat: no-repeat; background-size: 100% 100% 100% 100%">
        <div class="navbar-wrapper">
            <div class="container-fluid">
                <nav class="navbar navbar-inverse navbar-static-top">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                              <span class="sr-only">Toggle navigation</span>
                              <span class="icon-bar"></span>
                              <span class="icon-bar"></span>
                              <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                                Exams
                            </a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <c:if test="${user!=null}">
                                    <li><a href="${pageContext.request.contextPath}/index.jsp">${user.getIdAuthentication()}</a></li>
                                </c:if>
                                <c:if test="${admin!=null}">
                                    <li><a href="${pageContext.request.contextPath}/index.jsp">Admin</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                                <c:choose>
                                    <c:when test="${user!=null}">
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Notifications <span class="badge badge-inverse"><c:out value="${totalNotifications}"/></span> <span class="caret"></span></a>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${notifications}" var="name">
                                                    <li><a href="#"><c:out value="${name.getName()}" /> - <c:out value="${name.getRealizationDate()}"/></a></li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/LogoutServlet">Log out</a></li>
                                    </c:when>
                                   <c:when test="${admin!=null}">
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toogle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Questions</a>
                                            <ul class="dropdown-menu">
                                                <li><a href="${pageContext.request.contextPath}/ShowQuestionServlet">Show questions</a></li>
                                                <li><a href="${pageContext.request.contextPath}/admin/addQuestions.jsp">Add questions</a></li>
                                                <li><a href="${pageContext.request.contextPath}/ShowQuestionDeleteServlet">Delete questions</a></li>
                                            </ul>
                                        </li>
                                            <li><a href="${pageContext.request.contextPath}/LogoutServlet">Log out</a></li>
                                   </c:when>
                                   <c:when test="${user==null && admin==null}">
                                        <li><a href="#modal-signup" data-toggle="modal" data-target="#modal-signup">Sign Up</a></li>
                                        <li><a href="#modal-login" data-toggle="modal" data-target="#modal-login">Login</a></li>
                                    </c:when >                  
                                </c:choose>            
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        
        <div class="modal fade" id="modal-login" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header" style="">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3>Exams</h3>
                        <h6>BIENVENIDO, INGRESA A TU CUENTA</h6>
                    </div>
                    <div class="modal-body">
                        <form class="form-signin" method="post" action="${pageContext.request.contextPath}/LoginServlet">
                            <div class="input-group">
                                <input type="text" id="inputUsername" name="inputUsername" class="form-control input-lg"
                                       placeholder="Nombre de usuario" required autofocus />
                                <div class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-envelope"></span>
                                </div>
                            </div><br/>
                            <div class="input-group">
                                <input type="password" id="inputPassword" name="inputPassword" onchange="checkPassword()" class="form-control input-lg"
                                   placeholder="Contraseña" required />
                                <div class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </div>
                            </div><br/>
                            <button id="btn-login" class="btn btn-lg btn-primary btn-block" type="submit" >Ingresar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <a data-dismiss="modal" data-toggle="modal" href="#modal-signup" data-target="#modal-signup"><h6>REGISTRARME</h6></a>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="modal-signup" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h3>Exams</h3>
                        <h6>BIENVENIDO, REGÍSTRATE</h6>
                    </div>
                    <div class="modal-body">
                        <form  action="${pageContext.request.contextPath}/SignUpServlet" id="myForm" data-toggle="validator" class="form-signin" method="post">
                            <div class="input-group">
                                <input type="text" id="inputUsername" name="inputUsername" class="form-control input-lg"
                                   placeholder="Nombre de usario" required autofocus>
                                <div class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-user"></span>
                                </div>
                            </div><br/>
                            <div class="input-group">
                                <input type="email" id="inputEmail" name="inputEmail" class="form-control input-lg"
                                   placeholder="Direccion de correo" required>
                                <div class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-envelope"></span>
                                </div>
                            </div><br/>
                            <div class="input-group">
                                <input type="text" id="inputName" name="inputName" class="form-control input-lg"
                                   placeholder="Nombre" required>
                                <div class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-user"></span>
                                </div>
                            </div><br/>
                            <div class="input-group">
                                <input type="password" id="inputPassword" name="inputPassword" class="form-control input-lg"
                                   placeholder="Contraseña" required>
                                <div class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </div>
                            </div><br/>
                            <div class="input-group">
                                <input type="password" id="inputRepPassword" name="inputRepPassword" onchange="checkPassword()" class="form-control input-lg"
                                    placeholder="Repita la contraseña" required>
                                <div class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </div>
                            </div><br/>
                            <div class="input-group">
                                <select id="select-password" class="form-control input-lg i" name="gender">
                                    <option value="1">M</option>
                                    <option value="2">F</option>
                                </select>
                                <div id="btn-password" class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-user"></span>
                                </div>
                            </div><br/>
                            <div class="input-group">
                                <select class="form-control input-lg" name="role">
                                    <option value="admin">Admin</option>
                                    <option value="user">User</option>
                                </select>
                                <div id="btn-password" class="input-group-addon" style="background-color: #464D57; color: #fff;">
                                    <span class="glyphicon glyphicon-info-sign"></span>
                                </div>
                            </div><br/>
                            <button id="btn-signUp" class="btn btn-lg btn-primary btn-block" type="submit" disabled="true">Registarme</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
                            
       <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>Exams UN</h1>
                        <h3>Plataforma para inscribirse a exámenes de competencias y consultar resultados</h3>
                        <!--<hr class="intro-divider">-->
                    </div>
                </div>
            </div>
        </div>
        
        
        <!--<h1 style="text-align: center; vertical-align: middle; color: #999;">Exams UN</h1>
        
        <h3 style="text-align: center; color: #999;">Plataforma para inscribirse a exámenes de competencias y consultar resultados.</h3>-->
        <!--
        <br><br><br><br><br>
        <img class="img-responsive center-block" src="${pageContext.request.contextPath}/resources/images/unal.svg.png" >
        --> 
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dropdown.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/signUp.js"></script>
    </body>
</html>
