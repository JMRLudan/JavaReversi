import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
class MatchViewer extends JDialog {
    public MatchViewer() {
        setTitle("Match Viewer");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    
        Viewer viewer = new Viewer();
        JLabel date = new JLabel("Date Saved: " + viewer.getDate());
    
        final JPanel control_panel = new JPanel();
        


        JButton close = new JButton("Close");   
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    
        JButton prev = new JButton("Previous");   
        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewer.prev();
            }
        });
    
        JButton next = new JButton("Next");   
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewer.next();
            }
        });
        
        add(date);
        add(viewer);
        control_panel.add(prev);
        control_panel.add(next);
        control_panel.add(close);
        add(control_panel);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 500);
    }
}

@SuppressWarnings("serial")
class Viewer extends JPanel {

    private MatchReader mr; // model for the game

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public Viewer() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        mr = new MatchReader(); // initializes model for the game

        /*
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public String getDate() {
        return mr.getDateSaved();
    }
    
    public void next() {
        mr.nextTurn();
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }
    
    public void prev() {
        mr.prevTurn();
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draws board grid
        
        for (int r = 1; r < 8; r++) {
            g.drawLine(r * 50, 0, r * 50, 400);
        }
        for (int c = 1; c < 8; c++) {
            g.drawLine(0, c * 50, 400, c * 50);
        }
        
        // Draws X's and O's
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int state = mr.getCell(j, i);
                if (state == 1) {
                    g.drawOval(10 + 50 * j, 10 + 50 * i, 30, 30);
                } else if (state == 2) {
                    g.fillOval(10 + 50 * j, 10 + 50 * i, 30, 30);
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
