=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: Jludan
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
  - The 8x8 board is represented by an 8x8 array whose cells take a value of 0,1, or 2 depending on the piece on it.

  2. File I/O
  - The match history can be stored at any time to be rewatched. This match history is stored as a history.txt file that can be
  written to and read. The file format is simply an uninterrupted string of 0,1,2's representing the line by line, board by board
  state of the game. 
  - To meet the two state requirement, the file also saves the date when the file was saved at the end. This is accessed by the viewer.
  
  Changes: There was a suggestion on the proposal doc to just store one match instead of all past matches. Seeking to make my life
  easier, I applied this change. 

  3. Collections:
  - A Linked List keeps track of all the board states in the game. The Linked List is appropriate because it's important to have an
  ordering and to be able to go traverse the list backwards and forwards. 

  4. Testable Component:
  - GameTest.java contains Junit tests that test the model in different scenarios. It tests if it's able to stop illegal placements
  and if the board results are accurate. I also played a game of real Othello and inputted all the moves into the model to see if
  the final result was accurate. Additionally, I have a full game test with illegal moves embedded to ensure that those illegal moves
  did not affect the final result. 

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  The game utilizes and Model-View-Controller structure.
  
  -> Game.java handles the main board view and as a controller for the main GUI Buttons
  -> GameBoard.Java serves as the controller for the main game board and handles the view for painting the board
  -> Instructions.java serves as a view for the instructions.
  -> MathViewer.java serves as the view for the match viewing feature and has controllers for prev/next buttons.
  
  -> Othello.java serves as a model for the main board.
  -> MatchReader.java serves as a model for the match history viewing feature. 


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  -> The only major stumbling block I encountered was at the start when I was trying to implement the model for the othello board.
  I found it quite difficult to debug the model because working with the two dimensional array was quite confusing. I kept running
  into problems where I used zero-index when it wasn't necessary and forgot to use it when it was necessary. I also spent a significant
  amount of time fixing a bug which I fixed when I realized that going down in the Y direction for a 2d array meant increasing rather
  than decreasing the index. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  -> I'm pretty happy with how I designed the game. The Othello model and the MatchReader model are able to work independently from
  the controllers and the views. The private states for both are also well encapsulated. If I had to refactor anything, I might make
  variables for the size of the board so that it's possible to have arbitrarily large board sizes for Othello. There are also some
  inefficiencies with regards to how the game figures out which spots are valid and which aren't. Given that the game is small though,
  it's probably not worth making those optimizations.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
 
 For creating a dialog box:
 http://www.java2s.com/Tutorials/Java/Swing/JDialog/Create_About_dialog_based_on_JDialog_in_Java.htm
 
 For creating a multiline label:
 http://www.java2s.com/Tutorials/Java/Swing/JLabel/Create_a_multiline_JLabel_in_Java.htm
 
 For accessing the date:
 https://www.javatpoint.com/java-get-current-date
