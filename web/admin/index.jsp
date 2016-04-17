<%--
    Document   : index
    Created on : 28-feb-2016, 10:37:01
    Author     : alej0
--%>

<%@page import="co.unal.examsUnal.DataAccess.Entity.Exam"%>
<%@page import="co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exámenes</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal/modal.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-ui.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dataTables/css/dataTables.bootstrap.min.css" type="text/css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/chart/Chart.min.js"></script>
        
    </head>
    <body>
        <div class="modal fade modal-small" id="modal-deleted" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Eliminado</h4>
                    </div>
                    <div class="modal-body">
                        <p>El registro ha sido eliminado.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade modal-small" id="modal-saved" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Guardado</h4>
                    </div>
                    <div class="modal-body">
                        <p>El registro ha sido guardado.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade modal-large" id="modal-save" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Ingresar examen</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <label for="exam-name">Nombre</label><br/>
                                <input type="text" value="" id="exam-name" class="form-control"></input><br/>
                            <label for="exam-start-date">Fecha de expedición</label><br/>
                                <input styleClass="datepicker" value="" id="exam-start-date" class="form-control datepicker" autocomplete="off"></input><br/>
                            <label for="exam-date">Fecha de realización</label><br/>
                                <input type="text" value="" id="exam-date" class="form-control datepicker" autocomplete="off"></input><br/>
                            <label for="exam-cert-date">Fecha de certificación</label><br/>
                                <input type="text" value="" id="exam-cert-date" class="form-control datepicker" autocomplete="off"></input><br/>
                            <label for="exam-description">Descripción</label><br/>
                                <textarea type="text" value="" id="exam-description" rows="5" cols="50" class="form-control"></textarea><br/>
                                <button type="button" id="btn-save" class="btn btn-lg btn-primary btn-block">Guardar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade modal-small" id="modal-updated" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Actualizado</h4>
                    </div>
                    <div class="modal-body">
                        <p>El registro ha sido actualizado.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade modal-large" id="modal-update" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Ingresar examen</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <label>Id:&nbsp;</label><label id="id-up"></label><br/><br/>
                            <label for="exam-name-up">Nombre</label><br/>
                                <input type="text" value="" id="exam-name-up" class="form-control" placeholder="Nombre del exámen"></input><br/>
                            <label for="exam-start-date-up">Fecha de expedición</label><br/>
                                <input styleClass="datepicker" value="" id="exam-start-date-up" class="form-control datepicker" placeholder="Expedición" autocomplete="off"></input><br/>
                            <label for="exam-date-up">Fecha de realización</label><br/>
                                <input type="text" value="" id="exam-date-up" class="form-control datepicker" placeholder="Realización" autocomplete="off"></input><br/>
                            <label for="exam-cert-date-up">Fecha de certificación</label><br/>
                                <input type="text" value="" id="exam-cert-date-up" class="form-control datepicker" placeholder="Certificación" autocomplete="off"></input><br/>
                            <label for="exam-description-up">Descripción</label><br/>
                                <textarea type="text" value="" id="exam-description-up" rows="5" cols="50" class="form-control"></textarea><br/>
                                <button type="button" id="btn-update" class="btn btn-lg btn-primary btn-block">Guardar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h1 class="text-primary">Exámenes</h1><br/>
                    <table id="table-exams" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                        <%
                            ExamController examController = new ExamController();
                            Collection<Exam> exams = examController.findAll();
                            out.println("<thead>");
                            out.println("<tr>");
                                out.println("<th style='text-align: center;'>ID</th>");
                                out.println("<th style='text-align: center;'>Nombre</th>");
                                out.println("<th style='text-align: center;'>Descripción</th>");
                                out.println("<th style='text-align: center;'>Fecha de publicación</th>");
                                out.println("<th style='text-align: center;'>Fecha de presentación</th>");
                                out.println("<th style='text-align: center;'>Fecha de certificado</th>");
                                out.println("<th style='text-align: center;'>Actualizar</th>");
                                out.println("<th style='text-align: center;'>Eliminar</th>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tfoot>");
                            out.println("<tr>");
                                out.println("<th style='text-align: center;'>ID</th>");
                                out.println("<th style='text-align: center;'>Nombre</th>");
                                out.println("<th style='text-align: center;'>Descripción</th>");
                                out.println("<th style='text-align: center;'>Fecha de publicación</th>");
                                out.println("<th style='text-align: center;'>Fecha de presentación</th>");
                                out.println("<th style='text-align: center;'>Fecha de certificado</th>");
                                out.println("<th style='text-align: center;'>Actualizar</th>");
                                out.println("<th style='text-align: center;'>Eliminar</th>");
                            out.println("</tr>");
                            out.println("</tfoot>");

                            out.println("<tbody>");
                            for( Exam exam : exams){
                                out.println("<tr>");
                                    out.println("<td>"+exam.getExamId()+"</td>");
                                    out.println("<td>"+exam.getName()+"</td>");
                                    out.println("<td>"+exam.getDescription()+"</td>");

                                    if( exam.getExpeditionDate().getMonth() + 1 < 10 )
                                        out.println("<td style='text-align: center;'>"+exam.getExpeditionDate().getDate()+"/0"+exam.getExpeditionDate().getMonth()+"/"+(exam.getExpeditionDate().getYear()+1900)+"</td>");
                                    else
                                        out.println("<td style='text-align: center;'>"+exam.getExpeditionDate().getDate()+"/"+exam.getExpeditionDate().getMonth()+"/"+(exam.getExpeditionDate().getYear()+1900)+"</td>");

                                    if( exam.getRealizationDate().getMonth() + 1 < 10 )
                                        out.println("<td style='text-align: center;'>"+exam.getRealizationDate().getDate()+"/0"+exam.getRealizationDate().getMonth()+"/"+(exam.getRealizationDate().getYear()+1900)+"</td>");
                                    else
                                        out.println("<td style='text-align: center;'>"+exam.getRealizationDate().getDate()+"/"+exam.getRealizationDate().getMonth()+"/"+(exam.getRealizationDate().getYear()+1900)+"</td>");

                                    if( exam.getCertificationDate().getMonth() + 1 < 10 )
                                        out.println("<td style='text-align: center;'>"+exam.getCertificationDate().getDate()+"/0"+exam.getCertificationDate().getMonth()+"/"+(exam.getCertificationDate().getYear()+1900)+"</td>");
                                    else
                                        out.println("<td style='text-align: center;'>"+exam.getCertificationDate().getDate()+"/"+exam.getCertificationDate().getMonth()+"/"+(exam.getCertificationDate().getYear()+1900)+"</td>");

                                    out.println("<td style='text-align: center;'><button type='button' id='"+exam.getExamId()+"' class='btn-update btn btn-info'><span class='glyphicon glyphicon-refresh' aria-hidden='true'></span></button></td>");
                                    out.println("<td style='text-align: center;'><button type='button' id='"+exam.getExamId()+"' class='btn-delete btn btn-info'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button></td>");
                                out.println("</tr>");
                            }
                            out.println("</tbody>");
                        %>
                    </table><br/><br/><br/>
                    <div class="col-md-3">
                        <a href="${pageContext.request.contextPath}/admin/statistics.jsp"><button type="button" id="btn-statistics" class="btn btn-lg btn-primary btn-block">Estadísticas</button></a>
                    </div>
                    <div class="col-md-3 col-md-offset-6">
                        <button type="button" id="btn-insert" class="btn btn-lg btn-primary btn-block">Nuevo</button><br/><br/>
                    </div>
                    
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#certified-users" id="tab-certified-users">Usuarios certificados</a></li>
                        <li><a data-toggle="tab" href="#exams-statistics" id="tab-exams-statistics">Exámenes</a></li>
                        <li><a data-toggle="tab" href="#registered-users" id="tab-registered-users">Usuarios inscritos</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="certified-users" class="tab-pane fade in active">
                            <div class="col-md-12">
                                <h3 class="text-primary">Certificados</h3><br/>
                                <div class="col-md-9" style="text-align: center;">
                                    <canvas id="users-chart" width="800px;" height="500px;"></canvas>
                                </div>
                                <div class="col-md-3">
                                    <div id="legend-users-chart"></div>
                                </div>
                            </div>
                        </div>
                        <div id="exams-statistics" class="tab-pane fade">
                            <div class="col-md-12">
                                <h3 class="text-primary">Resultados por examen</h3><br/>
                                <div class="col-md-12">
                                    <label for="search-exams">Exámen</label><br/>
                                    <input type="text" id="search-exams" class="form-control" placeholder="Seleccione un exámen"></input><br/>
                                </div>
                                <div class="col-md-3 col-md-offset-9">
                                    <button type="button" id="btn-load" class="btn btn-lg btn-primary btn-block">cargar</button><br/><br/>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-9" style="text-align: center;">
                                    <canvas id="exam-chart" width="800px;" height="500px;"></canvas>
                                </div>
                                <div class="col-md-3" id="legend-exam-chart"></div>
                            </div>
                        </div>
                        <div id="registered-users" class="tab-pane fade">
                            <div class="col-md-12">
                                <h3 class="text-primary">Inscritos</h3><br/>
                                <div class="col-md-10 col-md-offset-1" style="text-align: center;">
                                    <canvas id="registered-chart" width="800px;" height="500px;"></canvas>
                                </div>
                                <div class="col-md-3">
                                    <div id="legend-registered-chart"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12"><br/><br/><br/><br/></div>
                    <div class="col-md-12"><br/><br/><br/><br/></div>
                    <div class="col-md-12"><br/><br/><br/><br/></div>
                </div>
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/resources/js/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/dataTables/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/dataTables/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/exams.js"></script>
    </body>
</html>
