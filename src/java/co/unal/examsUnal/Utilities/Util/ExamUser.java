/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

import co.unal.examsUnal.DataAccess.Entity.Exam;

/**
 *
 * @author alej0
 */
public class ExamUser {
    
    private Exam exam;
    private String status;
    
    public ExamUser(Exam exam, boolean approved, boolean presented){
        this.exam = exam;
        if( !presented )
            this.status = "PENDING";
        else{
            if(approved)
                this.status = "PASS";
            else
                this.status = "FAIL";
        }
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
