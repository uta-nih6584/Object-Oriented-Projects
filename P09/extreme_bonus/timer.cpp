#include "timer.h"
#include "timer_expired.h" // Include the new exception header

void Timer::tic() {
    _seconds--; // Decrement seconds
    if (_seconds < 0) {
        _seconds = 59;
        _minutes--;
        if (_minutes < 0) {
            _minutes = 59;
            _hours--;
            if (_hours < 0) {
                throw Timer_expired(); // Throw custom exception when timer expires
            }
        }
    }
}
