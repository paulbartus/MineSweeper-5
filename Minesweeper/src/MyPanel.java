import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 52;
	private static final int GRID_Y = 52;
	private static final int INNER_CELL_SIZE = 29;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 9;   //Last row has only one cell
	public static final int NUMBEROFMINES =10;
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public static final int MINE = -1;
	public int flagCounter =10;
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	
	//INDICATES WERE THE MINES ARE HIDDEN
	public boolean [][]Mines = new boolean[TOTAL_COLUMNS][TOTAL_ROWS];
	public int [][]Counter = new int[TOTAL_COLUMNS][TOTAL_ROWS]; 
	
	

	
	
	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //Top row
			colorArray[x][0] = Color.WHITE;
		}
		for (int y = 1; y < TOTAL_ROWS; y++) {   //Left column
			colorArray[0][y] = Color.WHITE;
		}
		for (int x = 1; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 1; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.white;
			}
		}
			//INITIALIZES THE MINES
			for (int x = 1; x < TOTAL_COLUMNS; x++) {   
				for (int y = 1; y < TOTAL_ROWS; y++) {
					Mines[x][y] = false;
					
				}
				
				
		}
			
			//INITIALIZE COUNTER
			for(int i=0;i<TOTAL_ROWS;i++){
				for(int j=0;j<TOTAL_COLUMNS;j++){
					
				Counter[i][j]= 0;	
			
				
					
				}	
					}
			
		
			MinesLocation();
			
	
			
	
	}
	
	
	//INITIALIZES THE GAME
	public void NewGame(){
		
	
		
	}
	
	//PUTS THE MINES LOCATION
	public void MinesLocation(){
		Random r= new Random();
		int numberofMinesPlace = 0;

while(numberofMinesPlace<NUMBEROFMINES){
		
                        int x = r.nextInt(TOTAL_ROWS);
                        int y = r.nextInt(TOTAL_COLUMNS);
			if(Mines[x][y]==false){
                            Mines[x][y] = true;
                            System.out.println("Coordinate:("+x+","+y+")");
                            System.out.println(Mines[x][y]);
                       }
			numberofMinesPlace++;
}

		}
	//MINES UNCOVERED
	public void setMineColor(){
		
		for(int i=0;i<TOTAL_ROWS;i++){
			for(int j=0;j<TOTAL_COLUMNS;j++){
				if(Mines[i][j]==true){
					colorArray[i][j]=Color.black;
					
				}
				
			}
		}
	
	}
	

	//COUNTER OF WERE THE MINES ARE
	public void Counter(){

		for(int i=0;i<TOTAL_ROWS;i++){
			for(int j=0;j<TOTAL_COLUMNS;j++){

				Counter[i][j]=CountAround(i,j);

			}
			
		}
	}

	
	
	//RETURN HOW MANY MINES ARE AROUND THE GRID
	
	public int CountAround(int x, int y){
		int Xposition = x;
		int Yposition = y;
		int CountOfMines = 0;
		int XLEFT;
		int XRIGHT;
		int YTOP;
		int YBOT;

		if(Mines[Xposition][Yposition]==true){


			if(x==0){XLEFT = 0;}else{XLEFT = Xposition-1;}
			if(x>=8){XRIGHT=8;}else{XRIGHT=x+1;}
			if(y == 0){YTOP = 0;}else{YTOP = Yposition - 1;}
			if(y >= 8){YBOT = 8;}else{YBOT = y + 1;}
			for(int i =XLEFT ; i<=XRIGHT;i++){

				for(int j=YTOP ;j<=YBOT;j++){
					if((i==Xposition&& j==Yposition)||(Mines[x][y]=true)){

					}else{
						if(Mines[x][y]==true){
							CountOfMines++;

						}

					}
				}	

			}
		}
		System.out.println(CountOfMines);
		
		return CountOfMines;

	}

				
			

		



		
		
		
	
		
		
				
	

	
	
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.gray);
		g.fillRect(x1, y1, width +1, height+1);

		//Draw the grid minus the bottom row (which has only one cell)
		//By default, the grid will be 10x10 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
		}


		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y <TOTAL_ROWS; y++) {
				if ((x == 0) || (y != TOTAL_ROWS)) {
					Color c = colorArray[x][y];
					g.setColor(c);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
				}
			}
		}
	}
	
	//GRID OF X
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x < 0 || x > TOTAL_COLUMNS || y < 0 || y > TOTAL_ROWS) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	//GRID OF Y
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE ) == 0) || (y % (INNER_CELL_SIZE) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x < 0 || x > TOTAL_COLUMNS  || y < 0 || y > TOTAL_ROWS ) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
}