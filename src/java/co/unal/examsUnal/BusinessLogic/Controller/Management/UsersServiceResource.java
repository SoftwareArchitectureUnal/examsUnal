/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.BusinessLogic.Controller.Management;

import co.unal.examsUnal.Utilities.Util.ExamData;
import co.unal.examsUnal.Utilities.Util.ExamUser;
import co.unal.examsUnal.Utilities.Util.UserExamResult;
import co.unal.examsUnal.Utilities.Util.UserResult;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Héctor Andrés Arias
 */
@Path("usersService")
public class UsersServiceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersServiceResource
     */
    public UsersServiceResource() {
    }

    /**
     * Retrieves representation of an instance of co.unal.examsUnal.BusinessLogic.Controller.Management.UsersServiceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
        ExamController examController = new ExamController();
        Collection<UserResult> usersResults = examController.getUsersResults();
        String results = "";
        String exams;
        for(UserResult userResult: usersResults){
            results += "$$" + userResult.getUser().getName() + "&&" + userResult.getUser().getEmail()+ "&&"; //+ userResult.getExamsUser() + "&&" + examResult.getFailed()
            exams = "";
            for( ExamUser examUser: userResult.getExamsUser() ){
                exams += "%%" + examUser.getExam().getName() + "%%" + examUser.getExam().getDescription() + "%%" + examUser.isApproved();
            }
            exams = exams.length() > 2 ? exams.substring(2) : "";
            results += exams;
        }
        results = results.length() > 2 ? results.substring(2) : "";
        return results;
        
        //return "<html><body><h1>Hello, World!!</body></h1></html>";
    }

    /**
     * PUT method for updating or creating an instance of UsersServiceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
