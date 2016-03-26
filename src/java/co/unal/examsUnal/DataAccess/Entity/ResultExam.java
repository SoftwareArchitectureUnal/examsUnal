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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yeisondavid
 */
@Entity
@Table(name = "resultexam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultExam.findAll", query = "SELECT r FROM ResultExam r"),
    @NamedQuery(name = "ResultExam.findByResultExamId", query = "SELECT r FROM ResultExam r WHERE r.resultExamId = :resultExamId"),
    @NamedQuery(name = "ResultExam.findByApproved", query = "SELECT r FROM ResultExam r WHERE r.approved = :approved"),
    @NamedQuery(name = "ResultExam.findByStatus", query = "SELECT r FROM ResultExam r WHERE r.status = :status")})
public class ResultExam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "resultExamId")
    private Integer resultExamId;
    @Column(name = "approved")
    private Integer approved;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "idExam", referencedColumnName = "examId")
    @ManyToOne
    private Exam idExam;
    @JoinColumn(name = "idUser", referencedColumnName = "idAuthentication")
    @ManyToOne
    private User idUser;

    public ResultExam() {
    }

    public ResultExam(Integer resultExamId) {
        this.resultExamId = resultExamId;
    }

    public Integer getResultExamId() {
        return resultExamId;
    }

    public void setResultExamId(Integer resultExamId) {
        this.resultExamId = resultExamId;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Exam getIdExam() {
        return idExam;
    }

    public void setIdExam(Exam idExam) {
        this.idExam = idExam;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resultExamId != null ? resultExamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultExam)) {
            return false;
        }
        ResultExam other = (ResultExam) object;
        if ((this.resultExamId == null && other.resultExamId != null) || (this.resultExamId != null && !this.resultExamId.equals(other.resultExamId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.unal.examsUnal.DataAccess.Entity.ResultExam[ resultExamId=" + resultExamId + " ]";
    }
    
}
