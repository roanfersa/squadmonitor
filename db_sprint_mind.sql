CREATE DATABASE IF NOT EXISTS db_sprint_mind;
USE db_sprint_mind;


CREATE TABLE tbl_departamento (
    id_departamento INT AUTO_INCREMENT PRIMARY KEY,
    supervisor_departamento VARCHAR(100),
    desc_departamento VARCHAR(255),
    nome_departamento VARCHAR(100)
);

CREATE TABLE tbl_usuario (
    cpf_usuario CHAR(11) PRIMARY KEY,
    departamento_usuario VARCHAR(100),
    squad_usuario INT,
    data_inicio_empresa_usuario DATE,
    senha_usuario VARCHAR(255),
    email_usuario VARCHAR(100),
    cargo_usuario VARCHAR(100),
    dt_nascimento_usuario DATE
);

CREATE TABLE tbl_funcionario (
    cpf_usuario CHAR(11) PRIMARY KEY,
    turno_trabalho VARCHAR(100),
    funcao_funcionario VARCHAR(100),
    FOREIGN KEY (cpf_usuario) REFERENCES tbl_usuario(cpf_usuario)
);

CREATE TABLE tbl_release (
    id_release INT AUTO_INCREMENT PRIMARY KEY,
    status_release VARCHAR(50),
    dataInicio_release DATE,
    dataFim_release DATE,
    nome_release VARCHAR(100)
);

CREATE TABLE tbl_sprint (
    id_sprint INT AUTO_INCREMENT PRIMARY KEY,
    status_sprint VARCHAR(50),
    id_release INT,
    dataFim_sprint DATE,
    dataInicio_sprint DATE,
    nome_sprint VARCHAR(100),
    FOREIGN KEY (id_release) REFERENCES tbl_release(id_release)
);


CREATE TABLE tbl_squad (
    id_squad INT AUTO_INCREMENT PRIMARY KEY,
    membros_squad INT,
    desc_squad VARCHAR(255),
    qtd_funcionarios INT,
    nome_squad VARCHAR(100),
    id_departamento INT,  
    FOREIGN KEY (id_departamento) REFERENCES tbl_departamento(id_departamento)
);


CREATE TABLE tbl_gestor (
    cpf_usuario CHAR(11) PRIMARY KEY,
    funcao_scrum_gestor VARCHAR(100),
    FOREIGN KEY (cpf_usuario) REFERENCES tbl_usuario(cpf_usuario)
);


ALTER TABLE tbl_squad 
ADD COLUMN id_gestor CHAR(11) NULL,
ADD CONSTRAINT fk_squad_gestor
    FOREIGN KEY (id_gestor) REFERENCES tbl_gestor(cpf_usuario);


CREATE TABLE tbl_participacaoSquad (
    id_squad INT,
    cpf_usuario CHAR(11),
    PRIMARY KEY (id_squad, cpf_usuario),
    FOREIGN KEY (id_squad) REFERENCES tbl_squad(id_squad),
    FOREIGN KEY (cpf_usuario) REFERENCES tbl_usuario(cpf_usuario)
);


CREATE TABLE tbl_checkIn (
    id_checkin INT AUTO_INCREMENT PRIMARY KEY,
    comentarioAdicional_usuario VARCHAR(255),
    sentimento_checkin VARCHAR(100),
    cargaTrabalho_checkin INT,
    foco_checkin VARCHAR(100),
    estresse_checkin INT,
    energia_checkin INT,
    humorGeral_checkin VARCHAR(100),
    dtAthora_checkin DATETIME,
    cpf_usuario CHAR(11),
    FOREIGN KEY (cpf_usuario) REFERENCES tbl_usuario(cpf_usuario)
);


CREATE TABLE tbl_checkinSprint (
    id_checkin INT,
    id_sprint INT,
    PRIMARY KEY (id_checkin, id_sprint),
    FOREIGN KEY (id_checkin) REFERENCES tbl_checkIn(id_checkin),
    FOREIGN KEY (id_sprint) REFERENCES tbl_sprint(id_sprint)
);


CREATE TABLE tbl_dashboard (
    id_dashboard INT AUTO_INCREMENT PRIMARY KEY,
    ultimaAtualizacao_dashboard DATETIME,
    riscosAtivos_dashboard INT,
    totalCheckin_dashboard INT,
    tendenciaEstresse_dashboard VARCHAR(100),
    mediaHumor_dashboard DECIMAL(5,2),
    id_departamento INT,
    FOREIGN KEY (id_departamento) REFERENCES tbl_departamento(id_departamento)
);


CREATE TABLE tbl_visualiza (
    cpf_usuario CHAR(11),
    id_dashboard INT,
    PRIMARY KEY (cpf_usuario, id_dashboard),
    FOREIGN KEY (cpf_usuario) REFERENCES tbl_usuario(cpf_usuario),
    FOREIGN KEY (id_dashboard) REFERENCES tbl_dashboard(id_dashboard)
);

CREATE TABLE tbl_alerta (
    id_alerta INT AUTO_INCREMENT PRIMARY KEY,
    qtdUsuariosProb_alerta INT,
    id_release INT,
    id_sprint INT,
    data_alerta DATE,
    mensagem_alerta VARCHAR(255),
    nivel_alerta VARCHAR(50),
    tipo_alerta VARCHAR(100),
    FOREIGN KEY (id_release) REFERENCES tbl_release(id_release),
    FOREIGN KEY (id_sprint) REFERENCES tbl_sprint(id_sprint)
);

SHOW TABLES FROM db_sprint_mind;

