from turtle import *

screen = Screen()
screen.setup()

text1 = open("usernames.txt", "r")
usernames = text1.read().splitlines()
#print(usernames)


text2 = open("passwords.txt", "r")
passwords = text2.read().splitlines()
#print(passwords)

def check():
  global UName
  for elem in usernames:
    if elem == UName:
      index = usernames.index(UName)
      if passwords[index] == PWord:
        print("Login Successful!")
        return
      else:
        print("Incorrect Password")
        return
  print("Invalid username")

def enterUName(x, y):
  writeUName.goto(-25, 27)
  global UName
  writeUName.showturtle()
  UName = input("Please enter your username: ")
  writeUName.goto(-25, 20)
  writeUName.write(UName, font=("Arial", 16))
  writeUName.hideturtle()
  

def enterPWord(x, y):
  writePWord.goto(-25, -26)
  global PWord
  writePWord.showturtle()
  PWord = input("Please enter your password: ")
  writePWord.goto(-25, -33)
  for num in range(len(PWord)):
    writePWord.write("*")
    writePWord.forward(10)
  writePWord.hideturtle()
  check()


username = Turtle()
password = Turtle()
writeUName = Turtle()
writePWord = Turtle()

register_shape("username.gif")
register_shape("password.gif")

username.shape("username.gif")
password.shape("password.gif")
writeUName.shape("arrow")
writePWord.shape("arrow")

username.up()
password.up()
writeUName.up()
writePWord.up()

username.goto(0, 25)
password.goto(0, -25)
writeUName.goto(-25, 27)
writePWord.goto(-25, -26)

writeUName.hideturtle()
writePWord.hideturtle()

listen()
username.onclick(enterUName)
password.onclick(enterPWord)


screen.mainloop()



