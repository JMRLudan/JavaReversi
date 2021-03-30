import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

/*
 * Code for creating dialog window from 
 * http://www.java2s.com/Tutorials/Java/Swing/
 * JDialog/Create_About_dialog_based_on_JDialog_in_Java.htm
 * 
 */

@SuppressWarnings("serial")
class Instructions extends JDialog {
    public Instructions() {
        setTitle("Instructions");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(Box.createRigidArea(new Dimension(0, 10)));

        MultiLineLabel line1 = new MultiLineLabel("Welcome to Othello!"
            + "\nThe objective in this board game is to end "
            + "up with the most pieces at the end of the game."
            + "\nEach player places 1 piece at the start of "
            + "their turn. If you sandwich your opponent's pieces"
            + "\nbetween your new piece and your other pieces,"
            + " those piece flip. Markers will indicate where you"
            + "\n can place pieces. The color of the marker "
            + "center indicates who's move it is. The game ends when there's no more moves"
            + "\nthat can be played. You are only allowed "
            + "to play moves that flip pieces. If there are no available spaces"
            + "\nYour turn is skipped. Player 1 plays white"
            + " pieces and Player 2 plays black pieces. To place a piece"
            + "\non your turn, simply click a tile on the board!"
            + ""
            + "\n This board comes with Replay Pro(tm). You can sav"
            + "e the history of a match at any time by pressing the Store Match History button!"
            + "\nIf you want to watch the match saved, you can p"
            + "ess the Watch Stored Match button. Have fun!");
        line1.setAlignmentX(0.5f);
        add(line1);

        add(Box.createRigidArea(new Dimension(0, 100)));

        JButton close = new JButton("Close");   
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        close.setAlignmentX(0.5f);
        add(close);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 300);
    }
}


/**
 ** @version $Id: MultiLineLabel.java 6164 2009-02-19 18:11:32Z polle $
 ** @author Justin Tan
 ** A multi-line Label-like AWT component.
 **/
@SuppressWarnings("serial")
 class MultiLineLabel extends JPanel {
    int fontAttributes = Font.PLAIN;
    float alignment;
    Color col = null;
    int spacing = 0;
    
    /**
     ** Constructor - make a multiline label
     **/
    public MultiLineLabel(String text, float alignment) {
        this.alignment = alignment;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (text != null) {
            setText(text);
        }
    }

    /**
     ** Constructor, defaults to centered text
     **/
    public MultiLineLabel(String text) {
        this(text, LEFT_ALIGNMENT);
    }

    /**
     * Constructor, empty with the given alignment
     */
    public MultiLineLabel(float alignment) {
        this(null, alignment);
    }

    /**
     * Constructor, empty with the given alignment and line spacing
     */
    public MultiLineLabel(float alignment, int spacing) {
        this(null, alignment);
        this.spacing = spacing;
    }

    /**
     ** Constructor - make an empty multiline label
     **/
    public MultiLineLabel() {
        this(null, LEFT_ALIGNMENT);
    }
  
    public void setText(String text) {
        // clear the existing lines from the panel
        removeAll();
        addText(text);
    }
  
    public void addText(String text) {
        addText(text, 12);
    }
    
    public void addText(String text, int size) {
        if (spacing > 0) {
            add(Box.createVerticalStrut(spacing));
        }
            
        String[] strs = splitLines(text);
        JLabel l;
        Font font = new Font("SansSerif", fontAttributes, size);

        for (int i = 0; strs != null && i < strs.length; i++) {
            l = new JLabel(strs[i]);
            l.setFont(font);
            l.setAlignmentX(alignment);

            if (col != null) {
                l.setForeground(col);
            }

            add(l);
        }   
    }

    /**
     * Splits "string" by "Delimiter"
     * 
     * @param str - the string to be split
     * @param delimiter - the field delimiter within str
     * @returns an array of Strings
     */
    public static String[] split(String str, String delimiter) {
        ArrayList<String> strings = new ArrayList<String>();
        int start = 0;
        int len = str.length();
        int dlen = delimiter.length();
        int offset = str.lastIndexOf(delimiter); // First of all, find the
        // Last occurance of the Delimiter
        // Stop empty delimiters
        if (dlen < 1) {
            return null;
        } else if (offset < 0) { // one element
            String[] result = {str};
            return result;
        }

        //
        // Append the delimiter onto the end if it doesn't already exit
        //
        if (len > offset + dlen) {
            str += delimiter;
            len += dlen;
        }

        do {
            // Get the new Offset
            offset = str.indexOf(delimiter, start);
            strings.add(str.substring(start, offset));

            // Get the new Start position
            start = offset + dlen;
        } while ((start < len) && (offset != -1));

        // Convert the list into an Array of Strings
        String[] result = new String[strings.size()];
        strings.toArray(result);
        return result;
    }

    /**
     * Splits "string" into lines (stripping end-of-line characters)
     * 
     * @param str - the string to be split
     * @returns an array of Strings
     */
    public static String[] splitLines(String str) {
        return (str == null ? null : split(str, "\n"));
    }

    public void addText(String text, boolean bold, boolean italic) {
        int oldAttributes = fontAttributes;
        setBold(bold);
        setItalic(italic);
        addText(text);
        fontAttributes = oldAttributes;
    }

    public void setForeground(Color col) {
        this.col = col;    
        Component[] components = this.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            component.setForeground(col);
        }
    }
      
    public void setItalic(boolean italic) {
        if (italic) {
            fontAttributes |= Font.ITALIC;
        } else {
            fontAttributes &= ~Font.ITALIC;
        }
    }
  
    public void setBold(boolean bold) {
        if (bold) {
            fontAttributes |= Font.BOLD;
        } else {
            fontAttributes &= ~Font.BOLD;
        }
    }
}
/*
 This file is part of the BlueJ program. 
 Copyright (C) 1999-2009  Michael K?lling and John Rosenberg 
 
 This program is free software; you can redistribute it and/or 
 modify it under the terms of the GNU General Public License 
 as published by the Free Software Foundation; either version 2 
 of the License, or (at your option) any later version. 
 
 This program is distributed in the hope that it will be useful, 
 but WITHOUT ANY WARRANTY; without even the implied warranty of 
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 GNU General Public License for more details. 
 
 You should have received a copy of the GNU General Public License 
 along with this program; if not, write to the Free Software 
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 
 
 This file is subject to the Classpath exception as provided in the  
 LICENSE.txt file that accompanied this code.
 */