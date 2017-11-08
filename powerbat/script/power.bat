@ECHO OFF
SET ThisScriptsDirectory=%~dp0
SET PowerShellScriptPath=%ThisScriptsDirectory%Inactive60Days.ps1
SET outname=%1
SET outfile=%ThisScriptsDirectory%%outname%
PowerShell -NoProfile -ExecutionPolicy Bypass  -Command "& {Start-Process PowerShell -ArgumentList '-NoProfile -ExecutionPolicy Bypass -File """"%PowerShellScriptPath%"""" """"%outfile%""""'}" 
