#include "clock.h"
#include <iomanip>
#include <stdexcept>

Clock::Clock(int hours, int minutes, int seconds) 
    : _hours(hours), _minutes(minutes), _seconds(seconds) {
    if (_hours < 0 || _hours > 23 || _minutes < 0 || _minutes > 59 || _seconds < 0 || _seconds > 59) {
        throw std::out_of_range("Invalid time value");
    }
}

void Clock::tic() {
    _seconds++;
    if (_seconds == 60) {
        _seconds = 0;
        _minutes++;
        if (_minutes == 60) {
            _minutes = 0;
            _hours = (_hours + 1) % 24;
        }
    }
}

void Clock::print() const {
    std::cout << std::setw(2) << std::setfill('0') << _hours << ":"
              << std::setw(2) << std::setfill('0') << _minutes << ":"
              << std::setw(2) << std::setfill('0') << _seconds << std::endl;
}