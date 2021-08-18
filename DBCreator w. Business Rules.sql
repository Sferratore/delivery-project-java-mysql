
#Creazione Schema e uso DB
CREATE SCHEMA Deliveries;
USE Deliveries;

#Creazione Tabelle
CREATE TABLE Ristorante(
	Nome VARCHAR(30) UNIQUE,
	Indirizzo VARCHAR(60) UNIQUE,
	DisponibilitaPosti BOOLEAN NOT NULL,
	DisponibilitaOrdini BOOLEAN NOT NULL,
	NumMaxPrenotazioni INT NOT NULL,
	PRIMARY KEY (Nome, Indirizzo)
);

CREATE TABLE Dipendente(
Codice VARCHAR(20),
Nome VARCHAR(20) NOT NULL,
Cognome VARCHAR(20) NOT NULL,
AnniEsperienza INT NOT NULL,
ShortCurriculum VARCHAR(1000) NOT NULL,
PRIMARY KEY(Codice)
);

CREATE TABLE Assunzione(
Dipendente VARCHAR(20),
NomeRistorante VARCHAR(30),
IndirizzoRistorante VARCHAR(60),
DataPresaServizio DATE NOT NULL,
TipoContratto VARCHAR(30) NOT NULL,
FOREIGN KEY(IndirizzoRistorante) REFERENCES Ristorante(Indirizzo) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(NomeRistorante) REFERENCES Ristorante(Nome) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(Dipendente) REFERENCES Dipendente(Codice) ON DELETE NO ACTION ON UPDATE CASCADE,
PRIMARY KEY(Dipendente,NomeRistorante,IndirizzoRistorante)
);

CREATE TABLE SocietaEsterna(
Nome VARCHAR(20) NOT NULL,
PartitaIVA VARCHAR(50) NOT NULL,
AmministratoreDelegato VARCHAR(60) NOT NULL,
PRIMARY KEY(Nome)
);

CREATE TABLE Rider(
Codice VARCHAR(20) NOT NULL,
Nome VARCHAR(20) NOT NULL,
DataPrimoImpiego DATE NOT NULL,
Disponibile BOOLEAN NOT NULL,
PRIMARY KEY(Codice)
);

