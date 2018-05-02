CREATE OR REPLACE PACKAGE LOGIN AS
  PROCEDURE GET_TYPE (UN VARCHAR2, PW VARCHAR2, AC OUT NUMBER); --UN es el nombre de usuario, PW la contraseña, y AC el tipo de cuenta
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