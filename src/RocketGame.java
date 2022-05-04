/*The Ultimate Rocket Game
 * Creators: Elias Haddad (ehaddad2), Yousra Awad(yawad2), Apsharee Ireen(aireen)
 * CSC 171 
 * 12/6/21
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class RocketGame extends JPanel implements ActionListener, KeyListener{
	// instance vars
	JLabel label = new JLabel();
	// starting num lives
	protected int numLives = 3;
	// starting pos of rocket
	protected int x = 0;
	protected int y = 300;
	// images displayed
	protected BufferedImage rocket;
	protected BufferedImage coin1;
	protected BufferedImage coin2;
	protected BufferedImage coin3;
	protected BufferedImage heart;
	protected BufferedImage endArrow;	
    protected int top1; // top rectangles
    protected int top2;
    protected int top3;
    protected int top4;
    protected int top5;
    protected int top6;
    protected int top7;
    protected int top8;
    protected int top9;
    protected int bottom1; // bottom rectangles
    protected int bottom2;
    protected int bottom3;
    protected int bottom4;
    protected int bottom5;
    protected int bottom6;
    protected int bottom7;
    protected int bottom8;
    protected int bottom9;
	protected int speedX = 0;
	protected int speedY = 0;
	protected int score = 0;
	protected boolean coin1Img = true;
	protected boolean coin2Img = true;
	protected boolean coin3Img = true;
	protected boolean heartImg = true;
	protected boolean endArrowImg = true;
    protected JLabel lives; // Label that shows remaining lives and score
    
    // animation timer
    Timer timer = new Timer (5, this);
    // game constructor
	public RocketGame() {
		timer.start();
        lives = new JLabel("Lives");
        // adds label to top of panel
        add(lives);
        lives.setForeground(Color.BLACK);
        lives.setFont(new Font("Serif", Font.PLAIN, 24));
        // updates this as numLives increases or decreases
        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
        this.addKeyListener(this);
        this.setFocusable(true);
        
        // loads in all images
        try {
            rocket = ImageIO.read(new File("bin/pixil-frame-2-2.png")); //Rocket Image
        } catch (IOException ex) {
            System.out.println("IO Exception.");
        }
        
        // load in the coins/heart
		 try {
	            coin1 = ImageIO.read(new File("bin/coin.png")); // Coin1 Image
	        } catch (IOException ex) {
	            System.out.println("IO Exception.");
	        }
		 try {
	            coin2 = ImageIO.read(new File("bin/coin.png")); // Coin2 Image
	        } catch (IOException ex) {
	            System.out.println("IO Exception.");
	        }
		 try {
	            coin3 = ImageIO.read(new File("bin/coin.png")); // Coin3 Image
	        } catch (IOException ex) {
	            System.out.println("IO Exception.");
	        }

		 try {
	            heart = ImageIO.read(new File("bin/heart.png")); // Heart image
	        } catch (IOException ex) {
	            System.out.println("IO Exception.");
	        }
		 
		 try {
			 endArrow = ImageIO.read(new File("bin/pixil-layer-Background-3.png")); //Arrow image
	        } catch (IOException ex) {
	            System.out.println("IO Exception.");
	        }
	}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			   // casting to 2d
		      Graphics2D graphicsObj = (Graphics2D)g;
		      Graphics2D endingLand = (Graphics2D)g;
		      
		      // set gradient of background rectangles
			  GradientPaint gp1 = new GradientPaint(20, 20, 
			  Color.pink, 0, 20, Color.white, true);
			  graphicsObj.setPaint(gp1);
			  
			  // upper half of background
			  graphicsObj.fillRect(0, 0, 100, 250);
		      graphicsObj.fillRect(100, 0, 100, 151);
		      graphicsObj.fillRect(200, 0, 100, 50);
		      graphicsObj.fillRect(300, 0, 100, 280);
		      graphicsObj.fillRect(400, 0, 100, 186);
		      graphicsObj.fillRect(500, 0, 100, 50);
		      graphicsObj.fillRect(600, 0, 100, 272);
		      graphicsObj.fillRect(700, 0, 100, 50);
		      graphicsObj.fillRect(800, 0, 100, 270);
		      
		      // lower half of background
		      graphicsObj.fillRect(0, 450, 100, 500);
		      graphicsObj.fillRect(100, 472, 100, 500);
		      graphicsObj.fillRect(200, 585, 100, 500);
		      graphicsObj.fillRect(300, 451, 100, 500);
		      graphicsObj.fillRect(400, 650, 100, 500);
		      graphicsObj.fillRect(500, 581, 100, 500);
		      graphicsObj.fillRect(600, 515, 100, 500);
		      graphicsObj.fillRect(700, 461, 100, 500);
		      graphicsObj.setPaint(Color.pink);
		      graphicsObj.fillRect(800, 613, 100, 500);
		      g.setColor(Color.red);
		      // places all images in proper positions
		      g.drawImage(rocket, x, y, null);
		      g.drawImage(heart, 220, 90, null);
		      g.drawImage(coin2, 450, 450, null);
		      g.drawImage(coin1, 500, 200, null);
		      g.drawImage(coin3, 700, 81, null);
		      g.drawImage(endArrow, 785, 400, null);

		   }
		@Override
		public void actionPerformed(ActionEvent e) {
			// x and y coordinate of rocket are updated based on the speed variables assigned below
			// at the key listener method
			x += speedX;
			y += speedY;
			
			// calls method to constantly check if rocket hits the top or bottom part of the background
			hitsBottomWall();
			hitsTopWall();
			
			// also checks if rocket hits coins or hearts using methods
			hitsHeart();
			hitsCoins();
			// creates border limit
			if (x < 0) {
				x = 0;
			}
			
			if(x > 780) {
				x = 780;
			}
			
			if (y < 0) {
				y = 0;
			}
			
			if (y > 550) {
				y = 550;
			}
		
			// disables key input if game lost
			if (numLives == 0) {
				
				this.setEnabled(false);
			}
	        repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();
			
			// moves rocket up while checking if it hit the walls
	        if (k == KeyEvent.VK_UP) {
	        	
	        	// changes rocket image to rotate rocket in specified direction
	        	try {
	                rocket = ImageIO.read(new File("bin/pixil-frame-2-2 (4).png"));
	            } catch (IOException ex) {
	                System.out.println("IO Exception.");
	            }
	    
	        	// speed values updated to reflect intended direction of movement
	    		speedX = 0;
				speedY = -2;
	            hitsTopWall();
	            hitsBottomWall();
	            repaint();

	        }
	        // move rocket left
	        if (k == KeyEvent.VK_LEFT) {

	        	try {
	                rocket = ImageIO.read(new File("bin/pixil-frame-2-2 (3).png"));
	            } catch (IOException ex) {
	                System.out.println("IO Exception.");
	            }
	        	speedX = -2;
				speedY = 0;
	            hitsTopWall();
	            hitsBottomWall();
	            repaint();
	        }
	        // move rocket right
	        if (k == KeyEvent.VK_RIGHT) {
	        	try {
	                rocket = ImageIO.read(new File("bin/pixil-frame-2-2 (1).png"));
	            } catch (IOException ex) {
	                System.out.println("IO Exception.");
	            }
				speedX = 2;
				speedY = 0;
	            hitsTopWall();
	            hitsBottomWall();
	            repaint();
	        }
	        // move rocket down
	        if (k == KeyEvent.VK_DOWN) {
	        	try {
	                rocket = ImageIO.read(new File("bin/rocketDownPic.png"));
	            } catch (IOException ex) {
	                System.out.println("IO Exception.");
	            }
				speedX = 0;
				speedY = 2;
	            hitsTopWall();
	            hitsBottomWall();
	            repaint();
	        }
	    }
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
		@Override
		// gravity component
		public void keyReleased (KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_UP) {
		
		speedX = 0;
		speedY = 1;
		}
		if (k == KeyEvent.VK_DOWN) {
			
			speedX = 0;
			speedY = 1;
		}
		if (k == KeyEvent.VK_RIGHT) {
			
			speedX = 0;
			speedY = 1;
		}
		if (k == KeyEvent.VK_LEFT) {
	
			speedX = 0;
			speedY = 1;
			}
		}
		// models each rectangle on the top part of the panel for collision detection
		public void hitsTopWall() {
			if (x <= 57 && y <= 240 && y >= 0) {
				// return rocket to position if hits wall
                x = 0;
                y = 300;
                speedX =0;
                speedY =0;
                // reduce numLives by 1 every time wall is hit
                numLives--;
                // calls this method to end game if all lives lost
                checkDeath();
                // updates jlabel
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 57 && x <= 150 && y <= 140 && y >= 0) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 150 && x <= 220 && y <= 40 && y >= 0) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 220 && x <= 340 && y <= 260 && y >= 0) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 340 && x <= 440 && y <= 175) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 440 && x <= 520 && y <= 40 && y >= 0) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 520 && x <= 640 && y <= 251 && y >= 0) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 640 && x <= 720 && y <= 40 && y >= 0) {
                x = 0;
                y =  300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 720 && x <= 780 && y <= 258 && y >= 0) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            }		
			
		}
		// models each rectangle on the bottom part of the panel for collision detection
		public void hitsBottomWall() {
			if (x < 57 && y >= 350) { 
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x >= 57 && x <= 141 && y >= 354) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 141 && x <= 210 && y >= 485) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 210 && x <= 339 && y >= 360) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 399 && x <= 411 && y >= 531) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            }
			else if (x > 411 && x <= 510 && y >= 462) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 510 && x <= 609 && y >= 396) {
                x = 0;
                y = 300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			else if (x > 609 && x <= 740 && y >= 342) {

                x = 0;
                y =  300;
                numLives--;
                checkDeath();
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
            } 
			// winning rectangle
			else if (x > 740 && x <= 780 && y >= 480) {
				
				// winning rectangle image
				try {
	                rocket = ImageIO.read(new File("bin/pixil-frame-2-2 (4).png"));
	            } catch (IOException ex) {
	                System.out.println("IO Exception.");
	            }
				// stops keyboard input, resets rocket position, and updates jlabel to display the win info
				this.setEnabled(false);
                x = 0;
                y = 300;
                speedX = 0;
                speedY = 0;
                lives.setText("You won! Your Score is " + score);
                
            }	
			
		}
		// method to check if lives lost are 0 
		public void checkDeath() {
			if (numLives==0) {
				// reset rocket
				speedX = 0;
				speedY = 0;
				// display upright rocket image
				try {
	                rocket = ImageIO.read(new File("bin/pixil-frame-2-2 (4).png"));
	            } catch (IOException ex) {
	                System.out.println("IO Exception.");
	            }
				// disable key input and update jlabel
				this.setEnabled(false);
				JLabel end = new JLabel();
                end.setText(" You Lost!");
                end.setForeground(Color.BLACK);
                end.setFont(new Font("Serif", Font.PLAIN, 24));
				add(end);
			}
		}
		
		// method to model rocket colliding with coins
		public void hitsCoins() {
			if (x >= 441 && x <= 498 && y <= 237 && coin1Img == true) { // hits coin 1
				// update score and jlabel
				score += 100;
				coin1Img = false;
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
				try {
		            coin1 = ImageIO.read(new File("bin/square.png"));
		        } catch (IOException ex) {
		            System.out.println("IO Exception.");
		        }
			}
			if (x >= 372 && x <=453 && y >=339 && coin2Img == true) { // hits coin 2
				score += 100;
				coin2Img = false;
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
				try {
		            coin2 = ImageIO.read(new File("bin/square.png"));
		        } catch (IOException ex) {
		            System.out.println("IO Exception.");
		        }
			}
			
			if (x >= 652 && x <=698 && y <= 119 && coin3Img == true) { // hits coin 3
				score += 100;
				coin3Img = false;
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
				try {
		            coin3 = ImageIO.read(new File("bin/square.png"));
		        } catch (IOException ex) {
		            System.out.println("IO Exception.");
		        }
			}
		}
		public void hitsHeart() {
			
			if (x >= 162 && x <= 192 && y <= 132 && heartImg == true) {
				// increase lives
				numLives += 1;
				heartImg = false;
		        lives.setText("lives remaining: " + String.valueOf(numLives) + " Score: " + score);
				try {
		            heart = ImageIO.read(new File("bin/square.png"));
		        } catch (IOException ex) {
		            System.out.println("IO Exception.");
		        }
			}
		}
		
		// method to load file for the background music used in main method
		void playMusic (String musicLocation) {
			try {
				
				File musicPath = new File(musicLocation);
				
				if (musicPath.exists()) {
					
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
				}
				
				else {
					
					System.out.println("Can't find the file");
				}
			}
			
			catch (Exception ex) {
				
				ex.printStackTrace();
			}
		}
}
