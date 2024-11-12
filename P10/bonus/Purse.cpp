// Purse.cpp
#include "Purse.h"
#include <iostream>

Purse::Purse(int pounds, int shillings, int pence)
    : _pounds(pounds), _shillings(shillings), _pence(pence) {
    rationalize();
}

void Purse::rationalize() {
    if (_pence >= 12) {
        _shillings += _pence / 12;
        _pence %= 12;
    }
    if (_shillings >= 20) {
        _pounds += _shillings / 20;
        _shillings %= 20;
    }
}

// Overload << operator to output in the required £ format
std::ostream& operator<<(std::ostream& os, const Purse& purse) {
    os << "£" << purse._pounds << " " << purse._shillings << "s" << purse._pence << "d";
    return os;
}

// Overload >> operator to input using # format and rationalize after reading
std::istream& operator>>(std::istream& is, Purse& purse) {
    char poundSign, sChar, dChar;
    is >> poundSign >> purse._pounds >> purse._shillings >> sChar >> purse._pence >> dChar;
    purse.rationalize();
    return is;
}

// Overload += to add balances correctly
Purse& Purse::operator+=(const Purse& other) {
    _pounds += other._pounds;
    _shillings += other._shillings;
    _pence += other._pence;
    rationalize();
    return *this;
}
