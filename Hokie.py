from turtle import*
import math

pi = math.pi

screen = Screen()

screen.setup(400, 420)

vt = Turtle()

register_shape("vt_logogif.gif")

bgcolor("#75787b")

#vt.shape("vt_logogif.gif")

vt.speed(0)

xbeakR = 0
ybeakR = 0

xGob = 0
yGob = 0

xGob2 = 0
yGob2 = 0

xbeakL = 0
ybeakL = 0

xEyeL = 0
yEyeL = 0

xEyeL2 = 0
yEyeL2 = 0

xOutline = 0
yOutline = 0

accentX = 0
accentY = 0

""" Check coords
y = vt.xcor()
    print(y)
"""

def circleR(rad, deg):
    for num in range(deg):
        vt.forward((rad*(deg*(pi/180)))/deg)
        vt.right(1)


def outline(width, fillcolor):
    vt.width(width)
    vt.fillcolor(fillcolor)
    vt.begin_fill()
    
    vt.up()
    vt.goto(108, -150)
    vt.down()
    vt.setheading(-30)
    
    vt.circle(16, 150)
    vt.forward(50)
    circleR(20, 40)
    vt.circle(2, 180)
    vt.circle(75, 20)
    vt.setheading(120)
    circleR(120, 30)
    vt.forward(28)
    vt.circle(50, 30)
    #outline/beak meet

    global xbeakR
    global ybeakR

    xbeakR = vt.xcor()
    ybeakR = vt.ycor()

    vt.setheading(30)
    vt.circle(25, 60)
    vt.circle(5, 45)
    vt.forward(10)
    circleR(20, 40)
    vt.circle(75, 70)
    circleR(25, 35)
    vt.circle(25, 60)

    vt.circle(154, 45)
    vt.circle(25, 35)
    vt.circle(12, 90)
    #outline/eye meet

    global xEyeL
    global yEyeL

    xEyeL = vt.xcor()
    yEyeL = vt.ycor()

    vt.setheading(270)
    vt.circle(64, 30)
    #outline/beak/eye meet

    global xbeakL
    global ybeakL

    xbeakL = vt.xcor()
    ybeakL = vt.ycor()

    vt.setheading(225)
    vt.forward(16)

    vt.circle(15, 45)
    vt.circle(12, 90)
    vt.circle(5, 45)
    #outline/gobbler/beak meet

    global xGob2
    global yGob2

    xGob2 = vt.xcor()
    yGob2 = vt.ycor()

    vt.setheading(260)
    vt.circle(64, 30)
    vt.circle(16, 60)

    vt.setheading(190)
    vt.circle(40, 100)
    vt.circle(10, 100)
    #outline/bottom outline meet

    global xOutline
    global yOutline
    
    xOutline = vt.xcor()
    yOutline = vt.ycor()

    vt.setheading(0)
    circleR(600, 21)

    vt.end_fill()

    

def beakright(width, fillcolor):
    vt.width(width)
    vt.fillcolor(fillcolor)
    
    
    vt.up()
    vt.goto(xbeakR, ybeakR)
    vt.down()

    vt.begin_fill()
    
    vt.setheading(30)
    vt.circle(25, 60)
    vt.circle(5, 47)
    vt.forward(10)
    vt.circle(35, 80)
    vt.forward(37)
    circleR(35, 63)
    #beakR/gobbler meet

    global xGob
    global yGob

    xGob = vt.xcor()
    yGob = vt.ycor()

    vt.setheading(230)
    circleR(28, 90)
    vt.setheading(270)
    circleR(12, 30)
    vt.circle(33, 30)
    vt.circle(57, 30)
    #tip of beak

    global accentX
    global accentY

    accentX = vt.xcor()
    accentY = vt.ycor()

    vt.setheading(45)
    circleR(80, 20)
    #end of mouth

    tempX = vt.xcor()
    tempY = vt.ycor()

    vt.setheading(345)
    vt.circle(130, 56)

    vt.end_fill()

    vt.up()
    vt.goto(tempX, tempY)
    vt.down()
    
    vt.setheading(30)
    vt.forward(3)
    circleR(105, 30)

    for num in range(55):
        vt.forward((60*(45*(pi/180)))/45)
        vt.left(1+(num*.005))
        vt.width(width+(num*.06))

    vt.width(width)
    vt.up()
    vt.goto(27, 24)
    vt.down()
    vt.setheading(30)

    for num in range(40):
        vt.forward((60*(40*(pi/180)))/40)
        vt.right(1)
        vt.width(width+(num*.07))

    vt.up()
    vt.goto(-64, 0)
    vt.down()

    vt.fillcolor("black")
    vt.begin_fill()
    vt.circle(1)
    vt.end_fill()

