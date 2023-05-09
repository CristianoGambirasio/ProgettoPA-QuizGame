#include "ConfigReader.h"
#include <fstream>

ConfigReader::ConfigReader(std::string path) {
	this->path=path+"/config.txt";
}

int ConfigReader::getNDomande(){
	std::string res;
	std::fstream file;

	file.open(path, std::ios::in);

	if(file.is_open()){
		std::getline(file,res);
	}
	file.close();
	return std::stoi(res);
}

ConfigReader::~ConfigReader() {
	// TODO Auto-generated destructor stub
}

