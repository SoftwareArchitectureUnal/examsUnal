DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Role`;
DROP TABLE IF EXISTS `ExamByQuestion`;
DROP TABLE IF EXISTS `ResultExam`;
DROP TABLE IF EXISTS `Question`;
DROP TABLE IF EXISTS `Exam`;

CREATE TABLE `Role`(
    `roleId` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    PRIMARY KEY(`roleId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `User` (
    `userId` varchar (255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `gender` int NOT NULL,
    `password` varchar(255) NOT NULL,
    `idRole` varchar(255) NOT NULL,
    FOREIGN KEY(idRole) REFERENCES ROLE(roleId) ON DELETE CASCADE,
    PRIMARY KEY(`userId`) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `Exam`(
	`examId` INT AUTO_INCREMENT,
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
	FOREIGN KEY(idUser) REFERENCES User(userId),
	FOREIGN KEY(idExam) REFERENCES Exam(examId),
	PRIMARY KEY(`resultExamId`)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

/*roles*/
Insert into role (roleId, description) values ("admin", "Administrador");
Insert into role (roleId, description) values ("user", "Usuario");


/* Usuarios */

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('admin', 'Administrador', 'admin@unal.edu.co', 1, 'password', 'admin');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('yeison', 'Yeison David Garcia Gomez', 'yeison@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('andres', 'Andres Rene Gutierrez Tiuso', 'andres@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('alejandro', 'Hugo Alejandro Arias Zamora', 'alejandro@unal.edu.co', 1, 'password', 'user');


INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('luis', 'Luis Fernando Bello Ocampo', 'luis@unal.edu.co', 1, 'password', 'user');


INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user1', 'User1', 'user1@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user2', 'User2', 'user2@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user3', 'User3', 'user3@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user4', 'User4', 'user4@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user5', 'User5', 'user5@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user6', 'User6', 'user6@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user7', 'User7', 'user7@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user8', 'User8', 'user8@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user9', 'User9', 'user9@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user10', 'User10', 'user10@unal.edu.co', 1, 'password', 'user');



INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user11', 'User11', 'user11@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user12', 'User12', 'user12@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user13', 'User13', 'user13@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user14', 'User14', 'user14@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user15', 'User15', 'user15@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user16', 'User16', 'user16@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user17', 'User17', 'user17@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user18', 'User18', 'user18@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user19', 'User19', 'user19@unal.edu.co', 1, 'password', 'user');

INSERT INTO examsdb.`user` (`userId`, `name`, email, gender, password, `idRole`) 
	VALUES ('user20', 'User20', 'user20@unal.edu.co', 1, 'password', 'user');



/*Exams*/

Insert Into exam (name, expeditionDate, realizationDate, certificationDate, description) values("Scrum Máster", '2016-2-04', '2016-2-24', '2016-2-28', "Examen para rol de Scrum Máster");
Insert Into exam (name, expeditionDate, realizationDate, certificationDate, description) values("Desarrollador", '2016-2-04', '2016-2-24', '2016-2-28', "Examen para hacer parte del equipo de desarrollo");
Insert Into exam (name, expeditionDate, realizationDate, certificationDate, description) values("Consultor ITIL", '2016-2-14', '2016-3-04', '2016-3-18', "Examen para consultor organizacional especializado en ITIL");
Insert Into exam (name, expeditionDate, realizationDate, certificationDate, description) values("Consultor eTOM", '2016-2-15', '2016-3-05', '2016-3-18', "Examen para consultor organizacional especializado en eTOM");
Insert Into exam (name, expeditionDate, realizationDate, certificationDate, description) values("Analista de redes Cisco", '2016-3-15', '2016-3-25', '2016-3-29', "Examen para analista de redes con especializaciones en cisco CCNA y CCNP");
Insert Into exam (name, expeditionDate, realizationDate, certificationDate, description) values("Analista de bases de datos", '2016-2-15', '2016-3-05', '2016-3-15', "Examen para analista de bases de datos oracle");




Insert Into resultexam (idUser, idExam, approved, status) values ("yeison", 1000, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user5", 1000, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user1", 1000, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user2", 1000, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user3", 1000, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user4", 1000, 1, 1);

Insert Into resultexam (idUser, idExam, approved, status) values ("yeison", 1001, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user1", 1001, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user2", 1001, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user3", 1001, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user4", 1001, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user5", 1001, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user6", 1001, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user7", 1001, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user8", 1001, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user9", 1001, 0, 1);

Insert Into resultexam (idUser, idExam, approved, status) values ("yeison", 1002, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user2", 1002, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user3", 1002, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user4", 1002, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user5", 1002, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user6", 1002, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user7", 1002, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user8", 1002, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user9", 1002, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user10", 1002, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user11", 1002, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user12", 1002, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user13", 1002, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user14", 1002, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user15", 1002, 1, 1);

Insert Into resultexam (idUser, idExam, approved, status) values ("user1", 1003, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user2", 1003, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user3", 1003, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user4", 1003, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user5", 1003, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user6", 1003, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user7", 1003, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user8", 1003, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user9", 1003, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user10", 1003, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user11", 1003, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user12", 1003, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user13", 1003, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user14", 1003, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user15", 1003, 1, 1);

Insert Into resultexam (idUser, idExam, approved, status) values ("user1", 1004, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user2", 1004, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user3", 1004, 0, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user4", 1004, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user5", 1004, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user6", 1004, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user7", 1004, 1, 1);
Insert Into resultexam (idUser, idExam, approved, status) values ("user8", 1004, 1, 1);

Insert Into resultexam (idUser, idExam, approved, status) values ("user1", 1005, 0, 0);
Insert Into resultexam (idUser, idExam, approved, status) values ("user2", 1005, 0, 0);
Insert Into resultexam (idUser, idExam, approved, status) values ("user3", 1005, 0, 0);
Insert Into resultexam (idUser, idExam, approved, status) values ("user4", 1005, 0, 0);
Insert Into resultexam (idUser, idExam, approved, status) values ("user5", 1005, 0, 0);
Insert Into resultexam (idUser, idExam, approved, status) values ("user6", 1005, 0, 0);
Insert Into resultexam (idUser, idExam, approved, status) values ("user7", 1005, 0, 0);
Insert Into resultexam (idUser, idExam, approved, status) values ("user8", 1005, 0, 0);
