CREATE OR REPLACE PACKAGE LOGIN AS 
  PROCEDURE GET_TYPE (UN VARCHAR2, PW VARCHAR2, AC OUT NUMBER); --este proceso verifica si los datos introducidos se corresponded con el usuario y contraseña de alguna cuenta en la base de datos, y devuelve un numero representando el tipo de cuenta
END;
  
CREATE OR REPLACE PACKAGE BODY LOGIN AS
  PROCEDURE GET_TYPE (UN VARCHAR2, PW VARCHAR2, AC OUT NUMBER) IS
  CNT NUMBER;
    BEGIN
      SELECT COUNT(*) INTO CNT FROM DB_USER WHERE USERNAME = UN AND PASSWD = PW; --si el usuario y contraseña introducidos se encuentran en una de las filas de la table DB_USER, AC es 1
      IF CNT = 1 THEN
        AC := 1;
      ELSE
        SELECT COUNT(*) INTO CNT FROM TEAM_OWNER WHERE USERNAME = UN AND PASSWD = PW;--si se encuentran en la tabla TEAM_OWNER, AC es 2
        IF CNT = 1 THEN
          AC := 2;
        ELSE
          SELECT COUNT(*) INTO CNT FROM DB_ADMIN WHERE USERNAME = UN AND PASSWD = PW;--si se encuentran en DB_ADMIN, AC es 3
          IF CNT = 1 THEN
            AC := 3;
          ELSE 
            AC := 0; --y si no se encuentra en ninguna, AC es 0
          END IF;
        END IF;
      END IF;
    END GET_TYPE;  
END;

----

CREATE OR REPLACE PACKAGE CLASSIFICATION AS  --este paquete contiene los procediminetos necesarios para llevar a java los datos necesarios para la clasificacion
  PROCEDURE GET_CLASSIFICATION (ID_LG NUMBER, CLASSIFICATION_OUT OUT SYS_REFCURSOR); --este es al que uno llama desde java, que usa a los otros dos, devuelve la clasificacion entera como sysrefcursor
  PROCEDURE GET_SCORE (ID_LG NUMBER); --este usa al siguiente, y realmente lo unico que hace es pasarle la ID de los juegos de la liga
  PROCEDURE GET_RESULT(ID_GA NUMBER); --este ultimo es el que determina los ganadores y asigna puntos a la tabla temporal
END;
  
CREATE OR REPLACE PACKAGE BODY CLASSIFICATION AS
  
  PROCEDURE GET_CLASSIFICATION (ID_LG NUMBER, CLASSIFICATION_OUT OUT SYS_REFCURSOR) IS
      CURSOR TEAM_IDS IS SELECT ID_TM FROM TEAM WHERE ID_TM IN --cursor de los equipos que participan en la liga
        (SELECT GAME FROM GAME_RESULT WHERE GAME IN
        (SELECT ID_GA FROM GAME WHERE MATCHSET IN
        (SELECT ID_MS FROM MATCHSET WHERE LEAGUE=ID_LG)))
        ORDER BY ID_TM;
      SCORE NUMBER(3);
      TM_NAME VARCHAR(12);
    BEGIN      
      FOR TM_CUR IN TEAM_IDS LOOP --por cada equipo, hacemos lo siguiente
        SELECT TEAM_NAME INTO TM_NAME FROM TEAM WHERE TEAM.ID_TM=TM_CUR.ID_TM; --obtenemos el nombre del equipo
        INSERT INTO CLASSIFICATION_TEMP VALUES (TM_CUR.ID_TM, TM_NAME, 0); --y lo insertamos en la tabla temporal, junto a su ID y (TEMPORALMENTE) 0 puntos
      END LOOP;
      GET_SCORE(ID_LG); --llamamos al proceso que eventualmente maneja las puntuaciones de los equipos
      OPEN CLASSIFICATION_OUT FOR SELECT * FROM CLASSIFICATION_TEMP;
      --DELETE FROM CLASSIFICATION_TEMP;
    END;
  
  PROCEDURE GET_SCORE (ID_LG NUMBER) IS --lo unico que hace este proceso es llamar a otro, enviandole los juegos de la liga como parametro
      CURSOR GAMES IS SELECT ID_GA FROM GAME WHERE MATCHSET IN --este es un cursor de todos los juegos en la liga
        (SELECT ID_MS FROM MATCHSET WHERE LEAGUE=ID_LG);
    BEGIN     
      FOR GA IN GAMES LOOP
        GET_RESULT(GA.ID_GA); --este es el proceso importante
      END LOOP;
    END;
    
  PROCEDURE GET_RESULT(ID_GA NUMBER) IS --este proceso compara las puntuaciones de los dos equipos de la liga, y asigna puntos al ganador (o a ambos equipos si empatan)
      T1 GAME_RESULT%ROWTYPE;
      T2 GAME_RESULT%ROWTYPE;
      CURSOR RESULTS IS SELECT * FROM GAME_RESULT WHERE GAME=ID_GA; --cursor con los dos resultados de cada juego
    BEGIN
    
        OPEN RESULTS; --abro el cursor 
        FETCH RESULTS INTO T1;
        FETCH RESULTS INTO T2; --guardo sus dos filas en estas variables
        
        IF T1.SCORE > T2.SCORE THEN -- si t1 tiene mas puntos que t2, se suman 2 puntos a t1
          UPDATE CLASSIFICATION_TEMP SET SCORE = SCORE + 2 WHERE TEAM_ID = T1.TEAM;
        ELSIF T2.SCORE > T1.SCORE THEN --lo mismo para t2
          UPDATE CLASSIFICATION_TEMP SET SCORE = SCORE + 2 WHERE TEAM_ID = T2.TEAM;
        ELSE --si no se cumplen ninguna de las condiciones anteriores es por que ambos tienen la misma puntuacion
          UPDATE CLASSIFICATION_TEMP SET SCORE = SCORE + 1 WHERE TEAM_ID IN (T1.TEAM, T2.TEAM); --y en ese caso le sumamos 1 a ambos
        END IF;
        CLOSE RESULTS;--cierro el cursor
        
    END;
    
END;