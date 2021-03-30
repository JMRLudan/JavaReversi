/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;



@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Othello oth; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        oth = new Othello(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                
                // updates the model given the coordinates of the mouseclick
                oth.playTurn(p.x / 100, p.y / 100);
                
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        oth.reset();
        status.setText("Player 1's Turn");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    /**
     * saves match history thus far
     */
    public void saveHistory() {
        oth.saveHistory();
        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }


    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (oth.getCurrentPlayer()) {
            status.setText("Player 1's Turn");
        } else {
            status.setText("Player 2's Turn");
        }
        
        if (oth.gameFinished()) {
            int winner = oth.getLeader();
            if (winner == 1) {
                status.setText("Player 1 wins!!!");
            } else if (winner == 2) {
                status.setText("Player 2 wins!!!");
            } else if (winner == 0) {
                status.setText("It's a tie.");
            }
        }
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board.  This approach
     * will not be sufficient for most games, because it is not 
     * modular.  All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper 
     * methods.  Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draws board grid
        
        for (int r = 1; r < 8; r++) {
            g.drawLine(r * 100, 0, r * 100, 800);
        }
        for (int c = 1; c < 8; c++) {
            g.drawLine(0, c * 100, 800, c * 100);
        }
        
        // Draws X's and O's
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int state = oth.getCell(j, i);
                if (state == 1) {
                    g.drawOval(20 + 100 * j, 20 + 100 * i, 60, 60);
                } else if (state == 2) {
                    g.fillOval(20 + 100 * j, 20 + 100 * i, 60, 60);
                } else if (oth.validPlay(oth.getCurrentPiece(), j, i)) {
                    g.drawOval(40 + 100 * j, 40 + 100 * i, 20, 20);
                    if (oth.getCurrentPlayer()) {
                        g.drawOval(45 + 100 * j, 45 + 100 * i, 10, 10);
                    } else {
                        g.fillOval(45 + 100 * j, 45 + 100 * i, 10, 10);
                    }
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
    
}