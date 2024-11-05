#include "clock.h"
#include <iostream>
#include <cstdlib>
#include <thread>
#include <chrono>

int main(int argc, char* argv[]) {
    if (argc != 4) {
        std::cerr << "usage: clock <hour> <minutes> <seconds>" << std::endl;
        return -1;
    }

    try {
        int hours = std::stoi(argv[1]);
        int minutes = std::stoi(argv[2]);
        int seconds = std::stoi(argv[3]);
        Clock clock(hours, minutes, seconds);

        std::cout << "Enter 'q' to quit:" << std::endl;

        while (true) {
            clock.print();
            if (std::cin.rdbuf()->in_avail() > 0) {
                std::string input;
                std::getline(std::cin, input);
                if (input == "q") break;
            }
            clock.tic();
            std::this_thread::sleep_for(std::chrono::seconds(1));
        }
    } catch (const std::out_of_range& e) {
        std::cerr << e.what() << std::endl;
        return -2;
    }

    return 0;
}
