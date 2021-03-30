import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */


public class Othello {
    
    private LinkedList<int[][]> history;
 
    private int[][] board;
    private int numTurns;
    private boolean player1;
    private boolean gameOver;
    private int p1Pieces;
    private int p2Pieces;
    
    private int[][] directions = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

    /**
     * Constructor sets up game state.
     */
    public Othello() {
        reset();
    }

    
    /**
     * parser takes in a set of coordinates and a direction to analyze, 
     * it returns the set of pieces in that general direction
     * @param c for column (0-index)
     * @param r for row (0-index)
     * @param xDir 1 if going right, -1 if going left, 0 if neutral
     * @param yDir 1 if going up, -1 if going down, 0 if neutral
     * @return
     */
    
    private int[] parser(int c, int r, int xDir, int yDir) {
        int size = 0;
        int height = 7;
        int width = 7;
        int[][] surround = 
            {{Math.min(r, c), r, Math.min(r, width - c)},
             {c, 0, width - c},
             {Math.min(height - r, c), height - r, Math.min(height - r, width - c)}}; 
        
        size = surround[1 - yDir][xDir + 1];
        
        int[] pieces = new int[size];
        
        for (int i = 0; i < size; i++) {
            pieces[i] = board[r + -1 * yDir * (i + 1)][c + xDir * (i + 1)];
        }
        
        
        return pieces;
    }
    
    private int oppositePiece(int piece) {
        if (piece == 1) {
            return 2;
        } else {
            return 1;
        }
    }
    
