from turtle import *

screen = Screen()
screen.setup(800, 500)

bgcolor("Black")

t = Turtle()

register_shape("DVDBlue.gif")
register_shape("DVDGreen.gif")
register_shape("DVDPink.gif")
register_shape("DVDPurple.gif")
register_shape("DVDYellow.gif")
register_shape("funny.gif")

list = ["DVDBlue.gif", "DVDGreen.gif", "DVDPink.gif", "DVDPurple.gif", "DVDYellow.gif", "funny.gif"]

y = 1

t.shape("DVDBlue.gif")
t.color("white")
t.up()
t.speed(100)
t.setheading(45)

x = True

posX = 0
posY = 0

lastSet = 45



while x == True:
    t.forward(3)
    posX = t.xcor()
    posY = t.ycor()
    
    if y > 5:
        y = 1

    if posX > 239 and posY > 179:
        t.shape(list[y-1])
        print("It hit the corner!")
        t.setheading(-135)
        lastSet = -135
        y += 1
        continue
    if posX > 239 and posY < -179:
        t.shape(list[y-1])
        print("It hit the corner!")
        t.setheading(135)
        lastSet = 135
        y +=1
        continue
    if posX < -239 and posY > 179:
        t.shape(list[y-1])
        print("It hit the corner!")
        t.setheading(-45)
        lastSet = -45
        y += 1
        continue
    if posX < -239 and posY < -179:
        t.shape(list[y-1])
        print("It hit the corner!")
        t.setheading(45)
        lastSet = 45
        y += 1
        continue

    
    
    if posY > 179:
        t.shape(list[y-1])
        if lastSet == 45:
            t.setheading(-45)
            lastSet = -45
        else:
            t.setheading(-135)
            lastSet = -135
        y += 1
    if posY < -179:
        t.shape(list[y-1])
        if lastSet == -45:
            t.setheading(45)
            lastSet = 45
        else:
            t.setheading(135)
            lastSet = 135
        y += 1
    if posX > 239:
        t.shape(list[y-1])
        if lastSet == 45:
            t.setheading(135)
            lastSet = 135
        else:
            t.setheading(-135)
            lastSet = -135
        y += 1
    if posX < -239:
        t.shape(list[y-1])
        if lastSet == 135:
            t.setheading(45)
            lastSet = 45
        else:
            t.setheading(-45)
            lastSet = -45
        y += 1
