/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

/**
 *
 * @author alej0
 */
public class ExamData {
    
    private String examName;
    private String examDescription;
    private boolean approved;
    
    public ExamData( String examName, String ExamDescription, boolean approved){
        this.examName = examName;
        this.examDescription = ExamDescription;
        this.approved = approved;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDescription() {
        return examDescription;
    }

    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
