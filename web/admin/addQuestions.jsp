<%-- 
    Document   : index
    Created on : Mar 3, 2016, 4:26:37 PM
    Author     : AndresGutierrez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signUp.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    </head>
    <body>
        
        <c:if test="${user!=null}">
            <%
                request.getRequestDispatcher("/user/index.jsp").forward(request, response);
            %>
        </c:if>
        <c:if test="${admin==null}">
            <%
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            %>
        </c:if>
       
        <%@include file="/WEB-INF/jspf/menu.jspf"%>
        <c:if test="${error!=null}" >
            <div class="alert alert-danger">
                 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Error!</strong> No se pudo crear la pregunta
            </div>
        </c:if>
        <div class="controller">
            <form action="${pageContext.request.contextPath}/AddQuestionServlet" id="myForm" data-toggle="validator" class="form-signin" method="post">
                <h2 class="form-signin-heading">Por favor llene el formulario</h2>
                <label for="inputQuestion" class="sr-only">Nombre de la pregunta</label>
                <input type="text" id="inputQuestion" name="inputQuestion" class="form-control"
                       placeholder="Pregunta" required autofocus>
                <label for="inputCategory" class="sr-only">Eliga la categoria</label>
                <select class="form-control" name="inputCategory">
                    <option value="General">General</option>
                    <option value="Especificas">Especificas</option>
                    <option value="Opinion">Opinion</option>
               </select>
               <label for="inputDescription" class="sr-only">Descripcion de la pregunta</label>
               <textarea class="form-control" id="inputDescription" name="inputDescription" placeholder="Description" required row="5"></textarea> 
               <button id="addQuestion" class="btn btn-lg btn-primary btn-block" type="submit">Add question</button>

            </form>
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
