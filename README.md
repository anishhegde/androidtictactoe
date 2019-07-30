# tictactoe
A simple tic tac toe game written in plain vanilla Java. 

<p float="left">
  <img src="https://github.com/anishhegde/androidtictactoe/blob/master/screen_shots/splash.png" width="250">
  <img src="https://github.com/anishhegde/androidtictactoe/blob/master/screen_shots/home_play.png" width="250">
  <img src="https://github.com/anishhegde/androidtictactoe/blob/master/screen_shots/wins.png" width="250">
</p>

## Game Rules
- Two players play the game one after another
- One player plays as "O" and the other player plays as "X"
- Whoever makes a a horizontal/vertical/diagonal row of three wins

## Constraints
- Grid size is 3X3
- One game is played at a time
- Only portrait mode
- State is not saved for revisiting a game
- Hitting restart button cleans the board and starts again

## Project Structure
|_ Game Engine  
|........|_ State Manager  
|........|_ Game Manager  
|_ Game View  
|........|_ Base Game Activity  
|........|_ Game Activity  
|_ Splash  
|........|_ Splash Activity  


### State Manager
- Manages the state between PLAY and END 
- Handles turns (Between X and O)

### Game Manager
- Handles the board
- Checks for win condition
  - Diagonal Check
  - Vertical Check
  - Horizontal Check
 - Checks for Board Complete
 - Communicates with the View on event
 
### Game Activity
- Handles View for the blocks
- Handles Animations


## Notes:

- I have tried to keep the View as dumb as possible
- Since the turn management is quite basic, I let the statemanager handle it
- I have tried to limit image assets to reduce size
- I have used fast loading fonts
- Has been built to scale as almost all values are in the values folder
- I have tried not to overly complicate the folder structure as the number of files are not too much
- Hope you enjoy it!

### TODO
- Make an ultimate tic tac toe with 3X3 grid of 3X3
- Add better winner management
- Store state 
- Tests

Make a great demo

