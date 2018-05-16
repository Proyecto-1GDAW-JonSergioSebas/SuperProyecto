/*bÃ¡sicas reglas de estilo:
todo en mayusculas
las constraints usan dos letras para referirse a cada tabla, que puedes ver en la lÃ­nea del create
el nombre de las FKs son la tabla misma, luego la otra, luego FK*/

--no realizar cambios a este fichero sin consultar EXTENSIVAMENTE con el equipo
--faltan constraints de tipo check, sinÃ³nimos, etc

--empezamos con la creaciÃ³n de tablas
DROP TABLE PLAYER CASCADE CONSTRAINTS;
DROP TABLE TEAM_OWNER CASCADE CONSTRAINTS;
DROP TABLE DB_ADMIN CASCADE CONSTRAINTS;
DROP TABLE DB_USER CASCADE CONSTRAINTS;
DROP TABLE TEAM CASCADE CONSTRAINTS;
DROP TABLE GAME_RESULT CASCADE CONSTRAINTS;
DROP TABLE GAME CASCADE CONSTRAINTS;
DROP TABLE MATCHSET CASCADE CONSTRAINTS;
DROP TABLE LEAGUE CASCADE CONSTRAINTS;
DROP TABLE CLASSIFICATION_TEMP CASCADE CONSTRAINTS;
DROP TABLE USERNAMES_TEMP;

CREATE TABLE TEAM_OWNER ( --TO
  ID_TO NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  USERNAME VARCHAR(12),
  PASSWD VARCHAR(12) NOT NULL,
  FULL_NAME VARCHAR(30) NOT NULL,
  TELEPHONE VARCHAR(9) NOT NULL,

  CONSTRAINT TO_FK PRIMARY KEY (ID_TO),
  CONSTRAINT TO_UK UNIQUE (USERNAME)
);

CREATE TABLE DB_ADMIN ( --AD
  ID_AD NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  USERNAME VARCHAR(12),
  PASSWD VARCHAR(12) NOT NULL,
  
  CONSTRAINT AD_FK PRIMARY KEY (ID_AD),
  CONSTRAINT AD_UK UNIQUE (USERNAME)
);

CREATE TABLE DB_USER ( --US
  ID_US NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  USERNAME VARCHAR(12),
  PASSWD VARCHAR(12) NOT NULL,
  
  CONSTRAINT US_FK PRIMARY KEY (ID_US),
  CONSTRAINT US_UK UNIQUE (USERNAME)
);

CREATE TABLE TEAM ( --TM
  ID_TM NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  TEAM_NAME VARCHAR(12) NOT NULL,
  NATIONALITY VARCHAR(2),
  TEAM_OWNER NUMBER(5) NOT NULL,
  BLOCKED NUMBER(1) DEFAULT 0,
  
  CONSTRAINT TM_PK PRIMARY KEY (ID_TM),
  CONSTRAINT TM_TO_FK FOREIGN KEY (TEAM_OWNER) REFERENCES TEAM_OWNER (ID_TO),
  CONSTRAINT TM_UK UNIQUE (TEAM_NAME)
);

CREATE TABLE PLAYER ( --PL
  ID_PL NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  FULL_NAME VARCHAR(30) NOT NULL,
  NICKNAME VARCHAR(12),
  SALARY NUMBER(8,2) NOT NULL,
  EMAIL VARCHAR(254) NOT NULL,
  TEAM NUMBER(5),

  CONSTRAINT PL_PK PRIMARY KEY (ID_PL),
  CONSTRAINT PL_TM_FK FOREIGN KEY (TEAM) REFERENCES TEAM (ID_TM),
  CONSTRAINT PL_SAL_MIN CHECK (SALARY>=10302.60),
  CONSTRAINT PL_UK UNIQUE (NICKNAME)
);

CREATE TABLE LEAGUE ( --LG
  ID_LG NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  LEAGUE_NAME VARCHAR(20) NOT NULL,
  OVER NUMBER(1) DEFAULT 0,
  
  CONSTRAINT LG_PK PRIMARY KEY (ID_LG)
);  

CREATE TABLE MATCHSET ( --MS
  ID_MS NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  LEAGUE NUMBER(5),
  
  CONSTRAINT MS_LG_FK FOREIGN KEY (LEAGUE) REFERENCES LEAGUE (ID_LG),
  CONSTRAINT MS_PK PRIMARY KEY (ID_MS)
);

CREATE TABLE GAME ( --GA
  ID_GA NUMBER(5) GENERATED ALWAYS AS IDENTITY,
  MATCHSET NUMBER(5) NOT NULL,
  DATE_TIME DATE,

  CONSTRAINT GA_MS_FK FOREIGN KEY (MATCHSET) REFERENCES MATCHSET (ID_MS),
  CONSTRAINT GA_PK PRIMARY KEY (ID_GA)
);

