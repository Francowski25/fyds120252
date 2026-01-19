create database ds120252;
use ds120252;

CREATE TABLE tUser (
    idUser CHAR(36) PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    surName VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tRefreshToken (
    idRefreshToken CHAR(36) PRIMARY KEY,
    token VARCHAR(500) NOT NULL UNIQUE,
    expiration DATETIME NOT NULL,
    idUser CHAR(36) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fkRefreshUser
        FOREIGN KEY (idUser)
        REFERENCES tUser(idUser)
        ON DELETE CASCADE
);

select * from tUser;
select * from tRefreshToken;
