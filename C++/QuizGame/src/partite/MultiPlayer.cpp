#include "MultiPlayer.h"
#include <iostream>

MultiPlayer::MultiPlayer(int nGiocatori, std::string path): SinglePlayer(path){
	this->nGiocatori = nGiocatori;
}

void MultiPlayer::start(){
	//Esegue il metodo di SinglePlater per ogni giocatore
	for(int i=0;i<nGiocatori;i++){
		std::cout<<"Player"<<i+1<<"\n";
		SinglePlayer::start();//Accedo al metodo della superclasse oscurato dal metodo override di Multiplayer
	}
}

MultiPlayer::~MultiPlayer() {
}

