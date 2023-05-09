cd C++/QuizGameCompiled

g++ -c ../QuizGame/src/*.cpp ../QuizGame/src/utils/*.cpp ../QuizGame/src/timer/*.cpp ../QuizGame/src/partite/*.cpp 
g++ -o C++Launcher.exe *.o
C++Launcher.exe

pause