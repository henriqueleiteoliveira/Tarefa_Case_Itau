CREATE DATABASE tarefas;

USE tarefas;

CREATE TABLE status(
	idStatus INT NOT NULL AUTO_INCREMENT,
	nomeStatus VARCHAR(50),
	PRIMARY KEY(idStatus)
);

CREATE TABLE tarefa(
	idTarefa INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	dataCriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	dataConclusao TIMESTAMP NULL,
	descricao VARCHAR(255),
	idStatus INT NOT NULL,
	observacao VARCHAR(255),
	nomeResponsavel VARCHAR(50),
	PRIMARY KEY(idTarefa)
);

ALTER TABLE tarefa ADD CONSTRAINT FK_STATUSTAREFA FOREIGN KEY(idStatus) REFERENCES status(idStatus);

INSERT INTO status(nomeStatus) values('pending');
INSERT INTO status(nomeStatus) values('completed');