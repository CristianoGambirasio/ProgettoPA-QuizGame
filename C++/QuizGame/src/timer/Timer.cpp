#include "Timer.h"
#include <thread>
#include <chrono>
#include <iostream>

std::thread thread;

Timer::Timer(int t) {

	this->timerExpired = false;
	this->tSec = t;

}

void Timer::run(){
	this->stop = false;
	thread = std::thread([&]{
		std::this_thread::sleep_for(std::chrono::milliseconds(10));//Make sure to print question before
		std::cout<<'\n';
		for(int i=0;i<tSec;i++){
			if(stop){
				break;
			}
			std::cout<<"\x1b[A \r    \r"<<tSec-i<<"\n";
			std::this_thread::sleep_for(std::chrono::seconds(1));
		}
		if(!stop){
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
	thread.detach();
	stop=true;
}

