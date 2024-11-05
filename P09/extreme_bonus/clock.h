#ifndef CLOCK_H
#define CLOCK_H

#include <iostream>
#include <stdexcept>

class Clock {
protected:  // Protected to allow derived classes access
    int _hours;
    int _minutes;
    int _seconds;

public:
    Clock(int hours, int minutes, int seconds); // Constructor
    virtual ~Clock() {} // Virtual destructor

    virtual void tic(); // Virtual method for ticking the clock
    void print() const; // Print the current time
};

#endif // CLOCK_H
