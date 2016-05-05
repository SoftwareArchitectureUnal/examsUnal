/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

import co.unal.examsUnal.DataAccess.Entity.Exam;
import co.unal.examsUnal.Utilities.Util.VerifyEmployeesStatusResponseDto.Status;

/**
 *
 * @author alej0
 */
public class ExamUser {
    
    private Exam exam;
    private Status status;
    
    public ExamUser(Exam exam, boolean approved, boolean presented){
        this.exam = exam;
        if( !presented )
            this.status = Status.PENDING;
        else{
            if(approved)
                this.status = Status.PASS;
            else
                this.status = Status.FAIL;
        }
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
