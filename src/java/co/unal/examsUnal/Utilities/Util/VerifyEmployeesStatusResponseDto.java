/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

/**
 *
 * @author AndresGutierrez
 */
import java.io.Serializable;
import java.util.List;

public class VerifyEmployeesStatusResponseDto implements Serializable {
    public static enum Status {
        PASS, FAIL, PENDING
    }
    
    public static class TestResultDto {
        private String name;
        private Status status;
        private String comment;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
        
        
    }
    
    public static class ResultDto {
        private String document;
        private List<TestResultDto> tests;

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public List<TestResultDto> getTests() {
            return tests;
        }

        public void setTests(List<TestResultDto> tests) {
            this.tests = tests;
        }
    }
    
    private List<ResultDto> results;

    public List<ResultDto> getResults() {
        return results;
    }

    public void setResults(List<ResultDto> results) {
        this.results = results;
    }
}