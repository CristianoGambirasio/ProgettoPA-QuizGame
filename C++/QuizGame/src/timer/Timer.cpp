#include "Timer.h"
#include <thread>
#include <chrono>
#include <iostream>

std::thread thread; //thread in cui si esegue il timer

Timer::Timer(int t) {

	this->timerExpired = false;
	this->tSec = t;

}

void Timer::run(){
	this->stop = false;
	thread = std::thread([&]{
		//Si assicura che il timer sia stampato come ultima cosa
		std::this_thread::sleep_for(std::chrono::milliseconds(10));
		std::cout<<'\n';

		//Conta tSec secondi
		for(int i=0;i<tSec;i++){
			if(stop){//Smette di contare se stop=true (Classe distrutta)
				break;
			}
			std::cout<<"\x1b[A \r    \r"<<tSec-i<<"\n";//Stampa del tempo rimanente
			std::this_thread::sleep_for(std::chrono::seconds(1));
		}
		if(!stop){//Stampa tempo scaduto solo se la classe è ancora in utilizzo
			std::cout<<"Tempo scaduto\n";
			this->timerExpired = true;
		}
	});
}

bool Timer::getTimerExpired(){
	return this->timerExpired;
}

void Timer::stopTimer(){
	this->stop = true;
}

Timer::~Timer() {
	//Scollega il thread dal programma
	thread.detach();
	stop=true;//Dice al metodo run di non stampare più niente
}

