@echo off

setlocal

rem set LP=%cd%
set CLASSPATH=%~dp0;%~dp0lib\*
set APP_MAIN=com.sgota.tool.tkcg.CommandLineMain

java -cp "%CLASSPATH%" %APP_MAIN% %*

endlocal

