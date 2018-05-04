#!/bin/bash

file=log.log
date=`date '+%Y-%m-%d %H:%M:%S'`

if ping -n -c 4 -q 8.8.8.8 >/dev/null; then
  echo " $date ping 8.8.8.8 : Correcto " >> $file  
else
  echo " $date ping 8.8.8.8 : Incorrecto " >> $file
fi

if ping -n -c 4 -q 172.20.222.107 >/dev/null; then 
	echo " $date ping 172.20.222.107 : Correcto " >> $file
else
	echo " $date ping 172.20.222.107 : Incorrecto" >> $file
fi

echo " El servidor srv1 en el IP 10.1.1.1 en $date 
ha tenido conexión con los siguientes nodos en su red local: " >>$file

if ping -n -c 2 -q 10.1.1.1 >/dev/null; then
	echo " Hay conexión con el nodo 10.1.1.1, con una latencia de: " >> $file
else
	echo " No hay conexión con el nodo 10.1.1.1 " >> $file
fi

echo  "El servidor srv2 en el IP 10.1.1.2 en $date 
ha tenido conexión con los siguientes nodos en su red local: " >>$file

if ping -n -c 2 -q 10.1.1.2 >/dev/null; then
	echo " Hay conexión con el nodo 10.1.1.2, con una latencia de: " >> $file
else
	echo " No hay conexión con el nodo 10.1.1.2 " >> $file
fi

echo " El servidor srv03 en el IP 10.1.1.3 en $date 
ha tenido conexión con los siguientes nodos en su red local: " >>$file

if ping -n -c 2 -q 10.1.1.3 >/dev/null; then
	echo " Hay conexión con el nodo 10.1.1.3, con una latencia de: " >> $file
else
	echo " No hay conexión con el nodo 10.1.1.3 " >> $file
fi

echo " El servidor srv4 en el IP 10.1.1.4 en $date 
ha tenido conexión con los siguientes nodos en su red local: " >>$file

if ping -n -c 2 -q 10.1.1.4 >/dev/null; then
	echo " Hay conexión con el nodo 10.1.1.4, con una latencia de: " >> $file
else
	echo " No hay conexión con el nodo 10.1.1.4 " >> $file
fi

echo " El servidor srv5 en el IP 10.1.1.5 en $date 
ha tenido conexión con los siguientes nodos en su red local: " >>$file

if ping -n -c 2 -q 10.1.1.5 >/dev/null; then
	echo " Hay conexión con el nodo 10.1.1.5, con una latencia de: " >> $file
else
	echo " No hay conexión con el nodo 10.1.1.5 " >> $file
fi

watch -n 30 ./log.sh