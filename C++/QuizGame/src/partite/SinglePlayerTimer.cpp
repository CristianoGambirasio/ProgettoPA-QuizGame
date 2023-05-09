#include "SinglePlayerTimer.h"
#include <iostream>

SinglePlayerTimer::SinglePlayerTimer(int seconds, std::string path): Timer(seconds), SinglePlayer(path) {
}//Initializer list dei costruttori delle classi base

void SinglePlayerTimer::start(){//Override di start
	int nRispGiuste = 0;

	this->run();//Inizio Timer

	for(int i=0;i<nDomande;i++){
		if(this->getTimerExpired()){//Controllo che il timer non sia scaduto, altrimenti esco dal ciclo
			break;
		}
		if(showDomanda(i)){
			nRispGiuste+=1;
		}
	}

	this->stopTimer();//Fermo l'esecuzione del timer una volta finito il quiz
	std::cin.ignore();
	std::cout<<"Risultato: "<<nRispGiuste<<"/"<<nDomande<<std::endl<<"Premi invio per continuare...\n"<<std::flush;
	std::cin.get();
	system("CLS");
}

SinglePlayerTimer::~SinglePlayerTimer() {
}

