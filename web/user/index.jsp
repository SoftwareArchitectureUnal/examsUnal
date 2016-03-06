<%-- 
    Document   : index
    Created on : Mar 3, 2016, 4:26:53 PM
    Author     : AndresGutierrez
--%>

<%@page import="co.unal.examsUnal.BusinessLogic.Controller.User.CertificationController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.unal.examsUnal.BusinessLogic.Controller.User.ExamRegisterController"%>
<%@page import="co.unal.examsUnal.DataAccess.Entity.User"%>
<%@page import="co.unal.examsUnal.DataAccess.Entity.Exam"%>
<%@page import="java.util.Date"%>
<%@page import="javafx.util.Pair"%>
<%@page import="java.util.Collection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    </head>
    <body>
        <c:if test="${admin!=null}">
            <%
                request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
            %>
        </c:if>
        <c:if test="${user==null}">
            <%
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            %>
        </c:if>
        
         <%@include file="/WEB-INF/jspf/menu.jspf"%>
         <form action="./RegisterExamServlet" method="post">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Examenes</div>
                <!-- Table -->
                <table class="table">
                    <%
                    Collection<Pair<Exam, Boolean>> lista = ExamRegisterController.ExamsUser(((User)session.getAttribute("user")).getUserId());
                    Boolean flag;
                    Exam exam;
                    out.println("<tr>");
                        out.println("<th>Nombre             </th>");
                        out.println("<th>Descripción        </th>");
                        out.println("<th>Fecha           </th>");
                        out.println("<th>Inscribir           </th>");
                        out.println("</tr>");
                    Date auxDate;
                    for( Pair myPair : lista)
                    {
                        
                        exam = (Exam)myPair.getKey();
                        auxDate = exam.getRealizationDate();
                        if ( auxDate.compareTo(new Date()) == -1) continue;
                        flag = (Boolean)myPair.getValue();
                        out.println("<tr>");
                        out.println("<td>"+exam.getName()+"               "+"</td>");
                        out.println("<td>"+exam.getDescription()+"              "+"</td>");
                        out.println("<td>"+exam.getRealizationDate()+"             "+"</td>");
                        if ( flag )
                        {
                            out.println("<td> <input type=\"checkbox\" name=\"checkb"+exam.getExamId()+"\"  checked>          </td>"); 
                        }
                        else
                        {
                            out.println("<td> <input type=\"checkbox\" name=\"checkb"+exam.getExamId()+"\" >          </td>");
                        }
                        out.println("</tr>");
                    }
                    %>
                </table>
            </div>
            <button type = "submit" class = "btn btn-success" name = "btsave">Guardar</button>
        </form>   
        
        <br><br><br>   
        <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Certificados</div>
                <!-- Table -->
                <table class="table">        
                <% User myUser = (User)session.getAttribute("user"); 
                    ArrayList<Exam> myExams = (ArrayList)CertificationController.getPasExamsofUser(myUser.getUserId());
                    out.print("<tr>");
                        out.print("<th>Exámenes</th>");
                    out.print("</tr>");
                    for( int i = 0; i < myExams.size(); i++ )
                    {
                       out.print("<tr>");
                       out.print("<td>"+(myExams.get(i).getName())+"</th>");
                       out.print("<td> <form action=\"./CertificationServlet\" method=\"post\"> "
                               + "<input type=\"hidden\" name=\"nameExam\" value=\""+myExams.get(i).getName()+"\">"
                               + "<button type = \"submit\" class = \"btn btn-success\" name=\"bt\" >Certificado</button>"
                               + "</form> </th>");
                       out.print("</tr>");
                    }
                %>
                
                
                </table>
        </div>        
        <%@include file="/WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
