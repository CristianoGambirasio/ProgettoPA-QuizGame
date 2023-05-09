
#ifndef UTILS_QUESTIONREADER_H_
#define UTILS_QUESTIONREADER_H_
#include <string>
#include <vector>

class QuestionReader {
	std::string path;
public:
	QuestionReader(std::string path);
	std::vector<std::string> getDomanda(int nDomanda);
	virtual ~QuestionReader();
};

#endif
