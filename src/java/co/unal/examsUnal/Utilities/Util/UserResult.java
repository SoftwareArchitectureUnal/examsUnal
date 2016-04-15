/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

import co.unal.examsUnal.DataAccess.Entity.User;
import java.util.ArrayList;

/**
 *
 * @author alej0
 */
public class UserResult {
    
    private User user;
    private ArrayList<ExamUser> examsUser = new ArrayList<>();
    
    public UserResult(User user, ArrayList<ExamUser> examsUser){
        this.user = user;
        this.examsUser = examsUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<ExamUser> getExamsUser() {
        return examsUser;
    }

    public void setExamsUser(ArrayList<ExamUser> examsUser) {
        this.examsUser = examsUser;
    }
    
    public void addExamUser(ExamUser examUser){
        this.examsUser.add(examUser);
    }
}
