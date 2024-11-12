#include "Purse.h"
#include <iostream>

// Initialize the static pound symbol as a standard UTF-8 encoded string
const std::string Purse::pound_utf8 = "£";  // Direct UTF-8 pound symbol

Purse::Purse(int pounds, int shillings, int pence)
    : _pounds(pounds), _shillings(shillings), _pence(pence) {
    rationalize();  // Ensure values are valid (e.g., pence < 12, shillings < 20)
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

// Overload << operator to output in the £ format
std::ostream& operator<<(std::ostream& os, const Purse& purse) {
    os << Purse::pound_utf8 << purse._pounds << " " << purse._shillings << "s " << purse._pence << "d";
    return os;
}

// Overload >> operator to input using £ format and rationalize after reading
std::istream& operator>>(std::istream& is, Purse& purse) {
    std::string poundSign;
    char sChar, dChar;
    
    // Expect input format: £3 4s5d
    is >> poundSign >> purse._pounds >> purse._shillings >> sChar >> purse._pence >> dChar;

    // Check if pound sign matches
    if (poundSign != Purse::pound_utf8) {
        is.setstate(std::ios::failbit);  // Set failbit if pound symbol is incorrect
        return is;
    }

    purse.rationalize();  // Adjust values to ensure consistency (e.g., pence and shillings normalization)
    return is;
}

// Overload += to add balances correctly
Purse& Purse::operator+=(const Purse& other) {
    _pounds += other._pounds;
    _shillings += other._shillings;
    _pence += other._pence;
    rationalize();  // Normalize values after addition
    return *this;
}
