import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This is a class that checks if a square has a ship or not
 * @author Pierre Olsson Kruse
 * @version 1.0
 */
public class BattleSquare extends JButton implements ActionListener {

	// Fields	
		
	private boolean hasShip;
	private BattleBoard mainProg;
	
	// Constants
	
	private final ImageIcon HIT_ICON = new ImageIcon("C:/Users/Pierre Olsson Kruse/workspace/BattleShip/src/finalhit.gif");
	private final ImageIcon MISS_ICON = new ImageIcon("C:/Users/Pierre Olsson Kruse/workspace/BattleShip/src/finalmiss.gif");
	private final ImageIcon WATER_ICON = new ImageIcon("C:/Users/Pierre Olsson Kruse/workspace/BattleShip/src/finalwater.gif");
	
	/**
	 * 
	 * @param paramBattleBoard This communicates with the main program
	 */
	public BattleSquare(BattleBoard paramBattleBoard){
		
		mainProg = paramBattleBoard;
		hasShip = false;
		setIcon(WATER_ICON);
		addActionListener(this);
		setVisible(true);
	}
	/**
	 * Checks if square is empty
	 */
	public void setOwner(){
		hasShip = true;
	}
	/**
	 * Resets the game and clears the battleboard
	 */
	public void resetButton(){
		setEnabled(true);
		if(hasShip){
			hasShip = false;
		}
		setIcon(WATER_ICON);
	}
	/**
	 * Checks which actions is performed
	 */
	public void actionPerformed (ActionEvent e){
		if(!hasShip){
			setDisabledIcon(MISS_ICON);
			setEnabled(false);
			mainProg.addMisses();
		}
		else{
			setDisabledIcon(HIT_ICON);
			setEnabled(false);
			mainProg.addHits();
		}
	}
}
