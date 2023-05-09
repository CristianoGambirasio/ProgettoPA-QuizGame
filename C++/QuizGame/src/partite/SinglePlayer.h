#ifndef SINGLEPLAYER_H_
#define SINGLEPLAYER_H_
#include <string>

class SinglePlayer{
public:
	std::string path;
	int nDomande;
	SinglePlayer(std::string path);
	virtual void start();
	bool showDomanda(int i);
	virtual ~SinglePlayer();
};

#endif
