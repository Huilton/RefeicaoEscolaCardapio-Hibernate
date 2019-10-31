
create database dbrefeicao;

use dbrefeicao;

create table if not exists pessoa (
id_pessoa integer not null auto_increment primary key,
nome varchar(50),
telefone varchar(10),
email varchar(50)
) engine=INNODB;

create table if not exists foto_cardapio (
id_foto_cardapio integer auto_increment primary key,
foto Blob,
nomeFoto varchar(1000)
) engine=INNODB;

create table if not exists instituicao (
id_instituicao integer not null auto_increment primary key,
nome_inst varchar(50),
email_inst varchar(100),
telefone_inst varchar(10),
id_pessoa integer,
foreign key(id_pessoa) references pessoa (id_pessoa)
) engine=INNODB;

create table if not exists refeicao (
id_refeicao integer not null auto_increment primary key,
id_foto_cardapio integer,
id_instituicao integer,
tipo_ref varchar(20),
data_ref datetime,
data_cadastro datetime,
nome_ref varchar(100),
descricao varchar(150), 
foreign key (id_instituicao) references instituicao (id_instituicao),
foreign key (id_foto_cardapio) references foto_cardapio (id_foto_cardapio)
) engine=INNODB;







