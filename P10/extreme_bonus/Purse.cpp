#include "Purse.h"
#include <iostream>
#include <sstream>
#include <string>
#include <limits>

// Initialize the static constant for the pound symbol
const std::string Purse::pound_utf8 = "£";

// Constructor
Purse::Purse(int pounds, int shillings, int pence) : _pounds(pounds), _shillings(shillings), _pence(pence) {
    rationalize();  // Make sure the money is rationalized
}

// Rationalize the purse to ensure pence and shillings are within valid ranges
void Purse::rationalize() {
    if (_pence >= 12) {
        _shillings += _pence / 12;
        _pence = _pence % 12;
    }
    if (_shillings >= 20) {
        _pounds += _shillings / 20;
        _shillings = _shillings % 20;
    }
}

// Overload the output stream operator to print the purse in a human-readable format
std::ostream& operator<<(std::ostream& os, const Purse& purse) {
    os << Purse::pound_utf8 << purse._pounds << " " << purse._shillings << "s " << purse._pence << "d";
    return os;
}

// Overload the input stream operator to read input in the format £<pounds> <shillings>s<pence>d
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
        is.clear();  // Clear error state
        is.ignore(std::numeric_limits<std::streamsize>::max(), '\n');  // Ignore bad input
        return is;  // Return immediately to prompt again
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
        is.clear();  // Clear error state
        is.ignore(std::numeric_limits<std::streamsize>::max(), '\n');  // Ignore bad input
        return is;  // Return immediately to prompt again
    }

    // Set values in purse object
    purse._pounds = pounds;
    purse._shillings = shillings;
    purse._pence = pence;

    // Rationalize the values (e.g., converting extra pence to shillings)
    purse.rationalize();

    return is;
}

// Overload the comparison operator for <=> (spaceship operator)
std::strong_ordering Purse::operator<=>(const Purse& other) const {
    if (_pounds != other._pounds) return _pounds <=> other._pounds;
    if (_shillings != other._shillings) return _shillings <=> other._shillings;
    return _pence <=> other._pence;
}

// Overload the increment operator (prefix)
Purse& Purse::operator++() {
    ++_pence;
    rationalize();  // Make sure the purse is rationalized
    return *this;
}

// Overload the increment operator (postfix)
Purse Purse::operator++(int) {
    Purse temp = *this;
    ++(*this);  // Use the prefix increment to do the work
    return temp;
}

// Overload the addition operator
Purse Purse::operator+(const Purse& other) const {
    return Purse(_pounds + other._pounds, _shillings + other._shillings, _pence + other._pence);
}

// Overload the subtraction operator
Purse Purse::operator-(const Purse& other) const {
    return Purse(_pounds - other._pounds, _shillings - other._shillings, _pence - other._pence);
}

// Overload the compound addition operator
Purse& Purse::operator+=(const Purse& other) {
    _pounds += other._pounds;
    _shillings += other._shillings;
    _pence += other._pence;
    rationalize();  // Rationalize after addition
    return *this;
}

// Overload the compound subtraction operator
Purse& Purse::operator-=(const Purse& other) {
    _pounds -= other._pounds;
    _shillings -= other._shillings;
    _pence -= other._pence;
    rationalize();  // Rationalize after subtraction
    return *this;
}

// Subscript operator to access values based on string key
int Purse::operator[](const std::string& key) const {
    if (key == "pounds") return _pounds;
    if (key == "shillings") return _shillings;
    if (key == "pence") return _pence;
    throw std::invalid_argument("Invalid key for accessing Purse values.");
}

