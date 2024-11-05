#include "timer.h"
#include <iostream>
#include <cstdlib>
#include <thread>
#include <chrono>

int main(int argc, char* argv[]) {
    if (argc != 4) {
        std::cerr << "usage: timer <hours> <minutes> <seconds>" << std::endl;
        return -1;
    }

    try {
        int hours = std::stoi(argv[1]);
        int minutes = std::stoi(argv[2]);
        int seconds = std::stoi(argv[3]);
        Timer timer(hours, minutes, seconds);

        std::cout << "Enter 'q' to quit:" << std::endl;

        while (true) {
            timer.print();
            if (std::cin.rdbuf()->in_avail() > 0) {
                std::string input;
                std::getline(std::cin, input);
                if (input == "q") break; // Allow quitting the timer
            }
            timer.tic();
            std::this_thread::sleep_for(std::chrono::seconds(1)); // Wait for 1 second
        }
    } catch (const std::out_of_range& e) {
        std::cerr << e.what() << std::endl;
        return -2;
    } catch (const std::runtime_error& e) {
        std::cout << e.what() << std::endl; // Print timer expiration message
    }

    return 0; // Normal exit
}
