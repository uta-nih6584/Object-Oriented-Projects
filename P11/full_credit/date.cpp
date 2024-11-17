#include "date.h"

int Date::compare(const Date& rhs) const {
    if (_year != rhs._year) return _year - rhs._year;
    if (_month != rhs._month) return _month - rhs._month;
    return _day - rhs._day;
}

