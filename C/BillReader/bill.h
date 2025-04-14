#include <stdio.h>
#ifndef BILL_H
#define BILL_H

/**
struct to hold the 3 parts of a phone number for each call record
*/
struct PhoneNumber{
    int areaCode;
    int exchangeCode;
    int lineNumber;
};

/**
struct to hold the 3 parts of a date and the whole date for each call record
*/
struct Date{
    int year;
    int month;
    int day;
    long wholeDate;
};

/**
struct for a single call record, and all aspects of one
*/
struct Call{
    struct Date callDate;
    struct PhoneNumber callNumber;
    int time;
    int duration;
    double cost;
};

/**
struct for the entire bill, with all aspects of the bill and an array of the calls (as an array of structs)
*/
struct Bill{
    char* name;
    char* street;
    char* state;
    struct Call* calls;
    int numCalls;
    int capacity;
};

/**
Interprets input command. Separated from main.c due to issues reading summarize line with no argumnets
*/
struct Bill check(FILE* in, FILE* out, char* currCmd, struct Bill current_bill, char read);

/**
Double capacity of array if full
*/
struct Call* expandCapacity(struct Call* old, int currentCapacity);

/**
Determine which call will come first in the array 
*/
int compareTimes(struct Call first, struct Call second);

/**
Add a call to the array, in the correct sorted order
*/
void addToArray(struct Call** calls, struct Call toAdd, int* currentCapacity, int* numberOfCalls);

/**
Reads a whole line call record
*/
struct Call read_call(FILE* in);

/**
Reads entire bill file, and calls read call to read each call record
*/
struct Bill load_bill(FILE* file, char* fileName, FILE* out);

/**
Convert integers to corresponding months
*/
char* intToMonth(int m);

/**
Convert months to corresponding integers
*/
int monthToInt(char* month);

/**
Calculate average duration of a call array
*/
double avgDuration(struct Call* arr, int size);

/**
Calculate average cost of a call array
*/
double avgCost(struct Call* arr, int size);

/**
Function to summarize a bill, checking if it's a whole summary or just 1 month
*/
char summarize_bill(FILE* in, FILE* out, struct Bill bill);

/**
Function to summarize just 1 month
*/
void summarize_month(FILE* out, struct Bill bill, int m);

/**
intermediate call for finding the average cost, so we can run it for whole bill or specific month
*/
void runAvgCost(FILE* out, struct Bill bill);

/**
intermediate call for finding the average cost, so we can run it for whole bill or specific month
*/
void runAvgDuration(FILE* out, struct Bill bill);

/**
converts integer to a string to account for preceding 0's
*/
char* intToString(int num, int places);

/**
Function for displaying an entire bill
*/
void displayBill(FILE* out, struct Bill bill);

/**
free any memory unaccounted for
*/
void clear_bill(struct Bill bill);

#endif