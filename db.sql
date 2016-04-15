DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Authentication`;
DROP TABLE IF EXISTS `Role`;
DROP TABLE IF EXISTS `ExamByQuestion`;
DROP TABLE IF EXISTS `ResultExam`;
DROP TABLE IF EXISTS `Question`;
DROP TABLE IF EXISTS `Exam`;

CREATE TABLE `Authentication`(
    `authenticationId` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY(`authenticationId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Role`(
    `roleId` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    PRIMARY KEY(`roleId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `User` (
    `name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `gender` int NOT NULL,
    `idRole` varchar(255) NOT NULL,
    `idAuthentication` varchar(255) NOT NULL,
    FOREIGN KEY(idAuthentication) REFERENCES AUTHENTICATION(authenticationId) ON DELETE CASCADE,
    FOREIGN KEY(idRole) REFERENCES ROLE(roleId) ON DELETE CASCADE,
    PRIMARY KEY(`idAuthentication`) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `Exam`(
	`examId` INT AUTO_INCREMENT=1000,
	`name` varchar(255) NOT NULL UNIQUE,
	`realizationDate` date NOT NULL,
	`description` varchar(255) NOT NULL,
	`certificationDate` date,
	`expeditionDate` date,
	PRIMARY KEY(`examId`)

)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
CREATE TABLE `Question`(
	`questionId` varchar(255) NOT NULL,
	`category` varchar(255) NOT NULL,
	`description` varchar(255) NOT NULL,
	PRIMARY KEY(`questionId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ExamByQuestion`(
	`examByQuestionId` int NOT NULL,
	`idQuestion` varchar(255) NOT NULL,
	`idExam` int NOT NULL,
	FOREIGN KEY(idQuestion) REFERENCES Question(questionId),
	FOREIGN KEY(idExam) REFERENCES Exam(examId),
	PRIMARY KEY(`examByQuestionId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `ResultExam`(
	`resultExamId` Int NOT NULL AUTO_INCREMENT,
	`idUser` varchar(255) NOT NULL,
	`idExam` int not null,
	`approved` int not nULL,
	`status` int not null,
	FOREIGN KEY(idUser) REFERENCES User(idAuthentication),
	FOREIGN KEY(idExam) REFERENCES Exam(examId),
	PRIMARY KEY(`resultExamId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;