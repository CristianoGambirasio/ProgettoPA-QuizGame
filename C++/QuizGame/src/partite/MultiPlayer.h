#ifndef PARTITE_MULTIPLAYER_H_
#define PARTITE_MULTIPLAYER_H_
#include "SinglePlayer.h"

class MultiPlayer : public SinglePlayer{
	int nGiocatori;
	std::string path;
public:
	MultiPlayer(int nGiocatori, std::string path);
	void start();
	virtual ~MultiPlayer();
};

#endif
