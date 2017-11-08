@ECHO OFF
SET ThisScriptsDirectory=%~dp0
SET PowerShellScriptPath=%ThisScriptsDirectory%Inactive90Days.ps1
SET outname=%1
SET outfile=%ThisScriptsDirectory%%outname%
echo %outfile%
PowerShell -NoProfile -ExecutionPolicy Bypass  -Command "& {Start-Process PowerShell -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File """"%PowerShellScriptPath%"""" """"%outfile%""""'}" 
