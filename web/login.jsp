<%-- 
    Document   : index
    Created on : Feb 24, 2016, 7:02:15 PM
    Author     : AndresGutierrez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exams</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signUp.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    </head>
    <body>
        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        <c:choose>
            <c:when test="${user!=null}">
                <%
                    request.getRequestDispatcher("/user/index.jsp").forward(request, response);
                %>
            </c:when>
            <c:when test="${admin!=null}">
                <%
                    request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
                %>
            </c:when>
        </c:choose>
        <c:if test="${login!=null}" >
            <div class="alert alert-danger">
                 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Error!</strong> No se pudo hacer el login
            </div>
        </c:if>
        <div class="container">
            <form class="form-signin" method="post" action="${pageContext.request.contextPath}/LoginServlet">
                <h2 class="form-signin-heading">Por favor hacer el login</h2>
                <label for="inputUsername" class="sr-only" >Nombre de usuario</label>
                <input type="text" id="inputUsername" name="inputUsername" class="form-control"
                       placeholder="Nombre de usario" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="inputPassword" onchange="checkPassword()" class="form-control"
                       placeholder="Contraseña" required>
                <button id="signUp" class="btn btn-lg btn-primary btn-block" type="submit" >Inicio de session</button>

            </form>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
