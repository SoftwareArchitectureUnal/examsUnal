<%-- 
    Document   : gradeExams
    Created on : 05-may-2016, 9:51:21
    Author     : alej0
--%>

<%@page import="co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto.Status"%>
<%@page import="co.unal.examsUnal.Utilities.Util.ExamUser"%>
<%@page import="co.unal.examsUnal.Utilities.Util.UserResult"%>
<%@page import="co.unal.examsUnal.DataAccess.Entity.Exam"%>
<%@page import="co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calificar</title>
        <link rel="icon" type="image/jpg" href="${pageContext.request.contextPath}/resources/images/favicon.jpg" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dataTables/css/dataTables.bootstrap.min.css" type="text/css">
        <style type="text/css">
            tr.group,
            tr.group:hover {
                background-color: #ddd !important;
            }
        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h1 class="text-primary">Exámenes</h1><br/>
                    <table id="table-grades" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                        <%
                            ExamController examController = new ExamController();
                            Collection<UserResult> userResults = examController.getUsersResults();
                            out.println("<thead>");
                                out.println("<tr>");
                                    out.println("<th style='text-align: center;'>Nombre</th>");
                                    out.println("<th style='text-align: center;'>Examen</th>");
                                    out.println("<th style='text-align: center;'>Descripción</th>");
                                    out.println("<th style='text-align: center;'>Presentado</th>");
                                    out.println("<th style='text-align: center;'>Aprobado</th>");
                                out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tfoot>");
                                out.println("<tr>");
                                    out.println("<th style='text-align: center;'>Nombre</th>");
                                    out.println("<th style='text-align: center;'>Examen</th>");
                                    out.println("<th style='text-align: center;'>Descripción</th>");
                                    out.println("<th style='text-align: center;'>Presentado</th>");
                                    out.println("<th style='text-align: center;'>Aprobado</th>");
                                out.println("</tr>");
                            out.println("</tfoot>");
                            out.println("<tbody>");
                            for( UserResult userResult : userResults){
                                /*out.println("<tr class=\"group\" >");
                                    out.println("<td colspan=\"4\">" + userResult.getUser().getName() + " CC: " + userResult.getUser().getDocument() + "</td>");
                                out.println("</tr>");*/
                                for( ExamUser examUser : userResult.getExamsUser() ){
                                    out.println("<tr>");
                                        out.println("<td>"+userResult.getUser().getName()+"</td>");
                                        out.println("<td>"+examUser.getExam().getName()+"</td>");
                                        out.println("<td>"+examUser.getExam().getDescription()+"</td>");
                                        int presentedValue = examUser.getStatus().equals(Status.PENDING) ? 0 : 1;
                                        int gradedValue = examUser.getStatus().equals(Status.PASS) ? 0 : 1;
                                        if(presentedValue == 0){
                                            out.println("<td style='text-align: center;'><input class='check-presented' type='checkbox' id='"+userResult.getUser().getIdAuthentication()+'-'+examUser.getExam().getExamId()+"' value='" + presentedValue +"'></td>");
                                            out.println("<td style='text-align: center;'><input class='check-grade' type='checkbox' id='graded-"+userResult.getUser().getIdAuthentication()+'-'+examUser.getExam().getExamId()+"' value='" + gradedValue +"' disabled></td>");
                                        }else{
                                            out.println("<td style='text-align: center;'><input checked='true' class='check-presented' type='checkbox' id='"+userResult.getUser().getIdAuthentication()+'-'+examUser.getExam().getExamId()+"' value='" + presentedValue +"'></td>");
                                            out.println("<td style='text-align: center;'><input class='check-grade' type='checkbox' id='graded-"+userResult.getUser().getIdAuthentication()+'-'+examUser.getExam().getExamId()+"' value='" + gradedValue +"'></td>");
                                        }
                                    out.println("</tr>");
                                }
                            }
                            out.println("</tbody>");
                        %>
                    </table><br/><br/><br/>
                    <div class="col-md-12"><br/><br/><br/><br/></div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/resources/js/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/dataTables/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/dataTables/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/grade.js"></script>
    </body>
</html>