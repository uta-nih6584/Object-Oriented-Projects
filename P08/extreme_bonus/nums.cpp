#include <iostream>
#include <string>
#include <vector>
#include <cctype>

int main(int argc, char* argv[]) {
    std::vector<std::string> numbers;
    std::vector<std::string> words;

    for (int i = 1; i < argc; ++i) {
        std::string arg = argv[i];
        if (!arg.empty() && std::isdigit(arg[0])) {
            numbers.push_back(arg);
        } else {
            words.push_back(arg);
        }
    }

    std::cout << "Numbers:" << std::endl;
    for (const auto& number : numbers) {
        std::cout << number << std::endl;
    }

    std::cout << "Words:" << std::endl;
    for (const auto& word : words) {
        std::cout << word << std::endl;
    }

    return 0;
}