def gobbler(width, fillcolor):
    vt.width(width)
    vt.fillcolor(fillcolor)
    
    vt.up()
    vt.goto(xGob, yGob)
    vt.down()

    vt.begin_fill()

    vt.setheading(50)
    vt.circle(10, 130)
    vt.forward(5)
    vt.circle(80, 33)
    #corner of left eye
    
    vt.setheading(270)
    circleR(11, 38)
    vt.circle(57, 30)
    vt.goto(xGob2, yGob2)
    vt.setheading(260)
    vt.circle(64, 30)
    vt.circle(16, 60)
    #bottom of gobbler/outline

    vt.circle(12, 109)
    vt.forward(40)
    vt.setheading(90)

    circleR(33, 30)
    vt.circle(12, 30)

    vt.setheading(320)
    vt.circle(28, 90)
    
    vt.end_fill()


def beakleft(width, fillcolor):
    vt.width(width)
    vt.fillcolor(fillcolor)

    vt.up()
    vt.goto(xbeakL, ybeakL)
    vt.down()

    vt.begin_fill()

    vt.setheading(225)
    vt.forward(16)

    vt.circle(15, 45)
    vt.circle(12, 90)
    vt.circle(5, 45)

    vt.setheading(82)
    circleR(57, 30)
    vt.circle(11, 38)

    global xEyeL2
    global yEyeL2

    xEyeL2 = vt.xcor()
    yEyeL2 = vt.ycor()
    
    vt.goto(xbeakL, ybeakL)

    vt.end_fill()

def lefteye(width):
    vt.width(width)
    vt.fillcolor("white")

    vt.up()
    vt.goto(xEyeL, yEyeL)
    vt.down()

    vt.begin_fill()

    vt.setheading(270)
    vt.circle(64, 30)
    vt.goto(xEyeL2, yEyeL2)

    vt.setheading(52)
    vt.circle(5, 38)
    vt.setheading(45)
    circleR(80, 15)

    tX = vt.xcor()
    tY = vt.ycor()

    vt.setheading(80)
    vt.circle(20, 60)

    tempX = vt.xcor()
    tempY = vt.ycor()
    
    vt.circle(20, 100)

    vt.end_fill()

    vt.up()
    vt.goto(tempX, tempY)
    vt.down()

    vt.setheading(190)
    vt.width(width+5)
    vt.circle(12, 126)

    vt.width(width+2)

    vt.up()
    vt.goto(tX, tY)
    vt.down()

    vt.fillcolor("black")
    vt.begin_fill()
    
    vt.setheading(80)
    vt.circle(20, 120)
    vt.setheading(18)
    vt.forward(27)

    vt.setheading(100)
    circleR(45, 30)
    vt.left(180)
    vt.circle(45, 30)
    vt.setheading(10)
    vt.forward(2)
    circleR(5, 100)
    vt.forward(5)
    circleR(5, 45)
    vt.circle(5, 45)
    vt.forward(14)
    vt.goto(tX, tY)

    vt.end_fill()

    vt.up()
    vt.goto(xEyeL+4, yEyeL+10)
    vt.down()
    
    vt.setheading(0)
    vt.circle(35, 44)
    
