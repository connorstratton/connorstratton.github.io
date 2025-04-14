#include <stdio.h>
#include <stdlib.h>
#include "bill.h"

/**
    CHECK FUNCTION
*/

/**
Interprets input command. Separated from main.c due to issues reading summarize line with no argumnets
*/
struct Bill check(FILE* in, FILE* out, char* currCmd, struct Bill current_bill, char read)
{
        struct Bill b = current_bill;     
        if (currCmd[0] == '#')                  //read comment
        {
            fscanf(in, "%[^\n]\n", currCmd);
        }
        else if (currCmd[0] == 'l')         //read load and launch load functions
        {
            fscanf(in, " %[^\n]\n", currCmd);
            FILE* tempFile;

            fprintf(out, "Command: load %s\n", currCmd);

            tempFile = fopen(currCmd, "r");

            if (tempFile == NULL)
            {
                printf("Error opening files.\n");
            }

            b = load_bill(tempFile, currCmd, out);

            fclose(tempFile);
        }
        else if (currCmd[0] == 's')             //read summarize
        {
            read = summarize_bill(in, out, b);      //launch summarize functions

            if (read != '0')                //if no arguments, manually run the next command that has already been read
            {
                currCmd[0] = read;
                check(in, out, currCmd, b, read);
            }
        }
        else if (currCmd[0] == 'a')             //read average command
        {
            fscanf(in, " %[^\n]\n", currCmd);
            if (currCmd[0] == 'c')              //if average cost
            {
                fprintf(out, "Command: average cost\n");
                runAvgCost(out, b);
            }
            else if(currCmd[0] == 'd')          //if average duration
            {
                fprintf(out, "Command: average duration\n");
                runAvgDuration(out, b);
            }
        }
        else if (currCmd[0] == 'd')             //read display
        {
            fprintf(out, "Command: display\n");

            displayBill(out, b);
        }

        return b;
}

/**
    FUNCTIONS FOR LOADING
*/

/**
Double capacity of array if full
*/
struct Call* expandCapacity(struct Call* old, int currentCapacity)
{
    struct Call* new = malloc((2 * currentCapacity) * sizeof(struct Call));

    for(int j = 0; j < currentCapacity; j++)
    {
        new[j] = old[j];
    }

    free(old);
    return new;
}

/**
Determine which call will come first in the array 
*/
int compareTimes(struct Call first, struct Call second)
{
    if (first.callDate.wholeDate > second.callDate.wholeDate){
        return 0;
    }
    else if (first.callDate.wholeDate < second.callDate.wholeDate){
        return 1;
    }
    else{
        if (first.time > second.time){
            return 0;
        }
    }
    return 1;
}

/**
Add a call to the array, in the correct sorted order
*/
void addToArray(struct Call** calls, struct Call toAdd, int* currentCapacity, int* numberOfCalls)
{
    if (*numberOfCalls == *currentCapacity)         //if array is full
    {
        *calls = expandCapacity(*calls, *currentCapacity);
        *currentCapacity *= 2;
    }
    if (*numberOfCalls == 0)            //if array is empty
    {
        *numberOfCalls = 1;
        (*calls)[0] = toAdd;
    }
    else
    {
        int found = 0;
        for(int i = 0; i < *numberOfCalls; i++)         //loop through array
        {
            if (compareTimes((*calls)[i], toAdd) == 0){         //check which will come first
                for(int k = *numberOfCalls; k > i; k--)         //shift elements to free a spot
                {
                    (*calls)[k] = (*calls)[k-1];
                }
                (*calls)[i] = toAdd;
                *numberOfCalls += 1;
                found = 1;
                break;
            }
        }
        

        if (found == 0)             //if call is placed on the end
        {
            (*calls)[*numberOfCalls] = toAdd;
            *numberOfCalls += 1;
        }
    }
}

/**
Reads a whole line call record
*/
struct Call read_call(FILE* in)
{
    struct Call c;

    char* callYear = malloc(5 * sizeof(char));      //allocate memory to make strings
    char* callMonth = malloc(3 * sizeof(char));
    char* callDay = malloc(3 * sizeof(char));
    int callTime;
    char* first = malloc(5 * sizeof(char));
    char* middle = malloc(5 * sizeof(char));
    char* last = malloc(6 * sizeof(char));
    int callDuration;
    double callCost;

    fscanf(in, "%4s%2s%2s %d %3s%3s%4s %d %lf\n", callYear, callMonth, callDay, &callTime, first, middle, last, &callDuration, &callCost);


    int yearInt = atoi(callYear);           //convert integers to strings
    int monthInt = atoi(callMonth);
    int dayInt = atoi(callDay);
    int firstInt = atoi(first);
    int secondInt = atoi(middle);
    int thirdInt = atoi(last);

