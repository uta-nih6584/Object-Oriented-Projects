#include <iostream>
#include <map>
#include <string>
#include "Purse.h"

int main() {
    std::map<std::string, Purse> vault;

    // Ask how many accounts to create
    int numAccounts;
    std::cout << "How many accounts? ";
    std::cin >> numAccounts;
    std::cin.ignore();  // Ignore the newline character left by std::cin

    // Create each account
    for (int i = 0; i < numAccounts; ++i) {
        std::string accountName;
        std::cout << "Name Account " << i << ": ";
        std::getline(std::cin, accountName);  // Read full name of account
        
        int pounds, shillings, pence;
        std::cout << "Enter Your Initial Deposit (pounds shillings pence): ";
        std::cin >> pounds >> shillings >> pence;
        std::cin.ignore();  // Ignore the newline character left by std::cin

        Purse account(pounds, shillings, pence);
        vault[accountName] = account;

        std::cout << "Account " << accountName << " created with " << account << ".\n";
    }

    // Initialize a default Purse object to hold the total balance
    Purse total;

    // Print account list and add up the total balance
    std::cout << "\nAccount list:\n";
    for (const auto& [accountName, account] : vault) {
        std::cout << accountName << ": " << account << "\n";
        total += account;
    }

    // Print total balance in bank
    std::cout << "\nTotal in bank is: " << total << std::endl;

    return 0;
}

