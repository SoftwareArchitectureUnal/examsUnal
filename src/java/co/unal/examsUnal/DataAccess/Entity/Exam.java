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
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ArqSoft
 */
@Entity
@Table(name = "exam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByExamId", query = "SELECT e FROM Exam e WHERE e.examId = :examId"),
    @NamedQuery(name = "Exam.findByName", query = "SELECT e FROM Exam e WHERE e.name = :name"),
    @NamedQuery(name = "Exam.findByRealizationDate", query = "SELECT e FROM Exam e WHERE e.realizationDate = :realizationDate"),
    @NamedQuery(name = "Exam.findByDescription", query = "SELECT e FROM Exam e WHERE e.description = :description"),
    @NamedQuery(name = "Exam.findByCertificationDate", query = "SELECT e FROM Exam e WHERE e.certificationDate = :certificationDate"),
    @NamedQuery(name = "Exam.findByExpeditionDate", query = "SELECT e FROM Exam e WHERE e.expeditionDate = :expeditionDate")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "examId")
    private Integer examId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "realizationDate")
    @Temporal(TemporalType.DATE)
    private Date realizationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "certificationDate")
    @Temporal(TemporalType.DATE)
    private Date certificationDate;
    @Column(name = "expeditionDate")
    @Temporal(TemporalType.DATE)
    private Date expeditionDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExam")
    private Collection<Resultexam> resultexamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExam")
    private Collection<Exambyquestion> exambyquestionCollection;

    public Exam() {
    }

    public Exam(Integer examId) {
        this.examId = examId;
    }

    public Exam(Integer examId, String name, Date realizationDate, String description) {
        this.examId = examId;
        this.name = name;
        this.realizationDate = realizationDate;
        this.description = description;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(Date certificationDate) {
        this.certificationDate = certificationDate;
    }

    public Date getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(Date expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    @XmlTransient
    public Collection<Resultexam> getResultexamCollection() {
        return resultexamCollection;
    }

    public void setResultexamCollection(Collection<Resultexam> resultexamCollection) {
        this.resultexamCollection = resultexamCollection;
    }

    @XmlTransient
    public Collection<Exambyquestion> getExambyquestionCollection() {
        return exambyquestionCollection;
    }

    public void setExambyquestionCollection(Collection<Exambyquestion> exambyquestionCollection) {
        this.exambyquestionCollection = exambyquestionCollection;
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
