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
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h1 class="text-primary">Exámenes</h1><br/>
                    <table id="table-exams" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                        <%
                            ExamController examController = new ExamController();
                            Collection<UserResult> userResults = examController.getUsersResults();
                            out.println("<thead>");
                            out.println("<tr>");
                                out.println("<th style='text-align: center;'>ID</th>");
                                out.println("<th style='text-align: center;'>Nombre</th>");
                                out.println("<th style='text-align: center;'>Descripción</th>");
                                out.println("<th style='text-align: center;'>Presentado</th>");
                                out.println("<th style='text-align: center;'>Aprobado</th>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tfoot>");
                            out.println("<tr>");
                                out.println("<th style='text-align: center;'>ID</th>");
                                out.println("<th style='text-align: center;'>Nombre</th>");
                                out.println("<th style='text-align: center;'>Descripción</th>");
                                out.println("<th style='text-align: center;'>Presentado</th>");
                                out.println("<th style='text-align: center;'>Aprobado</th>");
                            out.println("</tr>");
                            out.println("</tfoot>");

                            out.println("<tbody>");
                            for( UserResult userResult : userResults){
                                out.println("<tr class=\"group\">");
                                out.println("<td>" + userResult.getUser().getName() + "\tCC: " + userResult.getUser().getDocument() + "</td>");
                                out.println("</tr>");
                                for( ExamUser examUser : userResult.getExamsUser() ){
                                    out.println("<tr>");
                                        out.println("<td>"+examUser.getExam().getExamId()+"</td>");
                                        out.println("<td>"+examUser.getExam().getName()+"</td>");
                                        out.println("<td>"+examUser.getExam().getDescription()+"</td>");
                                        int presentedValue = examUser.getStatus().equals(Status.PENDING) ? 0 : 1;
                                        int gradedValue = examUser.getStatus().equals(Status.PASS) ? 0 : 1;
                                        out.println("<td style='text-align: center;'><input type='checkbox' id='"+examUser.getExam().getExamId()+"' value='" + presentedValue +"' ></td>");
                                        out.println("<td style='text-align: center;'><input type='checkbox' id='"+examUser.getExam().getExamId()+"' value='" + gradedValue +"' ></td>");
                                    out.println("</tr>");
                                }
                            }
                            out.println("</tbody>");
                        %>
                    </table><br/><br/><br/>
                    <div class="col-md-12"><br/><br/><br/><br/></div>
                    <div class="col-md-12"><br/><br/><br/><br/></div>
                    <div class="col-md-12"><br/><br/><br/><br/></div>
                </div>
            </div>
        </div>
    </body>
</html>
