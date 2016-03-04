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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/signUp.js"></script>

    </head>
    <body>
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        <c:if test="${signUp!=null}" >
            <div class="alert alert-danger">
                <strong>Error!</strong> No se pudo hacer el registro
            </div>
        </c:if>
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
        <div class="container">
            <form  action="${pageContext.request.contextPath}/SignUpServlet" id="myForm" data-toggle="validator" class="form-signin" method="post">
                <h2 class="form-signin-heading">Por favor llene el formulario</h2>
                <label for="inputUsername" class="sr-only">Nombre de Usario</label>
                <input type="text" id="inputUsername" name="inputUsername" class="form-control"
                       placeholder="Nombre de usario" required autofocus>
                <label for="inputEmail" class="sr-only">Correo Electronico</label>
                <input type="email" id="inputEmail" name="inputEmail" class="form-control"
                       placeholder="Direccion de correo" required>
                <label for="inputName" class="sr-only">Name</label>
                <input type="text" id="inputName" name="inputName" class="form-control"
                       placeholder="Nombre" required>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="inputPassword" onchange="checkPassword()" class="form-control"
                       placeholder="Contraseña" required>
                <label for="inputPassword" class="sr-only">Repeat Password></label>
                <input type="password" id="inputRepPassword" name="inputRepPassword" onchange="checkPassword()" class="form-control"
                       placeholder="Repita la contraseña" required>
               <select class="form-control" name="gender">
                    <option value="1">M</option>
                    <option value="2">F</option>
                
               </select>
                <select class="form-control" name="role">
                    <option value="admin">Admin</option>
                    <option value="user">User</option>
                
               </select>
                <button id="signUp" class="btn btn-lg btn-primary btn-block" type="submit" disabled="true">Registarse</button>
                 
                
            </form>
            
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
