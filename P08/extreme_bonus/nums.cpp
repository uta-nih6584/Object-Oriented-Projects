#include <iostream>
#include <string>
#include <vector>
#include <cctype>
#include <algorithm>
#include <random>

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

    std::random_device rd;
    std::default_random_engine eng(rd());
    std::shuffle(numbers.begin(), numbers.end(), eng);
    std::sort(words.begin(), words.end());

    std::cout << "Numbers (shuffled):" << std::endl;
    for (const auto& number : numbers) {
        std::cout << number << std::endl;
    }

    std::cout << "Words (sorted):" << std::endl;
    for (const auto& word : words) {
        std::cout << word << std::endl;
    }

    return 0;
}
