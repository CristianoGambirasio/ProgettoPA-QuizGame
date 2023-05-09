#include "QuestionReader.h"
#include <fstream>
#include <vector>

QuestionReader::QuestionReader(std::string path) {
	this->path = path;
}

std::vector<std::string> QuestionReader::getDomanda(int nDomanda){
	std::fstream file;
	std::string temp;
	std::vector<std::string> res;

	file.open((path+"/"+std::to_string(nDomanda)+".txt"), std::ios::in);

	for(int i=0;i<5;i++){
		if(file.is_open()){
			std::getline(file,temp);
		}
		res.push_back(temp);
	}

	file.close();
	return res;
}

QuestionReader::~QuestionReader() {
}

