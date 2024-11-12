// bank.cpp
#include <iostream>
#include <map>
#include <string>
#include "Purse.h"

int main() {
    std::cout << "Welcome to Ye Olde Bank of Merry England\n";

    std::map<std::string, Purse> vault;

    // Prompt user for number of accounts
    int numAccounts;
    std::cout << "How many accounts? ";
    std::cin >> numAccounts;
    std::cin.ignore();

    // Loop to get account details
    for (int i = 0; i < numAccounts; ++i) {
        std::string accountName;
        std::cout << "Name account " << i << ": ";
        std::getline(std::cin, accountName);

        Purse account;
        std::cout << "Enter your initial deposit (e.g., £3 4s5d): ";
        std::cin >> account;
        std::cin.ignore();

        if (!std::cin) {
            std::cerr << "Invalid input format. Please enter the amount as £ pounds shillings pence.\n";
            return 1;
        }

        std::cout << "Account " << accountName << " created with " << account << "\n";
        vault[accountName] = account;
    }

    // Calculate total and display each account
    Purse total;
    std::cout << "\nAccount List\n二二\n";
    for (const auto& [accountName, account] : vault) {
        std::cout << accountName << " with " << account << "\n";
        total += account;
    }

    // Display the total balance in the bank
    std::cout << "\nTotal in bank is " << total << std::endl;

    return 0;
}

