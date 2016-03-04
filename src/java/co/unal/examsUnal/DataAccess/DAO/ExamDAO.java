/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.DAO;

import co.unal.examsUnal.DataAccess.Entity.Exam;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author AndresGutierrez
 */
public class ExamDAO {
        public EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamsUnalPU");

    public Collection<Exam> findExamsUser(String userId){
        EntityManager em = emf.createEntityManager();
        Query query;
        try{
            query = em.createNativeQuery("SELECT * FROM Exam AS e INNER JOIN ResultExam AS r ON r.idExam=e.examId WHERE (r.idUser=? && (e.realizationDate<=DATE_ADD(CURDATE(),INTERVAL 2 DAY) && e.realizationDate>CURDATE()));",Exam.class);
            query.setParameter(1,userId);
            return query.getResultList();
        }catch(Exception e){
            //System.out.println(e.toString());
            return new ArrayList<Exam>();
        }
    }
}
