#ifndef CLOCK_H
#define CLOCK_H

#include <iostream>
#include <stdexcept>
#include <iomanip>

class Clock {
public:
    Clock(int hours, int minutes, int seconds);
    virtual ~Clock() = default;
    void print() const;
    void tic();

private:
    int _hours, _minutes, _seconds;
};

#endif

