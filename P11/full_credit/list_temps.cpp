#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include <map>
#include "date.h"

typedef double Temp;

int main(int argc, char* argv[]) {
    if (argc != 2) {
        std::cerr << "usage: " << argv[0] << " <data file>\n";
        return 1;
    }

    std::string filename = argv[1];
    std::ifstream file(filename);

    if (!file) {
        std::cerr << "Can't open input file " << filename << '\n';
        return 1;
    }

    std::map<Date, Temp> temps;

    // Reading data from CSV file
    std::string line;
    while (std::getline(file, line)) {
        std::istringstream stream(line);
        std::string continent, country, state, region, month, day, year, temp;

        // Read 8 fields from CSV
        std::getline(stream, continent, ',');
        std::getline(stream, country, ',');
        std::getline(stream, state, ',');
        std::getline(stream, region, ',');
        std::getline(stream, month, ',');
        std::getline(stream, day, ',');
        std::getline(stream, year, ',');
        std::getline(stream, temp, ',');

        // Convert relevant fields and add to map
        Date date(std::stoi(year), std::stoi(month), std::stoi(day));
        temps[date] = std::stod(temp);
    }

    // Main loop
    while (std::cin) {
        std::cout << "Enter start date (YYYY MM DD): ";
        int startYear, startMonth, startDay;
        if (!(std::cin >> startYear >> startMonth >> startDay)) break;

        std::cout << "Enter end date (YYYY MM DD): ";
        int endYear, endMonth, endDay;
        if (!(std::cin >> endYear >> endMonth >> endDay)) break;

        Date startDate(startYear, startMonth, startDay);
        Date endDate(endYear, endMonth, endDay);

        // Iterate through the map
        std::cout << "\nDate          Temperature (°F)\n";
        std::cout << "-------------------------------\n";

        for (auto it = temps.lower_bound(startDate); it != temps.end() && it->first <= endDate; ++it) {
            std::cout << it->first << "  " << std::fixed << std::setprecision(1) << it->second << '\n';
        }
        std::cout << std::endl;
    }

    return 0;
}

