/*The Ultimate Rocket Game
 * Creators: Elias Haddad (ehaddad2), Yousra Awad(yawad2), Apsharee Ireen(aireen)
 * CSC 171 
 * 12/6/21
 */

import javax.swing.JFrame;
import java.io.File;
import javax.sound.sampled.*;
// main panel for game
public class GamePanel extends JFrame{
	    public GamePanel() {
	    	
	        setTitle("Rocket Game");
	        setSize(900, 700);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    
	    public static void main(String[] args) {
	    	GamePanel panel = new GamePanel();
	    	RocketGame rocketGame = new RocketGame();
			panel.add(rocketGame);
	    	panel.setVisible(true);
	    	// loads in sound using method from RocketGame 
	    	String filepath = "bin/rocketAudio.wav";
	    	rocketGame.playMusic(filepath);
	    }
	}

