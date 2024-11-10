#ifndef PURSE_H
#define PURSE_H

#include <iostream>
#include <compare>

class Purse {
private:
    int _pence;   // Number of pence (pennies)
    int _shillings;  // Number of shillings
    int _pounds;  // Number of pounds

    void rationalize();

public:
    // Constructor with default values
    Purse(int pounds = 0, int shillings = 0, int pence = 0);

    // Friend function for output
    friend std::ostream& operator<<(std::ostream& os, const Purse& purse);

    // Overloaded comparison operators
    std::strong_ordering operator<=>(const Purse& other) const;

    // Overloaded increment operators
    Purse& operator++(); // Pre-increment
    Purse operator++(int); // Post-increment

    // Overloaded arithmetic operators
    Purse operator+(const Purse& other) const;
    Purse operator-(const Purse& other) const;

    // Compound assignment operators
    Purse& operator+=(const Purse& other);
    Purse& operator-=(const Purse& other);

    // Getter functions (optional)
    int getPounds() const { return _pounds; }
    int getShillings() const { return _shillings; }
    int getPence() const { return _pence; }
};

#endif // PURSE_H

