import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {

    @Test
    public void placeOnNonEmpty() {
        Othello board = new Othello();
        int[][] freshBoard = board.getBoard();
        board.playTurn(3, 3);
        board.playTurn(4, 3);
        board.playTurn(3, 4);
        board.playTurn(4, 4);
        assertTrue(Arrays.deepEquals(board.getBoard(), freshBoard));
    }
    
    @Test
    public void placeOnNonValid() {
        Othello board = new Othello();
        int[][] freshBoard = board.getBoard();
        board.playTurn(0, 0);
        board.playTurn(7, 7);
        board.playTurn(7, 0);
        board.playTurn(0, 7);
        assertTrue(Arrays.deepEquals(board.getBoard(), freshBoard));
    }
    
    @Test
    public void turnSkip() {
        Othello board = new Othello();
        int[][] forcedBoard = {
                {1,1,1,1,1,1,1,1},
                {2,2,2,2,2,2,2,2},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        board.forceBoard(forcedBoard);
        board.playTurn(0, 2);
        board.playTurn(1, 2);
        board.playTurn(2, 2);
        board.playTurn(3, 2);
        board.playTurn(4, 2);
        board.playTurn(5, 2);
        board.playTurn(7, 2);
        int[][] expected = {
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,0,1},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
        };
        assertTrue(Arrays.deepEquals(board.getBoard(), expected));
    }
    
    @Test
    public void leaderCount() {
        Othello board = new Othello();
        int[][] forcedBoard = {
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,2,0},
        };
        board.forceBoard(forcedBoard);
        board.playTurn(7, 7);
        int[][] expected = {
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
        };
        assertTrue(Arrays.deepEquals(board.getBoard(), expected));
        assertEquals(board.getLeader() , 1);
    }
    
    @Test
    public void fullGameAllValidInputs() {
        Othello board = new Othello();
        board.playTurn(2, 4);
        board.playTurn(2, 5);
        board.playTurn(2, 6);
        board.playTurn(3, 2);
        board.playTurn(2, 2);
        board.playTurn(1, 2);
        board.playTurn(1, 1);
        board.playTurn(2, 3);
        int[][] expected = {
                {0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0},
                {0,2,1,2,0,0,0,0},
                {0,0,2,2,2,0,0,0},
                {0,0,1,2,1,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
        };
        assertTrue(Arrays.deepEquals(board.getBoard(), expected));
        assertEquals(board.getLeader(), 0);
        board.playTurn(0, 2);
        board.playTurn(2, 7);
        board.playTurn(4, 2);
        board.playTurn(0, 0);
        board.playTurn(0, 0);
        board.playTurn(3, 5);
        board.playTurn(5, 3);
        board.playTurn(5, 2);
        board.playTurn(5, 1);
        board.playTurn(3, 6);
        board.playTurn(3, 7);
        int[][] expected2 = {
                {2,0,0,0,0,0,0,0},
                {0,2,0,0,0,2,0,0},
                {1,1,2,1,2,2,0,0},
                {0,0,2,2,1,2,0,0},
                {0,0,2,2,2,0,0,0},
                {0,0,2,2,0,0,0,0},
                {0,0,2,2,0,0,0,0},
                {0,0,2,2,0,0,0,0},
        };
        assertTrue(Arrays.deepEquals(board.getBoard(), expected2));
        assertEquals(board.getLeader(),2);
        board.playTurn(6, 3);
        board.playTurn(7, 3);
        board.playTurn(6, 2);
        board.playTurn(7, 2);
        board.playTurn(1, 0);
        board.playTurn(2, 0);
        board.playTurn(5, 5);
        board.playTurn(2, 1);
        board.playTurn(1, 4);
        board.playTurn(0, 5);
        board.playTurn(0, 4);
        board.playTurn(1, 5);
        board.playTurn(4, 1);
        board.playTurn(5, 0);
        board.playTurn(3, 1);
        board.playTurn(4, 5);
        board.playTurn(2, 4);
        board.playTurn(1, 3);
        board.playTurn(3, 0);
        board.playTurn(5, 4);
        board.playTurn(6, 5);
        board.playTurn(4, 6);
        board.playTurn(4, 7);
        board.playTurn(1, 6);
        board.playTurn(0, 7);
        board.playTurn(0, 6);
        board.playTurn(0, 3);
        board.playTurn(1, 7);
        board.playTurn(0, 1);
        board.playTurn(7, 5);
        board.playTurn(5, 6);
        board.playTurn(6, 4);
        board.playTurn(7, 4);
        board.playTurn(5, 7);
        board.playTurn(6, 7);
        board.playTurn(4, 0);
        board.playTurn(7, 6);
        board.playTurn(6, 6);
        board.playTurn(7, 7);
        board.playTurn(6, 0);
        board.playTurn(7, 0);
        board.playTurn(6, 1);
        board.playTurn(7, 1);
        int[][] expected3 = {
                {2,2,2,2,2,2,2,2},
                {2,2,2,2,2,2,2,2},
                {2,2,1,2,1,1,2,2},
                {2,2,2,2,1,2,2,2},
                {2,2,2,2,2,2,2,2},
                {2,1,2,1,1,2,2,2},
                {2,1,1,2,1,1,2,2},
                {2,2,2,2,2,2,2,2},
        };
        assertTrue(Arrays.deepEquals(board.getBoard(), expected3));
        assertEquals(board.getLeader() , 2);
        assertEquals(board.gameFinished(), true);
        
    }
    
    @Test
    public void fullGameWithInvalidInputs() {
        Othello board = new Othello();
        board.playTurn(2, 4);
        board.playTurn(2, 5);
        board.playTurn(2, 6);
        board.playTurn(3, 2);
        board.playTurn(2, 2);
        board.playTurn(1, 2);
        board.playTurn(1, 1);
        board.playTurn(7, 7);
        board.playTurn(2, 3);
        board.playTurn(0, 2);
        /**
         * Occupied Placements
         */
        board.playTurn(2, 7);
        board.playTurn(4, 2);
        board.playTurn(0, 0);
        board.playTurn(7, 7);
        board.playTurn(0, 0);
        board.playTurn(3, 5);
        board.playTurn(0, 7);
        board.playTurn(5, 3);
        board.playTurn(5, 2);
        board.playTurn(5, 1);
        board.playTurn(3, 6);
        board.playTurn(3, 7);
        board.playTurn(6, 3);
        board.playTurn(7, 3);
        board.playTurn(6, 2);
        board.playTurn(7, 2);
        board.playTurn(1, 0);
        board.playTurn(2, 0);
        board.playTurn(3, 6);
        board.playTurn(3, 7);
        board.playTurn(6, 3);
        board.playTurn(7, 3);
        board.playTurn(6, 2);
        /**
         * Illegal Placements
         */
        board.playTurn(7, 2);
        board.playTurn(1, 0);
        board.playTurn(2, 0);
        board.playTurn(5, 5);
        board.playTurn(2, 1);
        board.playTurn(1, 4);
        board.playTurn(0, 5);
        board.playTurn(0, 4);
        board.playTurn(1, 5);
        board.playTurn(4, 1);
        board.playTurn(5, 0);
        board.playTurn(3, 1);
        board.playTurn(4, 5);
        board.playTurn(2, 4);
        board.playTurn(1, 3);
        board.playTurn(3, 0);
        board.playTurn(5, 4);
        board.playTurn(6, 5);
        board.playTurn(4, 6);
        board.playTurn(4, 7);
        board.playTurn(1, 6);
        board.playTurn(0, 7);
        board.playTurn(0, 7);
        board.playTurn(0, 6);
        board.playTurn(0, 3);
        board.playTurn(1, 7);
        board.playTurn(0, 1);
        board.playTurn(7, 5);
        board.playTurn(5, 6);
        board.playTurn(6, 4);
        board.playTurn(7, 4);
        board.playTurn(5, 7);
        board.playTurn(6, 7);
        board.playTurn(4, 0);
        board.playTurn(7, 6);
        board.playTurn(6, 6);
        board.playTurn(7, 7);
        board.playTurn(6, 0);
        board.playTurn(7, 0);
        board.playTurn(6, 1);
        board.playTurn(7, 1);
        int[][] expected3 = {
                {2,2,2,2,2,2,2,2},
                {2,2,2,2,2,2,2,2},
                {2,2,1,2,1,1,2,2},
                {2,2,2,2,1,2,2,2},
                {2,2,2,2,2,2,2,2},
                {2,1,2,1,1,2,2,2},
                {2,1,1,2,1,1,2,2},
                {2,2,2,2,2,2,2,2},
        };
        assertTrue(Arrays.deepEquals(board.getBoard(), expected3));
        assertEquals(board.getLeader() , 2);
        assertEquals(board.gameFinished() , true);
        
    }
    
    

}