CREATE TABLE Veicolo(
Targa VARCHAR(10) NOT NULL,
Tipo VARCHAR(20) NOT NULL,
Rider VARCHAR(20) NOT NULL,
PRIMARY KEY(Targa),
FOREIGN KEY(Rider) REFERENCES  Rider(Codice) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Impiego(
Rider VARCHAR(20) NOT NULL,
SocietaEsterna VARCHAR(20) NOT NULL,
QuotaOraria FLOAT NOT NULL,
DataInizio DATE NOT NULL,
PRIMARY KEY(Rider,SocietaEsterna),
FOREIGN KEY(Rider) REFERENCES Rider(Codice),
FOREIGN KEY(SocietaEsterna) REFERENCES SocietaEsterna(Nome) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Cliente(
Codice VARCHAR(20),
Nome VARCHAR(20) NOT NULL,
Indirizzo VARCHAR(60) NOT NULL,
NumeroOrdini INT NOT NULL,
NumeroTelefono VARCHAR(20) NOT NULL UNIQUE,
DataRegistrazione DATE NOT NULL,
PRIMARY KEY(Codice)
);

CREATE TABLE Prenotazione(
Cliente VARCHAR(20),
NomeRistorante VARCHAR(30),
IndirizzoRistorante VARCHAR(60),
Posto VARCHAR(10) NOT NULL,
DataPrenotazione DATE NOT NULL,
Ora TIME NOT NULL,
PRIMARY KEY(Cliente,NomeRistorante,IndirizzoRistorante),
FOREIGN KEY(Cliente) REFERENCES Cliente(Codice) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(NomeRistorante) REFERENCES Ristorante(Nome) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(IndirizzoRistorante) REFERENCES Ristorante(Indirizzo) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Valutazione(
Codice INT AUTO_INCREMENT,
Rider VARCHAR(20),
Cliente VARCHAR(20),
Voto INT NOT NULL,
DataValutazione DATE NOT NULL,
Testo VARCHAR(1000) NOT NULL,
PRIMARY KEY(Codice),
FOREIGN KEY(Rider) REFERENCES Rider(Codice) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(Cliente) REFERENCES Cliente(Codice) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE ServizioDelivery(
Tipo VARCHAR(45),
Descrizione VARCHAR(600),
CadenzaSettimanale VARCHAR(150),
DataInizio DATE NOT NULL,
NomeRistorante VARCHAR(30),
IndirizzoRistorante VARCHAR(60),
PRIMARY KEY(Tipo,NomeRistorante,IndirizzoRistorante),
FOREIGN KEY(NomeRistorante) REFERENCES Ristorante(Nome) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(IndirizzoRistorante) REFERENCES Ristorante(Indirizzo) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE Ordine(
Codice INT AUTO_INCREMENT,
Rider VARCHAR(20),
Dipendente VARCHAR(20),
NomeRistorante VARCHAR(30),
IndirizzoRistorante VARCHAR(60),
Cliente VARCHAR(20) NOT NULL,
DataOrdine DATE NOT NULL,
Costo FLOAT NOT NULL,
Descrizione VARCHAR(1000) NOT NULL,
Acquisto VARCHAR(1000) NOT NULL,
StatoConsegna VARCHAR(50) NOT NULL,
OrarioConsegnaPresunto TIME NOT NULL,
OrarioConsegnaEffettivo TIME,
PRIMARY KEY(Codice, DataOrdine),
FOREIGN KEY(NomeRistorante) REFERENCES Ristorante(Nome) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(IndirizzoRistorante) REFERENCES Ristorante(Indirizzo) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(Rider) REFERENCES Rider(Codice) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(Cliente) REFERENCES Cliente(Codice) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY(Dipendente) REFERENCES Dipendente(Codice) ON DELETE NO ACTION ON UPDATE CASCADE
);


#Creazione Eventi
DELIMITER $$
CREATE EVENT resetCodiceOrdine 
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIME
DO
	ALTER TABLE Ordine AUTO_INCREMENT = 1;
$$
DELIMITER;

   
#Creazione Trigger
DELIMITER $$
CREATE TRIGGER IncaricatoreSingoloIns
    AFTER INSERT 
    ON Ordine FOR EACH ROW
BEGIN
    IF NEW.Rider IS NOT NULL AND NEW.Dipendente IS NOT NULL THEN
	SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = "Non puoi inserire un Ordine avente sia un Rider(Incaricato Esterno) che un Dipendente(Incaricato Interno)!";
    END IF;
END$$    
DELIMITER;


DELIMITER $$
CREATE TRIGGER IncaricatoreSingoloUp
    BEFORE UPDATE 
    ON Ordine FOR EACH ROW
BEGIN
    IF NEW.Rider IS NOT NULL AND NEW.Dipendente IS NOT NULL THEN
	SIGNAL SQLSTATE '45002' SET MESSAGE_TEXT = "Non puoi inserire un Ordine avente sia un Rider(Incaricato Esterno) che un Dipendente(Incaricato Interno)!";
    END IF;
END$$    
DELIMITER ;


DELIMITER $$
CREATE TRIGGER DisponibilitaOrdiniFalse
    BEFORE INSERT 
    ON Ordine FOR EACH ROW
BEGIN
    if (SELECT DisponibilitaOrdini
    FROM Ristorante
    WHERE NEW.NomeRistorante = Nome AND NEW.IndirizzoRistorante = Indirizzo) = FALSE THEN
	SIGNAL SQLSTATE '45003' SET MESSAGE_TEXT = "Non puoi effettuare un ordine a questo ristorante, la sua disponibilità ordini è false!";
    END IF;
END$$    
DELIMITER ;

DELIMITER $$
CREATE TRIGGER DisponibilitaPostiFalse
    BEFORE INSERT 
    ON Ordine FOR EACH ROW
BEGIN
    if (SELECT DisponibilitaPosti
    FROM Ristorante
    WHERE NEW.NomeRistorante = Nome AND NEW.IndirizzoRistorante = Indirizzo) = FALSE THEN
	SIGNAL SQLSTATE '45006' SET MESSAGE_TEXT = "Non puoi effettuare una prenotazione a questo ristorante, la sua disponibilità posti è false!";
    END IF;
END$$    
DELIMITER ;


DELIMITER $$
CREATE TRIGGER VotoTraUnoECinqueIns
	BEFORE INSERT
    ON Valutazione FOR EACH ROW
BEGIN
	IF NEW.Voto < 1 OR NEW.Voto > 5 THEN
    SIGNAL SQLSTATE '45004' SET MESSAGE_TEXT = "Inserimento su Valutazione errato: Il dominio di voto è un intero tra 1 e 5.";
    END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER VotoTraUnoECinqueUp
	BEFORE UPDATE
    ON Valutazione FOR EACH ROW
BEGIN
	IF NEW.Voto < 1 OR NEW.Voto > 5 THEN
    SIGNAL SQLSTATE '45005' SET MESSAGE_TEXT = "Modifica su Valutazione errata: Il dominio di un voto è un intero tra 1 e 5.";
    END IF;
END$$
DELIMITER ;