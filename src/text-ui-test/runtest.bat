@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT 2>nul

REM compile the code into the bin folder
javac -cp ..\src\java -Xlint:none -d ..\bin ..\src\java\Terry\UI\*.java ..\src\java\Terry\Command\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Terry.Terry < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
IF ERRORLEVEL 1 (
    echo Test result: FAILED
    exit /b 1
) else (
    echo Test result: PASSED
    exit /b 0
)