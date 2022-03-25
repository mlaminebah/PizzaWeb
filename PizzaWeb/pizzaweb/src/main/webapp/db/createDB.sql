
DROP DATABASE IF EXISTS pizza_web;

CREATE DATABASE pizza_web;
use pizza_web;

-- -- Table Personne
CREATE TABLE IF NOT EXISTS Personne (
      IDPersonne  INT (11) AUTO_INCREMENT NOT NULL,
      Nom VARCHAR(100) NOT NULL,
      Prenom VARCHAR(100) NOT NULL,
      Email VARCHAR(100) NOT NULL,
      Adresse VARCHAR(100),
      Phone VARCHAR(50) not null,
      PRIMARY KEY (IDPersonne),
      UNIQUE (Email)
);
INSERT INTO Personne (Nom,Prenom,Email,Adresse,Phone) VALUES ('Mama','Mamadou','lam@gmail.com','Coza','+33688700407'),('Mamaa','Prenom','la@gmail.com','Coza 2','+33699750407');
select *from Personne;
-- -- Table Client
CREATE TABLE IF NOT EXISTS Client (
      IDClient INT NOT NULL,
      MotDePasse varchar(50) not null,
      PRIMARY KEY (IDClient),
      FOREIGN KEY (IDClient) REFERENCES Personne(IDPersonne)
);
INSERT INTO Client VALUES (1,'12345a'),(2,'123456a');
select * from Client;

-- -- Table Administrateur
CREATE TABLE IF NOT EXISTS Administrateur (
    IDAdministrateur int not null,
    MotDePasse varchar(100) not null,
    primary key (IDAdministrateur),
    foreign key (IDAdministrateur) REFERENCES Personne(IDPersonne)
);
-- --Table Ingrédients
create TABLE IF NOT EXISTS Ingredients (
	IDIngreds int AUTO_INCREMENT not null,
	Nom varchar(100) not null,
	primary key (IDIngreds)
);

insert into Ingredients (Nom) VALUES 
('pâtes'),('ail'),('sauces tomates'),('champignons'),('fromages'),('basilic frais'),('tomates pelées'),('sauce piquante'),
('olives noires'),('olives vertes'),('mozzarella'),('viande boeuf'),('viande chevre'),('viande porc'),('huile d\'olive'),
('huile tournesol'),('hile d\'amande'),('épinards'),('feta'),('oeuf'),('merguez'),('pomme de terre'),('jambon'),('crème fraîche');
select * from Ingredients;

-- -- Table Pizza
create TABLE IF NOT EXISTS Pizza (
    IDPizza int AUTO_INCREMENT not null,
    Nom varchar(100) not null,
    Prix float not null,
    Photo varchar(100),
    Taille varchar(100) not null,
    QteStock int not null,
    primary key (IDPizza)
);
insert into Pizza (Nom,Prix,Photo,Taille,QteStock) VALUES 
('merguez piquant',7,'images/1.jpeg','Enfant',4),
('pomme de terre',7,'images/1.jpeg','Moyen',2),
('merguez piquant',8,'images/1.jpeg','Grande',2),
('pomme de terre',7,'images/1.jpeg','Moyenne',2),
('merguez piquant',14,'images/1.jpeg','XXL',4),
('pomme de terre',12,'images/1.jpeg','XXL',5);

select *from Pizza;

-- -- Table composition
create table IF NOT EXISTS  Composition (
	IDPizza int not null,
	IDIngreds int not null,
	primary key (IDPizza,IDIngreds),
	foreign key (IDPizza) REFERENCES Pizza(IDPizza),
	foreign key (IDIngreds) REFERENCES Ingredients(IDIngreds)
);
insert into Composition VALUES 
(1,1),(1,7),(1,8),(1,20),(1,9),(1,6),
(3,1),(3,7),(3,8),(3,20),(3,9),(3,6),
(5,1),(5,7),(5,8),(5,20),(5,9),(5,6),
(2,1),(2,22),(2,15),(2,11),(2,24),
(4,1),(4,22),(4,15),(4,11),(4,24),
(6,1),(6,22),(6,15),(6,11),(6,24);
select *from Composition;

-- --Table Commandes
create TABLE IF NOT EXISTS Commandes (
	IdCom int not null,
	IdClient int not null,
	IDPizza int not null,
	DateCommande datetime,
	QteCom int not null,
	primary key (IdCom,IdClient),
	FOREIGN KEY (IDClient) REFERENCES Client(IDClient),
	FOREIGN KEY (IDPizza)  REFERENCES Pizza (IDPizza)
);

-- Insertion des données

show tables;

