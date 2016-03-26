/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yeisondavid
 */
@Entity
@Table(name = "exam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByExamId", query = "SELECT e FROM Exam e WHERE e.examId = :examId"),
    @NamedQuery(name = "Exam.findByCertificationDate", query = "SELECT e FROM Exam e WHERE e.certificationDate = :certificationDate"),
    @NamedQuery(name = "Exam.findByDescription", query = "SELECT e FROM Exam e WHERE e.description = :description"),
    @NamedQuery(name = "Exam.findByExpeditionDate", query = "SELECT e FROM Exam e WHERE e.expeditionDate = :expeditionDate"),
    @NamedQuery(name = "Exam.findByName", query = "SELECT e FROM Exam e WHERE e.name = :name"),
    @NamedQuery(name = "Exam.findByRealizationDate", query = "SELECT e FROM Exam e WHERE e.realizationDate = :realizationDate")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "examId")
    private Integer examId;
    @Column(name = "certificationDate")
    @Temporal(TemporalType.DATE)
    private Date certificationDate;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "expeditionDate")
    @Temporal(TemporalType.DATE)
    private Date expeditionDate;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "realizationDate")
    @Temporal(TemporalType.DATE)
    private Date realizationDate;
    @OneToMany(mappedBy = "idExam")
    private Collection<ResultExam> resultExamCollection;
    @OneToMany(mappedBy = "idExam")
    private Collection<ExamByQuestion> examByQuestionCollection;

    public Exam() {
    }

    public Exam(Integer examId) {
        this.examId = examId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Date getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(Date certificationDate) {
        this.certificationDate = certificationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(Date expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    @XmlTransient
    public Collection<ResultExam> getResultExamCollection() {
        return resultExamCollection;
    }

    public void setResultExamCollection(Collection<ResultExam> resultExamCollection) {
        this.resultExamCollection = resultExamCollection;
    }

    @XmlTransient
    public Collection<ExamByQuestion> getExamByQuestionCollection() {
        return examByQuestionCollection;
    }

    public void setExamByQuestionCollection(Collection<ExamByQuestion> examByQuestionCollection) {
        this.examByQuestionCollection = examByQuestionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examId != null ? examId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unal.examsUnal.DataAccess.Entity.Exam[ examId=" + examId + " ]";
    }
    
}
