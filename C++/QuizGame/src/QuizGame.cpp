#include <string>
#include <iostream>
#include <dirent.h>
#include <list>
#include <algorithm>
#include <memory>

#include "partite/SinglePlayer.h"
#include "partite/MultiPlayer.h"
#include "partite/SinglePlayerTimer.h"
using namespace std;

const char* QuizDir = "../../Quiz/";

list<string> getQuizList();

int safeInput(string out,int min, int max);
string safeInput(string out, list<string> choices);

int main() {
	system("CLS");
	int scelta=-1;
	int nGiocatori;
	int seconds;
	string quiz;

	unique_ptr<SinglePlayer> partita;

	while(true){
		scelta = safeInput("Seleziona una modalita':"
				"\n1)Partita Singleplayer"
				"\n2)Partita Multiplayer"
				"\n3)Partita Singleplayer a tempo"
				"\n4)Esci dal gioco\n",0,4);

		if(scelta==4)
			break;

		quiz = QuizDir+safeInput("Inserisci il quiz che si vuole fare: \n",getQuizList());

		switch(scelta){
		case 1:
			partita= unique_ptr<SinglePlayer>(new SinglePlayer(quiz));
			partita->start();
			break;

		case 2:
			nGiocatori= safeInput("Inserisci il numero di giocatori (max 4): ",0,4);
			partita=unique_ptr<SinglePlayer>(new MultiPlayer(nGiocatori,quiz));
			partita->start();
			break;

		case 3:
			seconds= safeInput("Inserisci quanti secondi far durare la partita: ",1,3601);
			partita= unique_ptr<SinglePlayer>(new SinglePlayerTimer(seconds,quiz));
			partita->start();
			break;

		default:
			break;
		}
	}
	return 0;
}

int safeInput(string out, int min, int max){
	int res;
	cout<<out;
	cin>>res;
	while(!res || res<min || res> max){
		system("CLS");
		cout<<out;
		cin.clear();
		cin.ignore(40,'\n');
		cin>>res;
	}
	system("CLS");
	return res;
}

string safeInput(string out, list<string> choices){
	string res;
	do{
		cout<<out;
		for(auto choice : choices){
			cout<<choice<<"\n";
		}
		cin>>res;
		system("CLS");
		}while(find(choices.begin(), choices.end(), res) == choices.end()); //contains
	return res;
}

list<string> getQuizList(){
	DIR* dir_ptr;
	struct dirent *diread;
	list<string> content;

	if((dir_ptr = opendir(QuizDir)) != nullptr){
		while ((diread = readdir(dir_ptr)) != nullptr){
			content.push_back(diread->d_name);
		}
		closedir(dir_ptr);
	}
	content.remove(".");
	content.remove("..");
	return content;
}
