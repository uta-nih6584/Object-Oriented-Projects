#ifndef TIMER_H
#define TIMER_H

#include "clock.h"
#include <stdexcept>

class Timer : public Clock {
public:
    Timer(int hours, int minutes, int seconds) : Clock(hours, minutes, seconds) {}
    virtual ~Timer() {}

    void tic() override;
};

#endif // TIMER_H