CREATE TABLE GAME_RESULT ( --GR
  TEAM NUMBER(5),
  GAME NUMBER(5),
  SCORE NUMBER(3),

  CONSTRAINT GR_TM_FK FOREIGN KEY (TEAM) REFERENCES TEAM (ID_TM),
  CONSTRAINT GR_GA_FK FOREIGN KEY (GAME) REFERENCES GAME (ID_GA),
  CONSTRAINT GR_PK PRIMARY KEY (TEAM, GAME)
);
/

--ahora vamos con la creacion de triggers y vistas
CREATE OR REPLACE PACKAGE TRIGGER_MT AS --este trigger existe solo para la creacion de las variable que contiene
  TEMP_PLAYER PLAYER%ROWTYPE := NULL; --las cuales existen solo para prevenir el error de tabla mutante en los triggers codificados mas adelante
  TEMP_ADMIN DB_ADMIN%ROWTYPE := NULL;
  TEMP_USER DB_USER%ROWTYPE := NULL;
  TEMP_OWNER TEAM_OWNER%ROWTYPE := NULL;
END;
/
CREATE OR REPLACE TRIGGER PLAYER_MUTATING_TABLE --este trigger existe solo para resolver el problema de tabla mutante en los siguientes dos triggers
AFTER INSERT OR UPDATE OF TEAM --se ejecuta antes que los dos siguientes triggers
ON PLAYER
FOR EACH ROW
BEGIN
  TRIGGER_MT.TEMP_PLAYER.SALARY := :NEW.SALARY; --y todo lo que hace es introducir los nuevos valores a la variable que declaramos antes
  TRIGGER_MT.TEMP_PLAYER.TEAM := :NEW.TEAM;
END;
/
CREATE OR REPLACE TRIGGER PLAYERS_PER_TEAM --este trigger limita la cantidad de miembros por equipo
AFTER INSERT OR UPDATE OF TEAM
ON PLAYER
DECLARE
  MEMBERS NUMBER;
BEGIN
  SELECT COUNT(*) INTO MEMBERS FROM PLAYER WHERE TEAM=TRIGGER_MT.TEMP_PLAYER.TEAM; --una select para calcular la cantidad de miembros actuales en el equipo
  IF MEMBERS >= 6 THEN --si eso es mayor que 6, entonces 
    RAISE_APPLICATION_ERROR(-20001, 'Se ha alcanzado el limite de miembros en un equipo.'); --salta la excepcion
  END IF;
END;
/
CREATE OR REPLACE TRIGGER TOTAL_TEAM_SALARY --este trigger limita el salario total de los miembros del equipo
AFTER INSERT OR UPDATE OF TEAM
ON PLAYER
DECLARE
  TEAM_SALARY NUMBER;
BEGIN
  SELECT SUM(SALARY) INTO TEAM_SALARY FROM PLAYER WHERE TEAM = TRIGGER_MT.TEMP_PLAYER.SALARY; --aqui calculamos la suma del salario de los ya existentes miembros del equipo
  TEAM_SALARY := TEAM_SALARY + TRIGGER_MT.TEMP_PLAYER.SALARY; --y aqui le sumamos a eso el salario del nuevo miembro
  IF TEAM_SALARY >= 200000 THEN --si la suma de los totales es mayor a 200000 entonces
    RAISE_APPLICATION_ERROR(-20002, 'El salario total del equipo no debe ser mayor a 200000.'); --salta la excepcion
  END IF;
END;
/
CREATE TABLE USERNAMES_TEMP ( --tabla temporal, que durante la ejecución de los siguientes triggers contiene los nombres de usuario de todos las cuentas en el sistema
  USERNAME VARCHAR2(12)
);
/
CREATE OR REPLACE TRIGGER DBADMIN_USERNAMES_TABLE
BEFORE INSERT OR UPDATE OF USERNAME ON DB_ADMIN
DECLARE
  CURSOR USERNAMES IS 
    SELECT USERNAME FROM DB_USER --simplemente selecciona el nombre de usuario de una tabla
    UNION
    SELECT USERNAME FROM DB_ADMIN --y los une con los de otra
    UNION
    SELECT USERNAME FROM TEAM_OWNER;
BEGIN
  FOR US IN USERNAMES LOOP 
    INSERT INTO USERNAMES_TEMP VALUES (US.USERNAME); --inserta en la tabla temporal cada nombre de usuario en existencia
  END LOOP;
END;
/
CREATE OR REPLACE TRIGGER ADMIN_MUTATING_TABLE --trigger para prevenir el error de table mutante en el siguiente trigger
AFTER INSERT OR UPDATE OF USERNAME ON DB_ADMIN
FOR EACH ROW
BEGIN
  TRIGGER_MT.TEMP_ADMIN.USERNAME := :NEW.USERNAME; --solo inserta el nuevo nombre de usuario en esta variable, declarada bastante antes en este fichero
