#include "MultiPlayer.h"
#include <iostream>

MultiPlayer::MultiPlayer(int nGiocatori, std::string path): SinglePlayer(path){
	this->nGiocatori = nGiocatori;
}

void MultiPlayer::start(){
	for(int i=0;i<nGiocatori;i++){
		std::cout<<"Player"<<i+1<<"\n";
		SinglePlayer::start();
	}
}

MultiPlayer::~MultiPlayer() {
}

