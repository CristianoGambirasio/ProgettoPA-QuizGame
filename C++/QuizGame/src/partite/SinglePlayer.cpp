#include "SinglePlayer.h"
#include "../utils/ConfigReader.h"
#include "../utils/QuestionReader.h"
#include <iostream>
#include <vector>
#include <algorithm>

SinglePlayer::SinglePlayer(std::string path) {
	this->path = path;
	ConfigReader cr(path);
	this->nDomande = cr.getNDomande();
}

void SinglePlayer::start(){
	int nRispGiuste = 0;

	for(int i=0;i<nDomande;i++){
		if(showDomanda(i)){
			nRispGiuste+=1;
		}
	}
	std::cin.ignore();
	std::cout<<"Risultato: "<<nRispGiuste<<"/"<<nDomande<<std::endl<<"Premi invio per continuare...\n"<<std::flush;
	std::cin.get();
	system("CLS");
}

bool SinglePlayer::showDomanda(int i){

	QuestionReader qr(path);

	std::string rispGiusta;
	std::string domanda;
	int rispData;

	std::vector<std::string> q = qr.getDomanda(i+1);
	rispGiusta = q[1];
	domanda = q[0];

	q.erase(q.begin());
	std::random_shuffle(q.begin(), q.end());

	std::cout<<"Domanda "<<i+1<<")"<<domanda<<"\n"
			"1) "<<q[0]<<"\t\t\t2) "<<q[1]<<"\n"
			"3) "<<q[2]<<"\t\t\t4) "<<q[3]<<"\n\n";
	std::cin>>rispData;
	while(!rispData || (rispData<1||rispData>4)){
		system("CLS");
		std::cout<<"Domanda "<<i+1<<")"<<domanda<<"\n"
				"1) "<<q[0]<<"\t\t\t2) "<<q[1]<<"\n"
				"3) "<<q[2]<<"\t\t\t4) "<<q[3]<<"\n\n";
		std::cin.clear();
		std::cin.ignore(40,'\n');
		std::cin>>rispData;
	}
	system("CLS");

	if(q[rispData-1] == rispGiusta){
		return true;
	}
	return false;

}

SinglePlayer::~SinglePlayer() {
}

