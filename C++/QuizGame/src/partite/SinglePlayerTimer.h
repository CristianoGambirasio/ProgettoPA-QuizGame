#ifndef PARTITE_SINGLEPLAYERTIMER_H_
#define PARTITE_SINGLEPLAYERTIMER_H_
#include "../timer/Timer.h"
#include "SinglePlayer.h"

class SinglePlayerTimer : public Timer,public SinglePlayer{
public:
	SinglePlayerTimer(int seconds, std::string path);
	void start();
	virtual ~SinglePlayerTimer();
};

#endif
