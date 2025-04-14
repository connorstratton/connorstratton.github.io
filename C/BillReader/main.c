#include <stdio.h>
#include "bill.h"
#include <stdlib.h>

int main(int argc, char* argv[])
{
    if (argc != 3){         //check for correct number of argumnets
        printf("Incorrect number of arguments given.\n");
        return 1;
    }

    FILE* in;
    FILE* out;

    in = fopen(argv[1], "r");
    out = fopen(argv[2], "w");

    if (in == NULL || out == NULL)
    {
        printf("Error opening files.\n");
        return 1;
    }

    struct Bill current_bill;

    char currCmd[50];

    char read = '0';

    while(fscanf(in, "%s", currCmd) == 1)           //read a command from the input file, call function to interpret
    {
        current_bill = check(in, out, currCmd, current_bill, read);
    }

    clear_bill(current_bill);       //free and handle any leftover memory

    fclose(in);
    fclose(out);

    return 0;
}