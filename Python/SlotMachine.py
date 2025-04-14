import random

start = input("Press enter to start")

count = 0
goAgain = "y"


def check(count):
  for num in range(0, 5):
    for num2 in range(num + 1, 5):
      if list[num] == list[num2]:
        count += 1
  return count


total = 50

while goAgain == "y":
  x = random.randint(0, 9)
  y = random.randint(0, 9)
  z = random.randint(0, 9)
  a = random.randint(0, 9)
  b = random.randint(0, 9)

  print("\n\nYour numbers are:\n", x, " ", y, " ", z, " ", a, " ", b)

  list = [x, y, z, a, b]

  count = 0

  count = check(count)

  if x == y and x == z and x == a and x == b:
    total += 1000000
    print("JACKPOT + 1000000\nYour total is: ", total)
  elif count > 0:
    total += count * 5
    print(count, "match! + ", count * 5, "\nYour total is: ", total)
  else:
    total -= 20
    print("No match - 20\nYour total is: ", total)

  if total < 20:
    print("Bankrupt! You lose!")
    break

  goAgain = input("Would you like to go again? (y/n) ")
  while goAgain != "y" and goAgain != "n":
    goAgain = input("Please enter y or n ")
