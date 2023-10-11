if "%OS%"=="Windows_NT" setlocal
gradlew compileJava && cd app/build/classes/java/main && java bountyhunter.App
else
./gradlew compileJava && cd app/bin/main && java bountyhunter.App