@echo off
set CURRENT_DIR=%CD%
set BATCHFILE_DIR=%~dp0

for %%d in (%~dp0.) do set Directory=%%~fd
for %%d in (%~dp0..) do set ParentDirectory=%%~fd

cd /D %BATCHFILE_DIR%

rmdir /S /Q "%ParentDirectory%\src\main\java\com\stlogic\omnisci\thrift"

thrift-0.11.0.exe -r --gen java mapd.thrift

xcopy /s /e /Y "%BATCHFILE_DIR%\gen-java\*" "%ParentDirectory%\src\main\java\"
rmdir /S /Q "%BATCHFILE_DIR%\gen-java"

cd /D %CURRENT_DIR%