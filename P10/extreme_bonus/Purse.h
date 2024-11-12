#ifndef PURSE_H
#define PURSE_H

#include <iostream>
#include <string>

class Purse {
public:
    Purse(int pounds = 0, int shillings = 0, int pence = 0);

    // Overloaded operators
    friend std::ostream& operator<<(std::ostream& os, const Purse& purse);
    friend std::istream& operator>>(std::istream& is, Purse& purse);
    Purse& operator+=(const Purse& other);

    // Static pound symbol
    static const std::string pound_utf8;

private:
    int _pounds;
    int _shillings;
    int _pence;
    void rationalize();
};

#endif
