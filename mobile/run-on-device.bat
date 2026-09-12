@echo off
REM run-on-device.bat — ejecutar pruebas mobile en dispositivo Android (Redmi 13)
setlocal

:: Configuración (ajusta si hace falta)
set UDID=SKGMIXM
set DEVICE=Redmi 13
set PLATFORM_VERSION=12

:: Buscar primer APK en la carpeta apk\
set "APK="
for %%f in ("%~dp0apk\*.apk") do (
  set "APK=%%~f"
  goto :found
)
:found
if "%APK%"=="" (
  echo No se encontro ningun APK en "%~dp0apk".
  pause
  exit /b 1
)
echo APK encontrado: %APK%

echo
echo Comprobando dispositivos ADB...
adb devices
echo Si tu dispositivo no aparece, habilita Depuracion USB y reconecta.
pause

echo Iniciando Appium en una nueva ventana (si tienes npx/appium instalado)...
start "Appium" cmd /k "npx appium"
echo Esperando 5 segundos para que Appium arranque...
timeout /t 5 /nobreak >nul

echo Ejecutando pruebas Maven contra el dispositivo...
mvn -f "%~dp0pom.xml" -Dmobile.skip=false -Dappium.server.url=http://127.0.0.1:4723/wd/hub -Ddevice.name="%DEVICE%" -Dplatform.version=%PLATFORM_VERSION% -Dudid=%UDID% -Dmobile.apk.path="%APK%" clean test

echo
if %ERRORLEVEL% NEQ 0 (
  echo Maven devolvio error. Revisa los logs en target\surefire-reports.
) else (
  echo Pruebas finalizadas correctamente.
)
echo Presiona una tecla para salir...
pause
exit /b 0

:: Ejecutar contra una app ya instalada no está soportado en esta suite: use APK en mobile\apk en su lugar.
