@echo off
set CURRENT_DIR=%CD%
set BATCHFILE_DIR=%~dp0

for %%d in (%~dp0.) do set Directory=%%~fd
for %%d in (%~dp0..) do set ParentDirectory=%%~fd

cd /D %BATCHFILE_DIR%

rmdir /S /Q "%ParentDirectory%\src\main\python\omnisci"

thrift-0.11.0.exe -r --gen py mapd.thrift

xcopy /s /e /Y "%BATCHFILE_DIR%\gen-py\*" "%ParentDirectory%\src\main\python"
del /Q "%ParentDirectory%\src\main\python\__init__.py"
rmdir /S /Q "%BATCHFILE_DIR%\gen-py"

cd /D %CURRENT_DIR%