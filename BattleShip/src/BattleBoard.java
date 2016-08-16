import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 * This is the main class for the Battleship game
 * @author Pierre Olsson Kruse
 * @version 1.0
 */

public class BattleBoard extends JFrame implements ActionListener {

	// Fields
	
	private JPanel mainPanel, gridPanel, scorePanel;
	private BattleSquare[][] buttons;
	private JLabel missLabel, hitLabel, treeLabel;
	private Random random;
	private boolean shipIsVertical;
	private GridBagConstraints gbc;
	private int nrOfMisses, nrOfHits, startI, startJ;
	private JButton resetButton;
	
	// Constants
	
	private final int WINDOW_HEIGHT = 750;
	private final int WINDOW_WIDTH = 1000;
	private final int GRID_SIZE = 10;
	private final int SHIP_LENGTH = 3;
	private final int BUTTON_SIZE = 64;
	
	private final Color BG_COLOR = new Color(0.1f, 0.7f, 0.2f);
	private final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 25);
	private final ImageIcon TREE_ICON = new ImageIcon("C:/Users/Pierre Olsson Kruse/workspace/BattleShip/src/tree.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleBoard frame = new BattleBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 * @throws InvalidMidiDataException Indicates that inappropriate MIDI data was encountered.
	 * @throws MidiUnavailableException Is thrown when a requested MIDI component cannot be opened or created because it is unavailable.
	 */
	public BattleBoard() throws InvalidMidiDataException, IOException, MidiUnavailableException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("BattleShip");
		setResizable(false);	
		
		//	Set panels
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBackground(BG_COLOR);
		add(mainPanel);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 0.1;
		scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		mainPanel.add(scorePanel, gbc);
		
		gbc.gridx = 1;
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
		gridPanel.setPreferredSize(new Dimension((BUTTON_SIZE * GRID_SIZE), (BUTTON_SIZE * GRID_SIZE)));
		gridPanel.setSize(new Dimension((BUTTON_SIZE * GRID_SIZE), (BUTTON_SIZE * GRID_SIZE)));
		mainPanel.add(gridPanel, gbc);
		
		// Set labels
		
		nrOfMisses = 0;
		missLabel = new JLabel();
		missLabel.setFont(FONT);
		scorePanel.add(missLabel);
		
		nrOfHits = 0;
		hitLabel = new JLabel();
		hitLabel.setFont(FONT);
		scorePanel.add(hitLabel);
		
		treeLabel = new JLabel(TREE_ICON);
		mainPanel.add(treeLabel);
		
		// Set reset button
		
		resetButton = new JButton(" RESET ");
		resetButton.setFont(FONT);
		resetButton.addActionListener(this);
		scorePanel.add(resetButton);
		
		// Create grid of buttons
		
		buttons = new BattleSquare[GRID_SIZE][GRID_SIZE];
		addButtons(gridPanel);
		
		// Place ship
		
		random = new Random();
		
		resetAll();
		placeShips();
		
		setVisible(true);
		
		//  Set background music
		
		URL url = new URL("file:C:/Users/Pierre Olsson Kruse/workspace/BattleShip/src/DasBoot.mid");

	    Sequence sequence = MidiSystem.getSequence(url);
	    Sequencer sequencer = MidiSystem.getSequencer();

	    sequencer.open();
	    sequencer.setSequence(sequence);

	    sequencer.start();
	    }
		
	/**
	 * Add grid of buttons
	 * @param paramPanel This adds the Array of JButtons to a JPanel
	 */
	public void addButtons(JPanel paramPanel) {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				buttons[i][j] = new BattleSquare(this);
				paramPanel.add(buttons[i][j]);
		        buttons[i][j].setIcon(new ImageIcon("C:/Users/Pierre Olsson Kruse/workspace/BattleShip/src/water.gif"));
		        buttons[i][j].setSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		        buttons[i][j].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		    }
		}
	}
		        
	private void placeShips(){
		
		shipIsVertical = random.nextBoolean();
		
		if(shipIsVertical){
			startI = random.nextInt(GRID_SIZE - SHIP_LENGTH + 1);
			startJ = random.nextInt(GRID_SIZE);
			
			for (int i = 0; i < 3; i++){
				buttons[startI+i][startJ].setOwner();
			}
		}
		else{
			startJ = random.nextInt(GRID_SIZE - SHIP_LENGTH + 1);
			startI = random.nextInt(GRID_SIZE);
			
			for (int i = 0; i < 3; i++){
				buttons[startI][startJ+i].setOwner();
			}
		}
	}
	
	private void resetAll(){
		nrOfMisses = 0;
		nrOfHits = 0;
    	missLabel.setText(" Misses: " + nrOfMisses + " ");
    	hitLabel.setText(" Hits: " + nrOfHits + " ");
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				buttons[i][j].resetButton();
				
		    }
		}
	}
	
	private void win(){
		if(nrOfHits == 3)
			JOptionPane.showMessageDialog(null, "YOU WIN", "BATTLESHIP MASTER", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * This adds the number of misses to a JLabel
	 */
	public void addMisses(){
		nrOfMisses++;
    	missLabel.setText(" Misses: " + nrOfMisses + " ");
    }
	
	/**
	 * This adds the number of hits to a JLabel and checks if there is a winner
	 */
	public void addHits(){
		nrOfHits++;
		hitLabel.setText(" Hits: " + nrOfHits + " ");
		win();
	}
	/**
	 * Checks which actions is performed
	 */
	public void actionPerformed(ActionEvent e){
		resetAll();
		placeShips();
	}
}