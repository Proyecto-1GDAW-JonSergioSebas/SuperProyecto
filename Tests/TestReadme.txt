Test_Account_Type_Admin_3:
La captura de pantalla añadida muestra el test en el cual el tipo de cuenta es Admin

Este test comprueba que el tipo de cuenta sea el correcto,
en este caso el tipo de cuenta es Admin así que tendrá que devolver 3
si,por ejemplo, el resultado esperado fuera Owner, el resultado sería 2
, si fuera User, el resultado sería 1
y si la cuenta no existiera el resultado sería 0.

La ruta al .test que contiene ese test es:SuperProyecto/build/test/classes/DB/DBProceduresTest.class

Los datos que hay que introducir son el nombre de usuario(us) de la cuenta y la contraseña(pw)
en expresult se colocará 0,1,2 o 3 dependiendo de lo que se espere.



----------------------------------

Test_Team_ID_Aon_1:
La captura de pantalla añadida muestra el test en el cual el id del equipo es 1

Este test comprueba que el ID del equipo que devuelve sea el correcto,
en este caso el nombre del equipo es Aon, y en la base de datos tiene el ID 1
con otros equipos el resultado sería diferente,
para los equipos que ya existen sería:
Aon     1;
Rua     2;
Kolme   3;
Papat   4;
Bost    5;
Isii    6;
Seachd  7;
Eight   8;

La ruta al .test que contiene este test es :SuperProyecto/build/test/classes/DB/DBTeamTest.class

El dato que hay que introducir es el nombre del equipo(teamname)
en expresult se colocará el ID del equipo que corresponda con ese nombre
