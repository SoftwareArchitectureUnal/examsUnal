/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

import java.util.ArrayList;

/**
 *
 * @author alej0
 */
public class UserExamResult {
    
    private String name;
    private String email;
    private ArrayList<ExamData> examsData;
    
    public UserExamResult( String name, String email, ArrayList<ExamData> examsData){
        this.name = name;
        this.email = email;
        this.examsData = examsData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ExamData> getExamsData() {
        return examsData;
    }

    public void setExamsData(ArrayList<ExamData> examsData) {
        this.examsData = examsData;
    }
    
}
