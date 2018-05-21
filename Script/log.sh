#!/bin/bash

file=log.log

date=`date '+%Y-%m-%d %H:%M:%S'`


if ping -n -c 4 -q 8.8.8.8 >>$file; then

  echo " \n $date ping 8.8.8.8 : Correcto " >> $file  

else

  echo " \n $date ping 8.8.8.8 : Incorrecto " >> $file

fi


echo " \n El servidor srv1 en el IP 10.1.1.1 en $date 
ha tenido conexion con los siguientes nodos en su red local
con una latencia de: " >>$file

if ping -n -c 2 -q 10.1.1.1 >>$file; then

	echo " \n Hay conexion con el nodo 10.1.1.1 " >> $file

else

	echo " \n No hay conexion con el nodo 10.1.1.1 " >> $file

fi


echo  " \n El servidor srv2 en el IP 10.1.2.1 en $date 
ha tenido conexion con los siguientes nodos en su red local 
con una latencia de: " >>$file

if ping -n -c 2 -q 10.1.2.1 >>$file; then

	echo " \n Hay conexion con el nodo 10.1.2.1 " >> $file

else

	echo " \n No hay conexion con el nodo 10.1.2.1 " >> $file

fi


echo " \n El servidor srv03 en el IP 10.1.3.1 en $date 
ha tenido conexion con los siguientes nodos en su red local 
con una latencia de: " >>$file

if ping -n -c 2 -q 10.1.3.1 >>$file; then

	echo " \n Hay conexion con el nodo 10.1.3.1 " >> $file

else

	echo " \n No hay conexion con el nodo 10.1.3.1 " >> $file

fi


echo " \n El servidor srv4 en el IP 10.1.4.1 en $date 
ha tenido conexion con los siguientes nodos en su red local 
con una latencia de: " >>$file

if ping -n -c 2 -q 10.1.4.1 >>$file; then

	echo " \n Hay conexion con el nodo 10.1.4.1 " >> $file

else

	echo " \n No hay conexion con el nodo 10.1.4.1 " >> $file

fi


echo " \n El servidor srv5 en el IP 10.1.5.1 en $date 
ha tenido conexion con los siguientes nodos en su red local 
con una latencia de: " >>$file

if ping -n -c 2 -q 10.1.5.1 >>$file; then

	echo " \n Hay conexion con el nodo 10.1.5.1 " >> $file

else

	echo " \n No hay conexion con el nodo 10.1.5.1 " >> $file
	
fi