    /**
     * Takes in a parser result and returns true if that parser has a flippable piece.
     * @param piece  is piece being placed down
     * @param pieces is the parser result
     * @return
     */
    private boolean parserValid(int piece, int[] pieces) {
        if (pieces.length == 0) {
            return false;
        }
        if (pieces[0] == piece || pieces[0] == 0) {
            return false;
        } else {
            pieces[0] = -1;
            for (int i : pieces) {
                if (i == 0) {
                    return false;
                } else if (i == piece) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * checks to see if a play is valid given a piece and coordinates
     * @param piece is piece being placed down
     * @param c is the column (0 index)
     * @param r is the row (0 index)
     * @return
     */
    
    public boolean validPlay(int piece, int c, int r) {
        if (board[r][c] != 0) {
            return false;
        } 
        
        for (int[] dir: directions) {
            int[] pieces = parser(c,r,dir[0],dir[1]);
            if (parserValid(piece, pieces)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Places down a piece, and flips the pieces that are flippable
     * @param piece  piece being placed down
     * @param c column coordinate 0 index
     * @param r row coordinate 0 index
     */
    private void placePiece(int piece, int c, int r) {
        if (validPlay(piece, c, r)) {
            for (int[] dir: directions) {
                int[] pieces = parser(c,r,dir[0],dir[1]);
                if (parserValid(piece, pieces)) {
                    int pc = oppositePiece(piece);
                    for (int i = 0; pc == oppositePiece(piece); i++) {
                        board[r + -1 * dir[1] *  (i)][c + dir[0] * (i)] = piece;
                        pc = board[r + -1 * dir[1] * (1 + i)][c + dir[0] * (1 + i)];
                    }
                }
            }
            board[r][c] = piece;
            
        }
    }
    
    /**
     * Accepts a board coordinate and attempts to play a move in that location
     * @param c column coordinate 0 index
     * @param r row coordinate 0 index
     * @return
     */
    
    public boolean playTurn(int c, int r) {
        
        int playerPiece = getCurrentPiece();
        
        
        if (board[r][c] != 0 || gameOver) {
            return false;
        } 
        if (validPlay(playerPiece, c, r)) {
            placePiece(playerPiece, c, r);
            
            if (checkValidSpaces(oppositePiece(playerPiece))) {
                player1 = !player1;
            }
            int[][] boardCopy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new); 
            history.add(boardCopy);
            numTurns++;
            
            if (!checkValidSpaces(1) && !checkValidSpaces(2)) {
                triggerEnd();
            }
            
            return true;
        }
  
        return false;
    }
    
    /**
     * Triggers the end of the game,
     * @return returns an integer indicating the game result. 1 if p1 win, 2 if p2 win, 0 if tie.
     */
    
    public void triggerEnd() {
        gameOver = true;
        
       
    }
    
    public int getLeader() {
        
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 1) {
                    p1Pieces++;
                }
                if (cell == 2) {
                    p2Pieces++;
                }
            }
        }
        
        if (p1Pieces > p2Pieces) {
            return 1;
        } else if (p1Pieces < p2Pieces) {
            return 2;
        } else {
            return 0;
        }
    }
    
    public boolean gameFinished() {
        return gameOver;
    }
    
    
    
    

    /**
     * checkValidSpaces checks to see if there's a valid spot on the board to place a piece.
     * 
     * @return true if there's a suitable space
     */
    public boolean checkValidSpaces(int piece) {
        
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == 0) {
                    if (validPlay(piece, c,r)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("\n\nTurn " + numTurns + ":\n");
        for (int[] row: board) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        history = new LinkedList<int[][]>();
        board = new int[8][8];
        board[3][3] = 1;
        board[4][4] = 1;
        board[3][4] = 2;
        board[4][3] = 2;
        int[][] boardCopy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new); 
        history.add(boardCopy);
        numTurns = 0;
        player1 = true;
        gameOver = false;
    }
    
    /**
     * getCurrentPlayer is a getter for the player
     * whose turn it is in the game.
     * 
     * @return true if it's Player 1's turn,
     * false if it's Player 2's turn.
     */
    public boolean getCurrentPlayer() {
        return player1;
    }
    public int getCurrentPiece() {
        if (player1) {
            return 1;
        } else {
            return 2;
        }
    }
    
    /**
     * getCell is a getter for the contents of the
     * cell specified by the method arguments.
     * 
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents
     *         of the corresponding cell on the 
     *         game board.  0 = empty, 1 = Player 1,
     *         2 = Player 2
     */
    public int getCell(int c, int r) {
        return board[r][c];
    }
    
    
    /**
     * For debug purposes, returns copy of board
     * @return
     */
    public int[][] getBoard() {
        int[][] boardCopy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new); 
        return boardCopy;
    }
    
    /**
     * For debug purposes, forces a board state
     * @return
     */
    public void forceBoard(int[][] force) {
        board = force;
    }
    
    /**
     * Save the history of the board to a file 
     */
    public void saveHistory() {
        File file = Paths.get("files/history.txt").toFile();
        Writer w;
        try {
            
            w = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(w);
            for (int[][] brd : history) {
                String boardState = "";
                for (int[] row : brd) {
                    for (int cell : row) {
                        boardState = boardState + cell;
                    }
                }
                bw.write(boardState);
            }
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            bw.write(dtf.format(now));
            
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Code to debug the save history function
     */
    public void printHistory() {
        for (int[][] brd : history) {
            System.out.println();
            for (int[] row: brd) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
        }
    }
    
    
    
    /**
     * This main method illustrates how the model is
     * completely independent of the view and
     * controller.  We can play the game from start 
     * to finish without ever creating a Java Swing 
     * object.
     * 
     * This is modularity in action, and modularity 
     * is the bedrock of the  Model-View-Controller
     * design framework.
     * 
     * Run this file to see the output of this
     * method in your console.
     */
    public static void main(String[] args) {
        Othello t = new Othello();
        int[][] force = {
                {1,1,1,1,1,1,1,1},
                {2,2,2,2,2,2,2,2},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        t.forceBoard(force);

        t.printGameState();
        
        t.playTurn(2,4);
        t.playTurn(2,3);
        t.playTurn(2,2);
        t.playTurn(1,3);
        t.printHistory();
        t.saveHistory();       

        
    }
}



