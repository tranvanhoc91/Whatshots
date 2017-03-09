 @echo off
for /R %%i in (.) do (
if exist %%i\.svn rd %%i\.svn /s /q
)
echo successful