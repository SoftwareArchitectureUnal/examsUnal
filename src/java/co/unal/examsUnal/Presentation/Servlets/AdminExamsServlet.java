/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Presentation.Servlets;

import co.unal.examsUnal.BusinessLogic.Controller.Management.ExamController;
import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.Utilities.Util.ExamResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alej0
 */
@WebServlet(name = "AdminExamsServlet", urlPatterns = {"/AdminExamsServlet"})
public class AdminExamsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String method = request.getParameter("method");
        
        if(method.equals("insertExam")){
            try{
                String name = request.getParameter("name");

                DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
                String expDateAsString = request.getParameter("expeditionDate");
                Date expDate = sourceFormat.parse(expDateAsString);

                String realDateAsString = request.getParameter("realizationDate");
                Date realDate = sourceFormat.parse(realDateAsString);

                String certDateAsString = request.getParameter("certificationDate");
                Date certDate = sourceFormat.parse(certDateAsString);
                
                String description = request.getParameter("description");
                
                ExamController examController = new ExamController();
                examController.insert(name, expDate, realDate, certDate, description);
            }catch(Exception e){
                
            }
        }
        if(method.equals("findExam")){
            ExamController examController = new ExamController();
            int id = Integer.parseInt( request.getParameter("id") );
            System.out.println(id);
            Exam exam = examController.findById(id);
            String examData = "";

            examData += exam.getName();
            
            int month = exam.getExpeditionDate().getMonth() + 1;
            
            if( month < 10 )
                examData += ","+exam.getExpeditionDate().getDate()+"/0"+month+"/"+(exam.getExpeditionDate().getYear()+1900);
            else
                examData += ","+exam.getExpeditionDate().getDate()+"/"+month+"/"+(exam.getExpeditionDate().getYear()+1900);

            month = exam.getRealizationDate().getMonth() + 1;
            if( month < 10 )
                examData += ","+exam.getRealizationDate().getDate()+"/0"+month+"/"+(exam.getRealizationDate().getYear()+1900);
            else
                examData += ","+exam.getRealizationDate().getDate()+"/"+month+"/"+(exam.getRealizationDate().getYear()+1900);

            month = exam.getCertificationDate().getMonth() + 1;
            if( month < 10 )
                examData += ","+exam.getCertificationDate().getDate()+"/0"+month+"/"+(exam.getCertificationDate().getYear()+1900);
            else
                examData += ","+exam.getCertificationDate().getDate()+"/"+month+"/"+(exam.getCertificationDate().getYear()+1900);
            
            examData += ","+exam.getDescription();
            
            PrintWriter out = response.getWriter();
            out.print(examData);
        }
        
        if(method.equals("updateExam")){
            try{
                int examId = Integer.parseInt( request.getParameter("id") );
                System.out.println("id   : " + examId);
                String name = request.getParameter("name");

                DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
                String expDateAsString = request.getParameter("expeditionDate");
                Date expDate = sourceFormat.parse(expDateAsString);

                String realDateAsString = request.getParameter("realizationDate");
                Date realDate = sourceFormat.parse(realDateAsString);

                String certDateAsString = request.getParameter("certificationDate");
                Date certDate = sourceFormat.parse(certDateAsString);
                
                String description = request.getParameter("description");
                
                ExamController examController = new ExamController();
                examController.update(examId, name, expDate, realDate, certDate, description);
            }catch(ParseException e){
                e.printStackTrace();
            }
        }
        if(method.equals("deleteExam")){
            ExamController examController = new ExamController();
            int id = Integer.parseInt( request.getParameter("id") );
            System.out.println(id);
            Exam exam = examController.findById(id);
            examController.deleteExam(exam);
        }
        if(method.equals("examsResults")){
            try{
                ExamController examController = new ExamController();
                Collection<ExamResult> examResults = examController.getResultsByExam();
                String results = "";
                for(ExamResult examResult: examResults){
                    results += "$$" + examResult.getExam().getExamId() + "&&" + examResult.getExam().getName() + "&&" + examResult.getPassed() + "&&" + examResult.getFailed();
                }
                System.out.println(results);
                results = results.substring(2);
                PrintWriter out = response.getWriter();
                out.print(results);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(method.equals("examResult")){
            try{
                int examId = Integer.parseInt( request.getParameter("id") );
                System.out.println("Exam id: " + examId);
                ExamController examController = new ExamController();
                ExamResult examResult = examController.getResultByIdExam(examId);
                String result = examResult.getExam().getExamId() + "&&" + examResult.getExam().getName() + "&&" + examResult.getPassed() + "&&" + examResult.getFailed();
                System.out.println("result: " + result);
                PrintWriter out = response.getWriter();
                out.print(result);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if( method.equals("findAllExams") ){
            try{
                Date now = new Date();
                ExamController examController = new ExamController();
                Collection<Exam> exams = examController.findAll();
                String result = "";
                for(Exam exam: exams){
                    if(exam.getCertificationDate().compareTo(now) <= 0){
                        result += "$$" + exam.getExamId() + "&&" + exam.getName() + "&&" + "Certificado";
                    }else if( exam.getRealizationDate().compareTo(now) <= 0 ){
                        result += "$$" + exam.getExamId() + "&&" + exam.getName() + "&&" + "Realizado";
                    }else{
                        result += "$$" + exam.getExamId() + "&&" + exam.getName() + "&&" + "Publicado";
                    }
                }
                System.out.println(result);
                result = result.substring(2);
                PrintWriter out = response.getWriter();
                out.print(result);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(method.equals("examsRegistered")){
            try{
                ExamController examController = new ExamController();
                Collection<ExamResult> examRegistered = examController.getRegisteredByExam();
                String registers = "";
                for(ExamResult examRegister: examRegistered){
                    registers += "$$" + examRegister.getExam().getExamId() + "&&" + examRegister.getExam().getName() + "&&" + examRegister.getPassed();
                }
                System.out.println(registers);
                registers = registers.substring(2);
                PrintWriter out = response.getWriter();
                out.print(registers);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        processRequest(request, response);
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
