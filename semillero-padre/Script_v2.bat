@echo off
::Realizado por: Javier Arias
echo *****SCRIPTv2******
::DECLARACION DE VARIABLES
set nombre-directorio-1=semillero-servicios
set nombre-directorio-2=semillero-ear

::COMPILANDO 1ER DIRECTORIO
start "%nombre-directorio-1%" /D "D:\Documents\GitHub\subir-txt\semillero-padre\semillero-servicios\" mvn clean install 
echo Si el proyecto %nombre-directorio-1% compilo con exito, presiona cualquier tecla...
pause >nul

::COMPILANDO 2DO DIRECTORIO
start "%nombre-directorio-2%" /D "D:\Documents\GitHub\subir-txt\semillero-padre\semillero-ear\" mvn clean install 
echo Si el proyecto %nombre-directorio-2% compilo con exito, presiona cualquier tecla...
pause >nul

::LIMPIAR ARCHIVO .ear y ear.deployed
cd /D "C:\Program Files\wildfly-15.0.1.Final\standalone\deployments" 
echo Limpiando directorio...
del /F /S /Q *.ear
del /F /S /Q *.deployed

::COPIAR ARCHIVO GENERADO A DESTINO WILDFLY
echo Copiando .ear
xcopy D:\Documents\GitHub\subir-txt\semillero-padre\semillero-ear\target\semillero-ear-1.0-SNAPSHOT.ear "C:\Program Files\wildfly-15.0.1.Final\standalone\deployments"  


::EJECUTANDO standalone.bat
echo Ejecutando .bat
cd /D "C:\Program Files\wildfly-15.0.1.Final\bin\"  
call standalone.bat
pause
:exit
