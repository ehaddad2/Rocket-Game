# Rocket-Game
 Creators: Elias Haddad, Yousra Awad, and Apsharee Ireen -- @rochester.edu

 //Apsharee Ireen//
- designed rocket, hearts, coins, arrow, and incorporated them into the game's paint component

//Elias Haddad//
- designed background and worked on collsiion detection. Incorporated gravity, and speed/motion components.

//Yousra Awad//
- designed collidables (hearts and coins), worked on collision detection, and JFrame organization. 


/*******************************************************************************************************************/
 
 Code Description: 
 There are only two java files, one for the game panel "GamePanel" and one 
 for the rocket and game functionality "RocketGame" . We added all images and audio files in a folder
 called "bin". In the main method, which is in "GamePanel", we only added the rocketGame and everything executes from the class called 
 "RocketGame". The rocket moves using up, down, right, and, left arrows. If no key is pressed, gravity 
 pulls the rocket down. Whenever the rocket hits a coin, the score increments by 100, and when it hits a 
 heart, the number of lives increments by 1. When the rocket land at the last bottom rectangle, the user 
 wins. when the user hits any of the red rectangles, the user loses one life. The score and lives remaining are displayed at 
 the top of the page.

/*******************************************************************************************************************/

Some features:
• There is animation --> the rocket moves and gravity pulls it down

• It is interactive (i.e., keyboard/mouse.) --> player uses arrow keys to move the rocket

• There is a scoring mechanism shown to the player. --> The remaining lives and score are shown at the top of the game

• There is a definitive ending mechanism (i.e., you can win or lose). --> when the rocket lands at the last bottom rectangle,
they win. when the user run out of lives before reaching the landing area, they lose.

• There are two physical mechanisms: there's gravity and motion.

• There is collision detection (i.e., walls, items, rewards, hazards.)  --> the program detects when the rocket hits
the wall and reduces number of lives. It also detects when a coin is hit and increases score, when a heart is hit, it 
increases the number of lives.

We created a method called "playMusic" in the class called "RocketGame" 
and imported the audio file from "bin" using AuidioInputStream to play background music, a feature we think was worth noting :)