def righteye(width):
    vt.width(width)
    vt.fillcolor("white")

    vt.up()
    vt.goto(-24, 44)
    vt.down()

    vt.begin_fill()

    vt.setheading(40)
    circleR(90, 40)

    vt.setheading(180)
    vt.circle(90, 8)

    tempX = vt.xcor()
    tempY = vt.ycor()

    vt.setheading(80)

    circleR(40, 4)
    vt.width(2*width)

    circleR(40, 15)

    vt.circle(10, 74)
    vt.forward(17)

    t2x = vt.xcor()
    t2y = vt.ycor()

    vt.setheading(210)

    tempW = 2*width

    for num in range(10):
        vt.forward((100*(10*(pi/180)))/10)
        vt.left(1)
        tempW -= num*.01
        vt.width(tempW)

    
    for num in range(40):
        vt.forward((75*(40*(pi/180)))/40)
        vt.right(1)
        tempW -= num*.01
        vt.width(tempW)

    vt.setheading(350)
    
    for num in range(15):
        vt.forward((75*(15*(pi/180)))/15)
        vt.left(1)
        tempW += num*.01
        vt.width(tempW)
    
    vt.setheading(260)

    tempW = 5
    
    for num in range(62):
        vt.forward((25*(62*(pi/180)))/62)
        vt.left(1)
        tempW -= num*.002
        vt.width(tempW)

    vt.end_fill()

    vt.up()
    vt.goto(-2, 58)
    vt.down()

    vt.setheading(20)

    temp2 = 7

    vt.width(temp2)
    
    for num in range(80):
        vt.forward((8*(180*(pi/180)))/180)
        vt.left(1)
        if num > 120:
            temp2 -= num * .075
            vt.width(temp2)

    vt.circle(8, 40)
    
    for num in range(200):
        vt.forward((8*(180*(pi/180)))/180)
        vt.left(1)
        if num < 120:
            temp2 += num * .00001
            vt.width(temp2)
    
    vt.up()
    vt.goto(tempX + 5, tempY + 4)
    vt.down()

    vt.width(width)

    vt.circle(3)

    vt.up()
    vt.goto(t2x, t2y)
    vt.down()

    vt.setheading(0)

    vt.width(2*width)

    vt.circle(75, 10)

    vt.setheading(260)

    vt.circle(30, 12)
    
def accents(width):
    vt.width(width)


    #line in middle
    vt.up()
    vt.goto(xOutline, yOutline)
    vt.down()

    vt.setheading(60)
    vt.forward(20)

    tempW = width
    
    for num in range(50):
        vt.forward((75*(50*(pi/180)))/50)
        vt.right(1)
        tempW += num*.005
        vt.width(tempW)

    vt.forward(40)

    for num in range(30):
        vt.forward((100*(30*(pi/180)))/30)
        vt.left(1)
        tempW -= num*.001
        vt.width(tempW)

    for num in range(45):
        vt.forward((50*(45*(pi/180)))/45)
        vt.left(1)
        tempW -= num*.005
        vt.width(tempW)

    #bottom of beak shadow
    vt.up()
    vt.goto(accentX, accentY)
    vt.down()
    vt.width(width)

    vt.fillcolor("black")
    vt.begin_fill()

    vt.setheading(45)
    circleR(80, 20)

    vt.setheading(345)
    vt.circle(130, 32)

    vt.setheading(240)
    circleR(25, 55)
    vt.forward(56)

    vt.circle(25, 60)

    vt.goto(accentX, accentY)

    vt.end_fill()

    #eyebrowL
    vt.up()
    vt.goto(-103, 84)
    vt.width(width/2)
    vt.down()

    vt.setheading(0)

    tempW = width/2

    for num in range(28):
        vt.forward((20*(28*(pi/180)))/28)
        vt.right(1)
        tempW += num*.01
        vt.width(tempW)

    vt.up()
    vt.goto(0, 108)
    vt.width(width/2)
    vt.setheading(80)
    vt.down()

    tempW = width/2

    for num in range(100):
        vt.forward((12*(28*(pi/180)))/28)
        vt.right(1)
        tempW += num*.0005
        vt.width(tempW)

    vt.up()
    vt.goto(-68, -100)
    vt.width(width)
    vt.down()

    vt.setheading(340)

    tempW = width

    for num in range(40):
        vt.forward((30*(40*(pi/180)))/40)
        vt.left(1)
        tempW += .2
        vt.width(tempW)

    vt.up()
    vt.goto(8, -84)
    vt.setheading(15)
    vt.width(width/2)
    vt.down()

    tempW = width/2

    for num in range(15):
        vt.forward((100*(15*(pi/180)))/15)
        vt.right(1)
        tempW += .35
        vt.width(tempW)

    vt.up()
    vt.goto(32, -108)
    vt.width(width/1.5)
    vt.setheading(350)
    vt.down()

    tempW = width/1.5

    for num in range(20):
        vt.forward(1)
        tempW += .2
        vt.width(tempW)


outline(5, "#861F41")
beakright(5, "#E5751F") 
gobbler(5, "#E5751F")
beakleft(5, "#E5751F")
lefteye(5)
righteye(5)
accents(5)
vt.hideturtle()
done()
