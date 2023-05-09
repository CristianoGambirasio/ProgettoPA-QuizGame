#ifndef UTILS_CONFIGREADER_H_
#define UTILS_CONFIGREADER_H_
#include <string>

class ConfigReader {
	std::string path;
public:
	ConfigReader(std::string path);
	int getNDomande();
	virtual ~ConfigReader();
};

#endif
