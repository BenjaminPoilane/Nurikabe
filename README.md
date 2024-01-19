# Nurikabe

[Nurikabe](https://en.wikipedia.org/wiki/Nurikabe_(puzzle)) is a japanese puzzle game in which you have to fill a grid with black squares. 

## Rules 

In the final grid:
 - all black squares must be connected,
 - there shouldn't be any group of 4 black square forming a 2 by 2 square, 
 - the white squares must form as many connex shape as there are squares with a number,
 - each number must be in a white connex shape with as many square as the number indicate, 
 - there shouldn't be any group of 4 white square forming a 2 by 2 square
 
## Files

 - `Nurikabe.exe` is the game as a `.exe` file. It is in french ("jouer" means "play", and "taille de la grille" means "grid size")
 - the `Nurikabe` folder contains the code in java and images used to make the game.
 - `Nurikabe_rapport_fr.pdf` is a (poorly written) report in french, presenting the way the game is made, and a way to improve it by ensuring that each grid have a unique solution.

## Program

Each grid is generated randomly and has a solution. This solution is not necessarily unique.