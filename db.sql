DROP TABLE IF EXISTS `exam`;
DROP TABLE IF EXISTS `resultexam`;
DROP TABLE IF EXISTS `exambyquestion`;
DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `role`(
    `roleId` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    PRIMARY KEY(`roleId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user` (
    `name` varchar(255) NOT NULL,
    `document` varchar(255) UNIQUE NOT NULL,
    `email` varchar(255) UNIQUE NOT NULL,
    `gender` int NOT NULL,
    `idRole` varchar(255) NOT NULL,
    `idAuthentication` varchar(255) NOT NULL,
    FOREIGN KEY(idRole) REFERENCES `role`(roleId) ON DELETE CASCADE,
    PRIMARY KEY(`idAuthentication`) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `exam`(
    `examId` INT AUTO_INCREMENT,
    `name` varchar(255) NOT NULL UNIQUE,
    `realizationDate` date NOT NULL,
    `description` varchar(255) NOT NULL,
    `certificationDate` date,
    `expeditionDate` date,
    PRIMARY KEY(`examId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `question`(
    `questionId` varchar(255) NOT NULL,
    `category` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    PRIMARY KEY(`questionId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `exambyquestion`(
    `examByQuestionId` int NOT NULL,
    `idQuestion` varchar(255) NOT NULL,
    `idExam` int NOT NULL,
    FOREIGN KEY(idQuestion) REFERENCES question(questionId),
    FOREIGN KEY(idExam) REFERENCES exam(examId),
    PRIMARY KEY(`examByQuestionId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `resultexam`(
    `resultExamId` Int NOT NULL AUTO_INCREMENT,
    `idUser` varchar(255) NOT NULL,
    `idExam` int not null,
    `approved` int not NULL,
    `status` int not null,
    FOREIGN KEY(idUser) REFERENCES `user`(idAuthentication),
    FOREIGN KEY(idExam) REFERENCES `exam`(examId),
    PRIMARY KEY(`resultExamId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;