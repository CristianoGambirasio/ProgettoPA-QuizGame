#include "SinglePlayerTimer.h"
#include <iostream>

SinglePlayerTimer::SinglePlayerTimer(int seconds, std::string path): Timer(seconds), SinglePlayer(path) {
}

void SinglePlayerTimer::start(){
	int nRispGiuste = 0;

	this->run();

	for(int i=0;i<nDomande;i++){
		if(this->getTimerExpired()){
			break;
		}
		if(showDomanda(i)){
			nRispGiuste+=1;
		}
	}

	this->stopTimer();
	std::cin.ignore();
	std::cout<<"Risultato: "<<nRispGiuste<<"/"<<nDomande<<std::endl<<"Premi invio per continuare...\n"<<std::flush;
	std::cin.get();
	system("CLS");
}

SinglePlayerTimer::~SinglePlayerTimer() {
}

