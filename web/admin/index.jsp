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
        <%@include file="/WEB-INF/jspf/footer.jspf"%>

    </body>
</html>
