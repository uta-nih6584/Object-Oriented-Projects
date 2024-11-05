#include "clock.h"
#include <iomanip>

Clock::Clock(int hours, int minutes, int seconds) : _hours(hours), _minutes(minutes), _seconds(seconds) {
    // Time validation
    if (_hours < 0 || _minutes < 0 || _seconds < 0 || _minutes >= 60 || _seconds >= 60) {
        throw std::invalid_argument("Invalid time values");
    }
}

void Clock::print() const {
    std::cout << std::setw(2) << std::setfill('0') << _hours << ":"
              << std::setw(2) << std::setfill('0') << _minutes << ":"
              << std::setw(2) << std::setfill('0') << _seconds << std::endl;
}

void Clock::tic() {
    _seconds--; // Default implementation to decrement seconds
    if (_seconds < 0) {
        _seconds = 59;
        _minutes--;
        if (_minutes < 0) {
            _minutes = 59;
            _hours--;
            if (_hours < 0) {
                _hours = 0; // Prevent negative hours, might want to change this logic
            }
        }
    }
}
