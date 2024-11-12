#ifndef PURSE_H
#define PURSE_H

#include <iostream>
#include <compare>

class Purse {
private:
    int _pence;   
    int _shillings;  
    int _pounds;  

    void rationalize();

public:
   
    Purse(int pounds = 0, int shillings = 0, int pence = 0);

    friend std::ostream& operator<<(std::ostream& os, const Purse& purse);
    friend std::istream& operator>>(std::istream& is, Purse& purse);
    
    std::strong_ordering operator<=>(const Purse& other) const;

    Purse& operator++(); 
    Purse operator++(int); 

    Purse operator+(const Purse& other) const;
    Purse operator-(const Purse& other) const;

    Purse& operator+=(const Purse& other);
    Purse& operator-=(const Purse& other);

    int getPounds() const { return _pounds; }
    int getShillings() const { return _shillings; }
    int getPence() const { return _pence; }
};

#endif 
