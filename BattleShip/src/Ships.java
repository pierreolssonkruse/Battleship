import java.util.Random;

public class Ships {
		
		
		// Fields
		private int length;
		private int x, y;
		private int fieldSize;
		private Random random;
		private boolean okToPlace; 
	    
	    // Constructor 
	    public Ships(int inputLength, int inputSize){
	         length = inputLength;
	         fieldSize = inputSize;
	    }
	    
	    // Getters
	    public int getLength(){
	        return length;
	    }
	    
	    public int getX(){
			return x;
		}
	    
		public int getY(){
			return y;
		}
		
		// Setters
		
		public void placeShip(){
			
			okToPlace = false;
			while(!okToPlace){
				int x = (int)(getX() * Math.random());
				int y = (int)(getY() * Math.random());
				
		}
		
	}	
}
