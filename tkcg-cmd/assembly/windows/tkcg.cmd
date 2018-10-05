@echo off

setlocal

set DEPLOY_DIR=%~dp0

set CLASSPATH=%DEPLOY_DIR%;%DEPLOY_DIR%\lib\*

set APP_MAIN=com.sgota.tkcg.CommandLineMain

set TEMPLATE=%DEPLOY_DIR%/template

java -cp "%CLASSPATH%" %APP_MAIN% -t %TEMPLATE% %*

endlocal
