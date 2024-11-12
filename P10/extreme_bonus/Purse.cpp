#include "Purse.h"
#include <iostream>
#include <sstream>
#include <string>

// Initialize the static pound symbol as a standard UTF-8 encoded string
const std::string Purse::pound_utf8 = "\u00A3";  // UTF-8 pound symbol £

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
    std::string inputLine;
    std::getline(is, inputLine);  // Read the full line input

    // Strip leading and trailing whitespace
    inputLine.erase(0, inputLine.find_first_not_of(" "));
    inputLine.erase(inputLine.find_last_not_of(" ") + 1);

    // Check if the input starts with the pound symbol
    if (inputLine.substr(0, 2) != Purse::pound_utf8) {
        is.setstate(std::ios::failbit);  // Set failbit if pound symbol is missing
        std::cerr << "Invalid input format. Please enter the amount as £<pounds> <shillings>s<pence>d.\n";
        return is;
    }

    // Remove the pound symbol from the string
    inputLine.erase(0, 2);  // Erase the 2-byte pound symbol

    // Now we use a stringstream to break up the remaining input
    std::istringstream ss(inputLine);
    int pounds = 0, shillings = 0, pence = 0;
    char sChar = '\0', dChar = '\0';

    // Try to extract the components (pounds, shillings, and pence)
    if (!(ss >> pounds >> shillings >> sChar >> pence >> dChar) ||
        sChar != 's' || dChar != 'd') {
        is.setstate(std::ios::failbit);  // Set failbit if anything is wrong
        std::cerr << "Invalid input format. Please enter the amount as £<pounds> <shillings>s<pence>d.\n";
        return is;
    }

    // Set values in purse object
    purse._pounds = pounds;
    purse._shillings = shillings;
    purse._pence = pence;

    // Rationalize the values (e.g., converting extra pence to shillings)
    purse.rationalize();

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
