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
public class ExamResult {
    
    private Exam exam;
    private int passed;
    private int failed;
    
    public ExamResult(){
        
    }
    
    public ExamResult(Exam exam, int passed, int failed){
        this.exam = exam;
        this.passed = passed;
        this.failed = failed;
    }
    
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }
    
}
