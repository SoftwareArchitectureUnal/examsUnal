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
    
    private String document;
    private ArrayList<Tests> tests;
    
    public UserExamResult( String document, ArrayList<Tests> examsData){
        this.document = document;
        this.tests = examsData;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
    
    public ArrayList<Tests> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Tests> tests) {
        this.tests = tests;
    }
    
}
