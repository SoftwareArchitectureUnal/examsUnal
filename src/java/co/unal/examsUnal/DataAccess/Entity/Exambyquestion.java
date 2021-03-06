/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ArqSoft
 */
@Entity
@Table(name = "exambyquestion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exambyquestion.findAll", query = "SELECT e FROM Exambyquestion e"),
    @NamedQuery(name = "Exambyquestion.findByExamByQuestionId", query = "SELECT e FROM Exambyquestion e WHERE e.examByQuestionId = :examByQuestionId")})
public class Exambyquestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "examByQuestionId")
    private Integer examByQuestionId;
    @JoinColumn(name = "idQuestion", referencedColumnName = "questionId")
    @ManyToOne(optional = false)
    private Question idQuestion;
    @JoinColumn(name = "idExam", referencedColumnName = "examId")
    @ManyToOne(optional = false)
    private Exam idExam;

    public Exambyquestion() {
    }

    public Exambyquestion(Integer examByQuestionId) {
        this.examByQuestionId = examByQuestionId;
    }

    public Integer getExamByQuestionId() {
        return examByQuestionId;
    }

    public void setExamByQuestionId(Integer examByQuestionId) {
        this.examByQuestionId = examByQuestionId;
    }

    public Question getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Question idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Exam getIdExam() {
        return idExam;
    }

    public void setIdExam(Exam idExam) {
        this.idExam = idExam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examByQuestionId != null ? examByQuestionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exambyquestion)) {
            return false;
        }
        Exambyquestion other = (Exambyquestion) object;
        if ((this.examByQuestionId == null && other.examByQuestionId != null) || (this.examByQuestionId != null && !this.examByQuestionId.equals(other.examByQuestionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unal.examsUnal.DataAccess.Entity.Exambyquestion[ examByQuestionId=" + examByQuestionId + " ]";
    }
    
}
