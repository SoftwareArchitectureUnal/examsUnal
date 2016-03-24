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
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    </head>
    <body>
        
        <%@include file="/WEB-INF/jspf/menu.jspf"%>
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
        <!--
        <br><br><br><br><br>
        <img class="img-responsive center-block" src="${pageContext.request.contextPath}/resources/images/unal.svg.png" >
        --> 
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
