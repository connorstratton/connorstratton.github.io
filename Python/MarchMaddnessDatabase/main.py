import pandas
import matplotlib.pyplot as plt
import sqlite3
from os.path import exists
from tabulate import tabulate

def calc_yearPercent(numYears):                     #function calculating the percent of the number of times a team has made the tournament
    return round(((numYears / 17.0) * 100), 2)

def addAvgWins(curr, seed):                         #function assigning correct number of wins in tournament for each given round
    if seed == 32:
        curr += 1
    elif seed == 16:
        curr += 2
    elif seed == 8:
        curr += 3
    elif seed == 4:
        curr == 4
    elif seed == 2:
        curr += 5
    elif seed == 1:
        curr += 6
    return curr

def print_menu():                                   #function printing the menu for the user to see
    print("\nOptions:\n")
    print("[1] View all teams")
    print("[2] View a selected team's history")
    print("[3] View past National Champions")
    print("[4] Graph multiple teams' performances")
    print("[5] Exit")

def display_teams():                                #functionality of displaying all teams there is data for
    cursor = conn.execute('SELECT DISTINCT TEAM FROM TeamStats ORDER BY TEAM')

    for item in cursor:
        print(item[0])

def select_team():                                  #function to view yearly stats and other stats for a user-given team
    team = str(input("\nPlease enter a team (refer to team list for exact names): "))

    team = team.replace("'", "''")
    
    #run SQL query to get the years for that team
    cursor = conn.execute('SELECT YEAR, TEAM, SEED, ROUND FROM TeamStats WHERE TEAM=\''+team+'\' ORDER BY YEAR')

    tempArray = []
    avgSeed = 0
    avgWins = 0
    for item in cursor:
        tempArray.append([item[0], item[1], item[2], item[3]])
        avgSeed += item[2]
        avgWins = addAvgWins(avgWins, item[3])

    if (len(tempArray) == 0):
        print("\nNo data found for your entry, please refer to list of teams for valid teams or correct spelling\n")
    else:
        header = ["Year", "Team", "Seed", "Round"]

        #use tabulate library for nicer output formatting
        print(tabulate(tempArray, headers=header)) 

        #print other stats as well (percent of years in tournament, average seed, and average number of wins)
        print("\nPercent of tournaments appeared in: "+str(calc_yearPercent(len(tempArray)))+"%")
        print("Average seed when in tournament: "+str(round(avgSeed/len(tempArray), 1)))
        print("Average wins when in tournament: "+str(round(avgWins/len(tempArray), 1)))

def view_winners():                                 #function printing yearly winners of the tournament
    #SQL query getting all teams that made it to the last round, or in other words won the tournament
    cursor = conn.execute('SELECT YEAR, TEAM, SEED FROM TeamStats WHERE ROUND=1 ORDER BY YEAR')

    tempArray = []
    for item in cursor:
        tempArray.append([item[0], item[1], item[2]])

    header = ["Year", "Team", "Seed"]

    #using tabulate library again for outputting
    print(tabulate(tempArray, headers=header))

def graph(index, percents, seeds, wins):            #function graphing the data the user selected
    #assign arrays to each bar graph using pandas
    df = pandas.DataFrame({"Percent Made (x/10)":percents, "Average Seed (16-x)":seeds, "Average Wins":wins}, index=index)
    
    #plot the bar graph using the matplot library
    ax = df.plot.bar(rot=0)
    
    #save and display file
    plt.savefig("graph.png")
    print("\nGraph saved to graph.png in your current working directory")
    plt.show()

def graph_teams():                                  #function for collecting data to be graphed
    again = "y"
    index = []
    percents = []
    seeds = []
    wins = []
    while again == "y":                             #loop to collect desired number of teams
        team = str(input("Enter a team to add or enter \"graph\" to finish and graph entered teams: "))

        if team == "graph":                         #check if user is ready to graph
            if (len(index) > 0):
                again = "n"
                graph(index, percents, seeds, wins)
            else:
                print("Please enter at least 1 team")
        else:                                       #run SQL query to get needed data
            team = team.replace("'", "''")
            cursor = conn.execute('SELECT TEAM, SEED, ROUND FROM TeamStats WHERE TEAM=\''+team+'\' ORDER BY YEAR')

            tempArray = []
            avgSeed = 0
            avgWins = 0
            percent = 0
            for item in cursor:                     #extract the data that we want so we can manipulate it
                tempArray.append([item[0], item[1], item[2]])
                avgSeed += item[1]
                avgWins = addAvgWins(avgWins, item[2])
            
            if (len(tempArray) > 0):                #check if team is valid, and then add optimized values that we want for graphing
                index.append(tempArray[0][0])
                percents.append((calc_yearPercent(len(tempArray))) / 10)
                seeds.append((16 - avgSeed/len(tempArray)))
                wins.append(avgWins/len(tempArray))
            else:
                print("Team not found")



if exists('TeamStats.sqlite'):                      #connect to database
    conn = sqlite3.connect('TeamStats.sqlite')
    cursor = conn.cursor()
    goAgain = "y"

    #allow the user to repeat the program and select multiple menu options
    while goAgain == "y":
        print_menu()

        selection = str(input("\nSelection: "))

        #get the user's menu selection and check if it's valid
        while (selection != "1" and selection != "2" and selection != "3" and selection != "4" and selection != "5"):
            selection = str(input("\nPlease enter a number in the menu: "))

        if selection == "1":
            display_teams()
        elif selection == "2":
            select_team()
        elif selection == "3":
            view_winners()
        elif selection == "4":
            graph_teams()
        elif selection == "5":
            break

        #give the user an option to escape the program after running
        goAgain = str(input("\nWould you like to make another selection? (y/n) "))
        while (goAgain != "y" and goAgain != "n"):
            goAgain = str(input("Please enter y or n "))

    conn.close()                                #clode database
    print("\nDatabase accessed and closed successfully")
else:
    print("\nFile you're accessing doesn't exist")