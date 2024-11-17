#ifndef DATE_H
#define DATE_H

#include <iostream>
#include <iomanip>
#include <sstream>

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

    // Friend function to read Date from input stream in the format YYYY/MM/DD
    friend std::istream& operator>>(std::istream& in, Date& date) {
        char delimiter1, delimiter2;
        in >> date._year >> delimiter1 >> date._month >> delimiter2 >> date._day;

        // Check if the stream has failed or delimiters are not '/'
        if (in.fail() || delimiter1 != '/' || delimiter2 != '/') {
            in.setstate(std::ios::failbit);  // Set failbit if input is invalid
        }

        return in;
    }

    // Declaration of the compare method (no definition here)
    int compare(const Date& rhs) const;
};

#endif
