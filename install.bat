echo ::======================
echo ::以后台方式构建打包为jar
echo ::======================
set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_72
set CLASSPATH=.;%JAVA_HOME%\lib\tools.jar
set Path=C:\Program Files\apache-maven-3.3.9;%JAVA_HOME%\bin;%Path%
@echo off

echo [INFO] build and install modules.
call mvn clean install -Dmaven.test.skip=true -X
pause