    long wholeDateLong = (yearInt * 10000) + (monthInt * 100) + dayInt;

    free(callYear);
    free(callMonth);
    free(callDay);
    free(first);
    free(middle);
    free(last);

    c.callDate.year = yearInt;              //assign variables to structs
    c.callDate.month = monthInt;
    c.callDate.day = dayInt;
    c.callDate.wholeDate = wholeDateLong;
    c.callNumber.areaCode = firstInt;
    c.callNumber.exchangeCode = secondInt;
    c.callNumber.lineNumber = thirdInt;
    c.time = callTime;
    c.duration = callDuration;
    c.cost = callCost;

    return c;
}

/**
Reads entire bill file, and calls read call to read each call record
*/
struct Bill load_bill(FILE* file, char* fileName, FILE* out){
    struct Bill b;

    struct Call* currCalls = malloc(10 * sizeof(struct Call));
    int currentCapacity = 10;
    int numberOfCalls = 0;

    char* n = malloc(100 * sizeof(char));               //read headers
    fscanf(file, "%[^\n]\n", n);

    b.name = n;

    char* str = malloc(100 * sizeof(char));
    fscanf(file, "%[^\n]\n", str);

    b.street = str;

    char* sta = malloc(100 * sizeof(char));
    fscanf(file, "%[^\n]\n", sta);

    b.state = sta;

    char dummy[200];
    fgets(dummy, 200, file);

    fgets(dummy, 200, file);

    struct Call currentCall;

    while (!feof(file))             //loop through and read each call record
    {
        currentCall = read_call(file);
        addToArray(&currCalls, currentCall, &currentCapacity, &numberOfCalls);
    }

    b.calls = currCalls;

    b.numCalls = numberOfCalls;

    b.capacity = currentCapacity;

    fprintf(out, "\tLoaded %d records from %s\n", numberOfCalls, fileName);

    return b;
}


/**
    FUNCTIONS FOR SUMMARIZING
*/

/**
Convert integers to corresponding months
*/
char* intToMonth(int m){
    if(m == 1)
    {
        return "January";
    }
    else if(m == 2)
    {
        return "February";
    }
    else if(m == 3)
    {
        return "March";
    }
    else if(m == 4)
    {
        return "April";
    }
    else if(m == 5)
    {
        return "May";
    }
    else if(m == 6)
    {
        return "June";
    }
    else if(m == 7)
    {
        return "July";
    }
    else if(m == 8)
    {
        return "August";
    }
    else if(m == 9)
    {
        return "September";
    }
    else if(m == 10)
    {
        return "October";
    }
    else if(m == 11)
    {
        return "November";
    }
    else if(m == 12)
    {
        return "December";
    }
    else{
        printf("\n\nMonth: %d\n\n", m);
        return "Invalid";
    }
}

/**
Convert months to corresponding integers
*/
int monthToInt(char* month)
{
    if (month[0] == 'J' && month[1] == 'a'){
        return 1;
    }
    else if(month[0] == 'F'){
        return 2;
    }
    else if(month[0] == 'M' && month[2] == 'r'){
        return 3;
    }
    else if(month[0] == 'A' && month[1] == 'p'){
        return 4;
    }
    else if(month[0] == 'M' && month[1] == 'a'){
        return 5;
    }
    else if(month[0] == 'J' && month[2] == 'n'){
        return 6;
    }
    else if(month[0] == 'J' && month[2] == 'l'){
        return 7;
    }
    else if(month[0] == 'A'&& month[1] == 'u'){
        return 8;
    }
    else if(month[0] == 'S'){
        return 9;
    }
    else if(month[0] == 'O'){
        return 10;
    }
    else if(month[0] == 'N'){
        return 11;
    }
    else if(month[0] == 'D'){
        return 12;
    }
    else{
        return 0;
    }
}

/**
Calculate average duration of a call array
*/
double avgDuration(struct Call* arr, int size)
{
    double sum = 0;
    double num = 0;
    for(int i = 0; i < size; i++)
    {
        sum += (double)arr[i].duration;
        num += 1;
    }

    if(num == 0)
    {
        return 0;
    }
    else{
    sum = sum / num;

    return sum;
    }
}

/**
Calculate average cost of a call array
*/
double avgCost(struct Call* arr, int size)
{
    double sum = 0;
    double num = 0;
    for(int i = 0; i < size; i++)
    {
        sum += arr[i].cost;
        num += 1;
    }

    if(num == 0)
    {
        return 0;
    }
    else{
    sum = sum / num;

    return sum;
    }
}

