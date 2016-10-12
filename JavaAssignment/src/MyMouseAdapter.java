import java.awt.Color;

import java.awt.Component;

import java.awt.Insets;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.util.Random;



import javax.swing.JFrame;



public class MyMouseAdapter extends MouseAdapter {

private Color oldColor = Color.BLACK;


private Random generator = new Random();




private Color newColor;





public void mousePressed(MouseEvent e) {

Component c = e.getComponent();

while (!(c instanceof JFrame)) {

c = c.getParent();

if (c == null) {

return;

}

}

JFrame myFrame = (JFrame) c;

MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);

Insets myInsets = myFrame.getInsets();

int x1 = myInsets.left;

int y1 = myInsets.top;

e.translatePoint(-x1, -y1);

int x = e.getX();

int y = e.getY();

myPanel.x = x;

myPanel.y = y;

myPanel.mouseDownGridX = myPanel.getGridX(x, y);

myPanel.mouseDownGridY = myPanel.getGridY(x, y);



myPanel.repaint();

switch (e.getButton()) {

case 1:	//Left mouse button


break;

case 3:	//Right mouse button

//Do nothing

break;

default:    //Some other button (2 = Middle mouse button, etc.)

//Do nothing

break;

}

}



public void mouseReleased(MouseEvent e) {

Component c = e.getComponent();

while (!(c instanceof JFrame)) {

c = c.getParent();

if (c == null) {

return;

}

}

JFrame myFrame = (JFrame)c;

MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel

Insets myInsets = myFrame.getInsets();

int x1 = myInsets.left;

int y1 = myInsets.top;

e.translatePoint(-x1, -y1);

int x = e.getX();

int y = e.getY();

myPanel.x = x;

myPanel.y = y;

int gridX = myPanel.getGridX(x, y);

int gridY = myPanel.getGridY(x, y);

switch (e.getButton()) {


case 1:	//Left mouse button


if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {

//Had pressed outside

//Do nothing

} else {

if ((gridX == -1) || (gridY == -1)) {

//Is releasing outside

//Do nothing

} else {

if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {

//Released the mouse button on a different cell where it was pressed

//Do nothing

} else {

//Released the mouse button on the same cell where it was pressed

if ((gridX == 0) || (gridY == 0)) {

//On the left column and on the top row... do nothing

} else{

//On the grid other than on the left column and on the top row:



myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = setColor(setRandomColor());

myPanel.repaint();



}

if((myPanel.mouseDownGridX==0 && gridX==0)&& ((myPanel.mouseDownGridY>0 && gridY>0)&&(myPanel.mouseDownGridY<10 && gridY<10))){



for( int row =1;row<10;row++){

myPanel.colorArray[row][myPanel.mouseDownGridY] = setColor(setRandomColor());

myPanel.repaint();

}

}

else if(((myPanel.mouseDownGridX>0 && gridX>0)&&(myPanel.mouseDownGridX<10 && gridX<10))&& (myPanel.mouseDownGridY==0 && gridY==0)){



for( int col =1;col<10;col++){

myPanel.colorArray[myPanel.mouseDownGridX][col] = setColor(setRandomColor());

myPanel.repaint();

}


}

else if((myPanel.mouseDownGridX==0 && gridX==0)&& (myPanel.mouseDownGridY==0 && gridY==0)){


for( int diagonalLine =1;diagonalLine<11;diagonalLine++){

for(int k =1;k<diagonalLine;k++)

{

myPanel.colorArray[k][k] = setColor(setRandomColor());

myPanel.repaint();

}

}

}

} 



}



}

myPanel.repaint();

break;

case 3:

if ((gridX == -1) || (gridY == -1)) {


for(int row=0;row<10;row++){


myPanel.colorArray[row][0] = setColor1(setRandomColor1());

myPanel.repaint();


}

for(int col=0;col<11;col++){


myPanel.colorArray[0][col] = setColor1(setRandomColor1());

myPanel.repaint();


}

} 

break;

default:    //Some other button (2 = Middle mouse button, etc.)

//Do nothing

break;

}



}
//CHANGE SMAL BOX TO NUMBER OR WHITE SQUARE
public Color setRandomColor(){

Color newColor = null;


switch (generator.nextInt(5)) {

case 0:

newColor = Color.YELLOW;

break;

case 1:

newColor = Color.MAGENTA;

break;

case 2:

newColor = Color.BLACK;

break;

case 3:

newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)

break;

case 4:

newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)

break;

}


return newColor;



}



public Color setColor(Color c){


this.newColor = c; 


while(oldColor.equals(newColor)){


switch (generator.nextInt(5)) {

case 0:

newColor = Color.YELLOW;

break;

case 1:

newColor = Color.MAGENTA;

break;

case 2:

newColor = Color.BLACK;

break;

case 3:

newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)

break;

case 4:

newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)

break;


}


}


oldColor=newColor;



return newColor;

}

public Color setRandomColor1(){

Color newColor = null;


switch (generator.nextInt(3)) {

case 0:

newColor = Color.pink;

break;

case 1:

newColor = Color.red;

break;

case 2:

newColor = Color.BLUE;

break;

}

return newColor;

}



public Color setColor1(Color t){


this.newColor = t; 


while(oldColor.equals(newColor)){


switch (generator.nextInt(3)) {

case 0:

newColor = Color.pink;

break;

case 1:

newColor = Color.RED;

break;

case 2:

newColor = Color.BLUE;

break;


}

}

return newColor;

}


}