END;
/
CREATE OR REPLACE TRIGGER UNIQUE_USERNAME_ADMIN --este trigger se asegura de que el nombre de usuario de un administrador es unico entre todos los tipos de cuenta, independientemente de las mayusculas y minusculas
AFTER INSERT OR UPDATE OF USERNAME ON DB_ADMIN
DECLARE
  CURSOR LIST IS SELECT USERNAME FROM USERNAMES_TEMP; --aqui llamamos a la vista de nombres de usuario declarada antes, y hacemos un cursor con sus contenidos
BEGIN
  FOR US IN LIST LOOP --por cada nombre de usuario en el cursor
    IF UPPER(TRIGGER_MT.TEMP_ADMIN.USERNAME) = UPPER(US.USERNAME) THEN --verificamos que no coincida con el nuevo nombre de usuario
      RAISE_APPLICATION_ERROR(-20003, 'El nombre de usuario ya está utilizado.'); --y si coincide, salta esta excepcion
    END IF;
  END LOOP;
  DELETE FROM USERNAMES_TEMP; --al finalizar, borra el contenido de la tabla temporal con los nombres de usuario
END; --los siguientes cuatro triggers son identicos a este y el anterior, solo para los otros tipos de cuenta, asi que los dejo sin comentar
/
CREATE OR REPLACE TRIGGER DBUSER_USERNAMES_TABLE
BEFORE INSERT OR UPDATE OF USERNAME ON DB_USER
DECLARE
  CURSOR USERNAMES IS 
    SELECT USERNAME FROM DB_USER --simplemente selecciona el nombre de usuario de una tabla
    UNION
    SELECT USERNAME FROM DB_ADMIN --y los une con los de otra
    UNION
    SELECT USERNAME FROM TEAM_OWNER;
BEGIN
  FOR US IN USERNAMES LOOP 
    INSERT INTO USERNAMES_TEMP VALUES (US.USERNAME); --inserta en la tabla temporal cada nombre de usuario en existencia
  END LOOP;
END;
/
CREATE OR REPLACE TRIGGER USER_MUTATING_TABLE 
AFTER INSERT OR UPDATE OF USERNAME ON DB_USER
FOR EACH ROW
BEGIN
  TRIGGER_MT.TEMP_USER.USERNAME := :NEW.USERNAME;
END;
/
CREATE OR REPLACE TRIGGER UNIQUE_USERNAME_DBUSER
AFTER INSERT OR UPDATE OF USERNAME ON DB_USER
DECLARE
  CURSOR LIST IS SELECT USERNAME FROM USERNAMES_TEMP;
BEGIN
  FOR US IN LIST LOOP
    IF UPPER(TRIGGER_MT.TEMP_USER.USERNAME) = UPPER(US.USERNAME) THEN
      RAISE_APPLICATION_ERROR(-20003, 'El nombre de usuario ya está utilizado.');       
    END IF;
  END LOOP;
  DELETE FROM USERNAMES_TEMP;
END;
/
CREATE OR REPLACE TRIGGER OWNER_USERNAMES_TABLE
BEFORE INSERT OR UPDATE OF USERNAME ON TEAM_OWNER
DECLARE
  CURSOR USERNAMES IS 
    SELECT USERNAME FROM DB_USER --simplemente selecciona el nombre de usuario de una tabla
    UNION
    SELECT USERNAME FROM DB_ADMIN --y los une con los de otra
    UNION
    SELECT USERNAME FROM TEAM_OWNER;
BEGIN
  FOR US IN USERNAMES LOOP 
    INSERT INTO USERNAMES_TEMP VALUES (US.USERNAME); --inserta en la tabla temporal cada nombre de usuario en existencia
  END LOOP;
END;
/
CREATE OR REPLACE TRIGGER OWNER_MUTATING_TABLE
AFTER INSERT OR UPDATE OF USERNAME ON TEAM_OWNER
FOR EACH ROW
BEGIN
  TRIGGER_MT.TEMP_OWNER.USERNAME := :NEW.USERNAME;
END;
/
CREATE OR REPLACE TRIGGER UNIQUE_USERNAME_OWNER
AFTER INSERT OR UPDATE OF USERNAME ON TEAM_OWNER
DECLARE
  CURSOR LIST IS SELECT USERNAME FROM USERNAMES_TEMP;
BEGIN
  FOR US IN LIST LOOP
    IF UPPER(TRIGGER_MT.TEMP_OWNER.USERNAME) = UPPER(US.USERNAME) THEN
      RAISE_APPLICATION_ERROR(-20003, 'El nombre de usuario ya está utilizado.');
    END IF;
  END LOOP;
  DELETE FROM USERNAMES_TEMP;
END;
/
CREATE TABLE CLASSIFICATION_TEMP ( --tabla temporal, solo contiene datos durante la ejecucion de los procesos que generan los datos de clasificacion
  TEAM_ID NUMBER(5) PRIMARY KEY,
  TEAM_NAME VARCHAR2(12),
  SCORE NUMBER(3)  
);
/