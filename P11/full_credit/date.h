#ifndef DATE_H
#define DATE_H

#include <iostream>
#include <iomanip>

class Date {
private:
    int _year, _month, _day;

public:
    // Constructor with default values (January 1, 1970)
    Date(int year = 1970, int month = 1, int day = 1) 
        : _year(year), _month(month), _day(day) {}

    // Comparison operators
    inline bool operator==(const Date& rhs) const { return (compare(rhs) == 0); }
    inline bool operator!=(const Date& rhs) const { return (compare(rhs) != 0); }
    inline bool operator< (const Date& rhs) const { return (compare(rhs) <  0); }
    inline bool operator<=(const Date& rhs) const { return (compare(rhs) <= 0); }
    inline bool operator> (const Date& rhs) const { return (compare(rhs) >  0); }
    inline bool operator>=(const Date& rhs) const { return (compare(rhs) >= 0); }

    // Friend function to print Date in YYYY/MM/DD format
    friend std::ostream& operator<<(std::ostream& os, const Date& date) {
        char old_fill = os.fill('0');
        os << date._year << '/'
           << std::setw(2) << date._month << '/'
           << std::setw(2) << date._day;
        os.fill(old_fill);  // Restore fill character
        return os;
    }

    // Declaration of the compare method (no definition here)
    int compare(const Date& rhs) const;
};

#endif