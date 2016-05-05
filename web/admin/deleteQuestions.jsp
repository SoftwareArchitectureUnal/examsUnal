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
        <div class="container">
            <form action="${pageContext.request.contextPath}/DeleteQuestionServlet" id="myForm" data-toggle="validator"  method="post">
                <table class="table table-bordered">
                <thead>
                    <th>Question</th>
                    <th>Category</th>
                    <th>Descripcion</th>
                    <th></th>
                </thead>
               
                <tbody>
                    <c:forEach items="${questions}" var="var">
                        <tr>
                            <td>${var.getQuestionId()}</td>
                            <td>${var.getCategory()}</td>
                            <td>${var.getDescription()}</td>
                            <td>
                                <div class="checkbox">
                                    <label><input type="checkbox" name="${var.getQuestionId()}" value="${var.getQuestionId()}"></label>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button id="addQuestion" class="btn btn-lg btn-primary btn-block" type="submit">Borrar preguntas</button>

            </form>
            
        </div>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
