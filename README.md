# war-of-battleship
# Introduction
I am going to show you how to build a simple (only because there is no graphical user 
interface - GUI) version of the classic game Battleship. Battleship is usually a two-player game, where each player has a fleet of ships and an ocean 
(hidden from the other player), and tries to be the first to sink the other player's fleet.  We will be doing just a one-player vs. computer version, where the computer places the 
ships, and the human attempts to sink them.  We'll play this game on a 10x10 “ocean” and will be using the following ships (“the fleet”).
# How it works
This is a Human vs. Computer version.  The computer places the ten ships on the ocean in such a way that no ships are immediately adjacent to each other, either horizontally, vertically, or diagonally.

The human player does not know where the ships are. The human player tries to hit the ships, by indicating a specific row and column number (r,c). 
The computer responds with one bit of information saying, “hit” or “miss”. 

When a ship is hit but not sunk, the program does not provide any information about what kind 
of a ship was hit. However, when a ship is hit and sinks, the program prints out a message 
“You just sank a ship - (type).” After each shot, the computer re-displays the ocean with the 
new information.  

A ship is “sunk” when every square of the ship has been hit. Thus, it takes four hits (in four 
different places) to sink a battleship, three to sink a cruiser, two for a destroyer, and one for a 
submarine.  

The object is to sink the fleet with as few shots as possible; the best possible score would be 
20 (lower scores are better.) When all ships have been sunk, the program prints out a 
message that the game is over, and tells how many shots were required. 
