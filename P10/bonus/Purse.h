#ifndef PURSE_H
#define PURSE_H

#include <iostream>

class Purse {
private:
    int _pounds;
    int _shillings;
    int _pence;

    void rationalize();

public:
    Purse(int pounds = 0, int shillings = 0, int pence = 0);
    
    // Friend functions for I/O
    friend std::ostream& operator<<(std::ostream& os, const Purse& purse);
    friend std::istream& operator>>(std::istream& is, Purse& purse);

    Purse& operator+=(const Purse& other);
    // Other member functions and operators...
};

#endif // PURSE_H

