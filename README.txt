Aidan Goldfarb
agoldfa7@u.rochester.edu
agoldfa7 31319775
Project 1: Game playing

Checkers game implemented with black (O) to move first and white (X) to move second, includes
4x4 or 8x8 for each of the following
	1) Human vs Human
	2) Human vs AI
	3) AI vs AI

Movement:
	1)Standard checkers notation (A4-B3, D1-C2, A1xB2...)
	2)Multi-captures are automatically calculated according to maximum capture rule (longest capture always taken). Simply enter the first capture move.
		i.e. If longest capture sequence is A1xB2xC3, entering A1xB2 is equivilant.
	3)A1 is top left, D4 is bottom right.
	4)Illegal moves are rejected and you will be asked to move again 

Requirements:
	1)4x4 minimax plays quickly and perfectly. In checkers, moving second is an advantage (shown by 4x4 AI vs AI, where 2nd to move always wins). Per Prof. Ferguson instructions, a tie is automatically reached after 10 ply
	2)8x8 H-minimax and AB-H-minimax. Heuristic described below. Plays quicky and pretty well. I am able to beat it by exploting its flaws, but a random move is generally worse
	3)Formal model: <S,A,Actions,Results,Cost>
		S: Board.java: java class that represents the state of the board
		A: Board.getChildren(): method of a board state, return all possible 'child' states (all legal moves made) for a given state
		Actions: Board.getChildren() turn dependant
		Results: Board.move(move) results in an updated Board state with move made
		Cost: Depth is calculated when looking down the tree, fixed-depth. Implemented in the various minimax classes
	4) Board.java  Piece.java  Point.java  Start.java  Minimax(DL/H/HAB).java(3 different classes) represent proper object-oriented design
		Per requirements, there is no standalone alpha-beta pruning minimax, only a minimax heuristic with alpha beta. This could be easily changed by replacing the cutoff test with terminal test. 

Heuristic:
	1)A basic row score system. The farther a piece is advanced up the board, the better. A high utility value is awarded to a position with more advanced pieces.
	2)It will sometimes walk into captures and play poor moves for the sake of advancing its pieces. This is how to exploit its flaws.
	3)8x8 AI vs AI shows the flaws of this simple heuristic. It plays to a tie where a king is being moved back and forth in a corner with better moves avaible. 


To run and use:
	In terminal:
		1) Navigate to directory containing .java project files
		2) Compile all files: "javac *.java"
		3) Run Start.java: "java Start"
		4) You will be prompted with a sequence of options.
			Select board size (4 or 8): (enter desired size) 
				Behavior is only unique on board size for AI vs AI, where it will use regular minimax for 4x4 and h-minimax-AB for 8x8.
			0: Human v.s. Human(default) |  1: Human v AI  |  2: AI v AI: (enter desired gamemode)
				Non-standard input will not be accepted

			0: Human v.s. Human is selected, a board will appear and play can begin. 
			1: Human v.s AI is selected: 0: Minimax(default)  |  1: MinimaxH  |  2: MinimaxH-AB: (enter desired AI mode)
				0: Default minimax algorithm. AI will search to bottom of tree and return best move. AI plays white (X) and moves second.
				1: Heuristic minimax (described later)
				   Specify a depth limit (10 recommended, max 15): (enter desired depth here)
					Depth cannot be over 15 (overflow protection), although the program will play beyond that, the outcome is not very different.
					Once acceptable limit is entered, play can begin. AI plays white (X) and moves second.
				2: Same as '1', accept using AB pruning. 
	Eclipse or other IDE:
		1) Import project into IDE, run.
		2) Continue from 4) from above (In terminal instructions).



