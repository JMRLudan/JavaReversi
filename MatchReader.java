import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.LinkedList;

public class MatchReader {
    String rawMatchFile = "";
    String dateSaved = "";
    int matchLength;
    private LinkedList<int[][]> history = new LinkedList<int[][]>();
    int currentTurnViewed = 0;
    
    public MatchReader() {
        Reader readerNotBuffered;
        try {
            File file = Paths.get("files/history.txt").toFile();
            readerNotBuffered = new FileReader(file);
            BufferedReader reader = new BufferedReader(readerNotBuffered);
            
            rawMatchFile = " " + reader.readLine();
            
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        matchLength = rawMatchFile.length() / 64;       
        
        CharacterIterator it = new StringCharacterIterator(rawMatchFile);
        
        for (int i = 0; i < matchLength; i++) {
            int[][] board = new int[8][8];
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    int cell = Character.getNumericValue(it.next());
                    board[r][c] = cell;
                }
            }
            history.add(board);
        }
        
        char letter = it.next();
        while (letter != CharacterIterator.DONE) {
            dateSaved += letter;
            letter = it.next();
        }
    }
    
    public String getDateSaved() {
        return dateSaved;
    }
    
    public int[][] viewCurrentTurn() {
        return history.get(currentTurnViewed);
    }
    
    public int[][] nextTurn() {
        currentTurnViewed = Math.min(matchLength - 1, currentTurnViewed + 1);
        return history.get(currentTurnViewed);
    }
    
    public int[][] prevTurn() {
        currentTurnViewed = Math.max(0, currentTurnViewed - 1);
        return history.get(currentTurnViewed);
    }
    
    public int getCell(int c, int r) {
        return history.get(currentTurnViewed)[r][c];
    }
    
    public void printBoard(int[][] board) {
        for (int[] row: board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        MatchReader mr = new MatchReader();
        
        mr.printBoard(mr.viewCurrentTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
        mr.printBoard(mr.nextTurn());
 
        

    }
    
    
    
    

}
