<%-- 
    Document   : index
    Created on : Mar 3, 2016, 4:26:53 PM
    Author     : AndresGutierrez
--%>

<%@page import="co.unal.examsUnal.Utilities.Util.Pair"%>
<%@page import="co.unal.examsUnal.BusinessLogic.Controller.Report.CertificationController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.unal.examsUnal.BusinessLogic.Controller.Management.ExamRegisterController"%>
<%@page import="co.unal.examsUnal.DataAccess.Entity.User"%>
<%@page import="co.unal.examsUnal.DataAccess.Entity.Exam"%>
<%@page import="java.util.Date"%>

<%@page import="java.util.Collection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exams</title>
        <link rel="icon" type="image/jpg" href="${pageContext.request.contextPath}/resources/images/favicon.jpg" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal/modal.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dataTables/css/dataTables.bootstrap.min.css" type="text/css">
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
         
        <div class="col-md-10 col-md-offset-1">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#exam-register" id="tab-exam-register">Incribir exámenes</a></li>
                <li><a data-toggle="tab" href="#show-certified" id="tab-show-certified">Certificados</a></li>         
            </ul>
            <div class="tab-content">
                <div id="exam-register" class="tab-pane fade in active">
                    <form action="./RegisterExamServlet" method="post">
                        <div class="panel panel-default" style="padding: 12px;">
                        <!-- Default panel contents -->
                            <div class="panel-heading">Examenes</div><br/><br/>
                            <!-- Table -->
                            <table id="table-exams-user" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                                <%
                                    ExamRegisterController myExamRC = new ExamRegisterController();
                                    Collection<Pair<Exam, Boolean>> lista = myExamRC.ExamsUser(((User)session.getAttribute("user")).getIdAuthentication());
                                    Boolean flag;
                                    Exam exam;
                                    out.println("<thead>");
                                        out.print("<tr>");
                                            out.print("<th>Exámenes</th>");
                                        out.print("</tr>");
                                        out.println("<tr>");
                                            out.println("<th style='text-align: center;'>Nombre</th>");
                                            out.println("<th style='text-align: center;'>Descripción</th>");
                                            out.println("<th style='text-align: center;'>Fecha</th>");
                                            out.println("<th style='text-align: center;'>Inscribir</th>");
                                        out.println("</tr>");
                                    out.println("</thead>");
                                    out.println("<tfoot>");
                                        out.println("<tr>");
                                            out.println("<th style='text-align: center;'>Nombre</th>");
                                            out.println("<th style='text-align: center;'>Certificado</th>");
                                        out.println("</tr>");
                                    out.println("</tfoot>");
                                    out.println("<tbody>");
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
                                        out.println("<td>"+exam.getRealizationDate().getDate()+"/"+exam.getRealizationDate().getMonth()+"/"+(exam.getRealizationDate().getYear()+1900)+"             "+"</td>");
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
                                    out.println("</tbody>");
                                %>
                            </table>
                        </div>    
                        <button type = "submit" class = "btn btn-primary" name = "btsave">Guardar</button>
                    </form>  
                </div>
                <div id="show-certified" class="tab-pane fade">
                    <div class="panel panel-default" style="padding: 12px;">
                    <!-- Default panel contents -->
                        <div class="panel-heading">Certificados</div><br/><br/>
                        <!-- Table -->
                        <table id="table-certificates" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                            <% 
                                User myUser = (User)session.getAttribute("user"); 
                                ArrayList<Exam> myExams = (ArrayList)CertificationController.getPasExamsofUser(myUser.getIdAuthentication());
                                out.println("<thead>");
                                    out.print("<tr>");
                                        out.print("<th>Exámenes</th>");
                                    out.print("</tr>");
                                    out.println("<tr>");
                                        out.println("<th style='text-align: center;'>Nombre</th>");
                                        out.println("<th style='text-align: center;'>Certificado</th>");
                                    out.println("</tr>");
                                out.println("</thead>");
                                out.println("<tfoot>");
                                    out.println("<tr>");
                                        out.println("<th style='text-align: center;'>Nombre</th>");
                                        out.println("<th style='text-align: center;'>Certificado</th>");
                                    out.println("</tr>");
                                out.println("</tfoot>");
                                out.println("<tbody>");
                                    for( int i = 0; i < myExams.size(); i++ ){
                                       out.print("<tr>");
                                       out.print("<td>"+(myExams.get(i).getName())+"</td>");
                                       out.print("<td style='text-align: center;'> <form action=\"./CertificationServlet\" method=\"post\" target=\"_blank\"> "
                                               + "<input type=\"hidden\" name=\"nameExam\" value=\""+myExams.get(i).getName()+"\">"
                                               + "<button type = \"submit\" class = \"btn btn-primary\" name=\"bt\" >Certificado</button>"
                                               + "</form> </td>");
                                       out.print("</tr>");
                                    }
                                out.println("</tbody>");
                            %>
                        </table>
                    </div>  
                </div>
                <div class="col-md-12"><br/><br/><br/><br/></div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/resources/js/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/dataTables/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/dataTables/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/user/user.js" type="text/javascript"></script>
    </body>
</html>