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
    private boolean approved;
    
    public ExamUser(Exam exam, boolean approved){
        this.exam = exam;
        this.approved = approved;
    }
    
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
