#ifndef TIMER_TIMER_H_
#define TIMER_TIMER_H_

class Timer {
	bool stop=false;
	bool timerExpired;
	int tSec;
public:
	Timer(int t);
	void run();
	bool getTimerExpired();
	void stopTimer();
	virtual ~Timer();
};

#endif
