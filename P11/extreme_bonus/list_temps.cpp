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

    // Main loop for querying the temperature data
    while (true) {
        // Read the start date
        std::cout << "Enter start date (YYYY/MM/DD): ";
        Date startDate;
        std::cin >> startDate;

        if (std::cin.fail()) {
            std::cerr << "Invalid start date format. Please try again.\n";
            std::cin.clear();  // Clear the error flag
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');  // Ignore incorrect input
            continue;  // Restart the loop if the input is invalid
        }

        // Read the end date
        std::cout << "Enter end date (YYYY/MM/DD): ";
        Date endDate;
        std::cin >> endDate;

        if (std::cin.fail()) {
            std::cerr << "Invalid end date format. Please try again.\n";
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            continue;
        }

        if (endDate < startDate) {
            std::cerr << endDate << " is earlier than " << startDate << ". Please try again.\n";
            continue;  // Restart the loop if the end date is earlier than the start date
        }

        // Iterate through the map, starting at the first date and stopping at the last date
        std::cout << "\nDate        Temperature (°F)\n";
        std::cout << "-------------------------------\n";

        auto it = temps.find(startDate);
        if (it == temps.end()) {
            // If the start date is not found, print a message and continue
            std::cout << startDate << " is not in the database!\n";
        } else {
            // Iterate until we find a date greater than the end date
            for (; it != temps.end() && it->first <= endDate; ++it) {
                std::cout << it->first << "  " << std::fixed << std::setprecision(1) << it->second << '\n';
            }
        }

        std::cout << std::endl;
    }

    return 0;
}