/**
Function to summarize a bill, checking if it's a whole summary or just 1 month
*/
char summarize_bill(FILE* in, FILE* out, struct Bill bill)
{
    char* tempMonth = malloc(15 * sizeof(char));
    fscanf(in, " %[^\n]\n", tempMonth);
    int monthInt = monthToInt(tempMonth);
    if (monthInt != 0)                      //if summarizing a single month
    {
        fprintf(out, "Command: summarize %s\n", tempMonth);
        summarize_month(out, bill, monthInt);

        free(tempMonth);
        return '0';
    }
    else                    //summarizing all 12 months
    {
        fprintf(out, "Command: summarize\n");
        for(int k = 1; k < 13; k++)             //loop for each month
        {
            summarize_month(out, bill, k);
        }

        double dur = avgDuration(bill.calls, bill.numCalls);
        double co = avgCost(bill.calls, bill.numCalls);

        fprintf(out, "\t\tYear %d:    Total %d    Average Duration: %.4f   Average Cost: %.6f\n", bill.calls[0].callDate.year, bill.numCalls, dur, co);

        char toReturn = tempMonth[0];           //reassign so we can free tempMonth

        free(tempMonth);

        return toReturn;
    }

}

/**
Function to summarize just 1 month
*/
void summarize_month(FILE* out, struct Bill bill, int m)
{
    struct Call* toAvg = malloc(bill.numCalls * sizeof(struct Call));

    int toAvgSize = 0;

    for(int i = 0; i < bill.numCalls; i++)
    {
        if(bill.calls[i].callDate.month == m)       //extract calls in the desired month
        {
            toAvg[toAvgSize] = bill.calls[i];
            toAvgSize += 1;
        }
    }

    double avgDur = avgDuration(toAvg, toAvgSize);
    double avgCo = avgCost(toAvg, toAvgSize);
    char* mString = intToMonth(m);

    if(toAvgSize == 0)          //if no values...
    {
        fprintf(out, "\t\t%s:\tTotal 0 Average Duration: 0  Average Cost: 0\n", mString);
    }
    else{                       //...otherwise
        fprintf(out, "\t\t%s:\tTotal %d Average Duration: %.4f   Average Cost: %.2f\n", mString, toAvgSize, avgDur, avgCo);
    }

    free(toAvg);
}


/**
    FUNCTIONS FOR AVERAGING
*/

/**
intermediate call for finding the average cost, so we can run it for whole bill or specific month
*/
void runAvgCost(FILE* out, struct Bill bill)
{
    double avg = avgCost(bill.calls, bill.numCalls);
    fprintf(out, "\t\tAverage Cost: %.2f\n", avg);
}

/**
intermediate call for finding the average duration, so we can run it for whole bill or specific month
*/
void runAvgDuration(FILE* out, struct Bill bill)
{
    double avg = avgDuration(bill.calls, bill.numCalls);
    fprintf(out, "\t\tAverage Duration: %.4f\n", avg);
}

/**
    FUNCTIONS FOR DISPLAY
*/

/**
converts integer to a string to account for preceding 0's
*/
char* intToString(int num, int places)
{
    char* temp = malloc(5 * sizeof(char));

    int power = 1;                              //creating the power of 10 since we can't compile with the math library
    for (int i = 0; i < places - 1; i++) {
        power *= 10;
}

    if (num < power)       //checking if preceding 0 is needed
    {
        sprintf(temp, "%0*d", places, num);
    }
    else{
        sprintf(temp, "%d", num);
    }

    return temp;
}

/**
Function for displaying an entire bill
*/
void displayBill(FILE* out, struct Bill bill)
{

    fprintf(out, "%s\n%s\n%s\n\n", bill.name, bill.street, bill.state);         //headers
    fprintf(out, "Date        Time    Number      Drtn    Cost\n");
    fprintf(out, "-----------------------------------------------------\n");

    for(int i = 0; i < bill.numCalls; i++)          //loop for each call record
    {
        char* tempM = intToString(bill.calls[i].callDate.month, 2);
        char* tempD = intToString(bill.calls[i].callDate.day, 2);
        char* tempT = intToString(bill.calls[i].time, 4);

        fprintf(out, "%d%s%s\t%s\t%d%d%d\t%d\t%.2f\n", bill.calls[i].callDate.year, tempM, tempD, tempT, bill.calls[i].callNumber.areaCode, bill.calls[i].callNumber.exchangeCode, bill.calls[i].callNumber.lineNumber,  bill.calls[i].duration, bill.calls[i].cost);
    
        free(tempM);
        free(tempD);
        free(tempT);
    }
}

/**
claering records
*/

/**
free any memory unaccounted for
*/
void clear_bill(struct Bill bill)
{
    free(bill.calls);
    free(bill.name);
    free(bill.street);
    free(bill.state);
}