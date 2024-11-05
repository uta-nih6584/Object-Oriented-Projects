#include "clock.h"
#include <iostream>
#include <cstdlib>
#include <thread>
#include <chrono>
#include <atomic>

std::atomic<bool> running(true);

void inputThread() {
    std::string input;
    while (running) {
        std::getline(std::cin, input);
        if (input == "q") {
            running = false;
        }
    }
}

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

        std::thread t(inputThread); // Start the input thread

        while (running) {
            clock.print();
            std::cout << std::flush;
            clock.tic();
            std::this_thread::sleep_for(std::chrono::seconds(1));
        }

        t.join(); // Wait for the input thread to finish
    } catch (const std::out_of_range& e) {
        std::cerr << e.what() << std::endl;
        return -2;
    }

    return 0;
}
