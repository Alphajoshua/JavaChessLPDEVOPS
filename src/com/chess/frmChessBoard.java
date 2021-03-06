import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class frmChessBoard extends JFrame implements MouseListener

{    

      public static void main(String[] args)

      {

            final frmChessBoard app = new frmChessBoard();             

      }

      // the whole constructor is for setting up the UI of the form

      public frmChessBoard()

      {

            c = getContentPane();

            setBounds(100, 100, 470, 495);

            setBackground(new Color(204, 204, 204));

            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setTitle("Mettez-nous 21/20");

            setResizable(false);

            c.setLayout(null);     

            pnlMain.setBounds(3, 3, 460, 460);

            pnlMain.setBackground(new Color(255, 255, 255));

            c.add(pnlMain);

            this.drawChessBoard();

            this.arrangeChessPieces();

            show();

      }

      // This method captures the move on the chess board and then make

      // it happen, logically and physically; also, it sends the move to

      // the other client

      public void mouseClicked(MouseEvent e)

      {

            if(bMyTurn)

            {                

                  Object source = e.getSource();

                  JPanel pnlTemp = (JPanel)source;         

                  int intX = (pnlTemp.getX()/57);

                  int intY = (pnlTemp.getY()/57);

                  this.boolMoveSelection = !this.boolMoveSelection;

                  if(this.boolMoveSelection)

                  {

                        this.pntMoveFrom = new Point(intX, intY);

                        if(this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals(""))

                              this.boolMoveSelection = !this.boolMoveSelection;

                        if((!this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals("")) &&

                                    this.bWhite && this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().charAt(1) == 'B')

                              this.boolMoveSelection = !this.boolMoveSelection;

                        if((!this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim().equals("")) &&

                                    !this.bWhite && this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().charAt(1) == 'W')

                              this.boolMoveSelection = !this.boolMoveSelection;

                        if(this.boolMoveSelection)

                              this.makeChessPieceDifferent(true);

                  }

                  else

                  {

                        this.pntMoveTo = new Point(intX, intY);

                        if(!this.pntMoveFrom.equals(this.pntMoveTo))

                        {

                              if(      this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString().trim() != "")

                                    if(this.isMoveValid())

                                    {                                        

                                          this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x] = this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x].toString();

                                          this.strChessBoard[this.pntMoveFrom.y][this.pntMoveFrom.x] = "  ";

                                          this.moveChessPiece();                                     

                                    }

                                    else

                                    {

                                          JOptionPane.showMessageDialog(this, "Invalid Move Request.", "Warning", JOptionPane.ERROR_MESSAGE);

                                          this.makeChessPieceDifferent(false);

                                    }

                        }

                        else

                              this.makeChessPieceDifferent(false);

                  }    

            }

      }    

      // This method checks if attempted move is valid or not

      private boolean isMoveValid()

      {

            boolean isMoveValid = true;

            return isMoveValid;

      }

      // This method makes the selected chess piece looks like selected

      private void makeChessPieceDifferent(boolean bSelected)

      {

      for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++)

            if(this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)

            {

                  JLabel lblTemp = (JLabel)this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z);

                  lblTemp.setEnabled(!bSelected);

            }          

      }

      // If class level variables Point-From and Point-To are set,

      // then this method actually moves a piece, if any exists, from

      // one cell to the other

      private void moveChessPiece()

      {

      for(int z = 0; z < this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponentCount(); z++)

            if(this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)

            {

                  this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].remove(z);

                  this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].repaint();

            }

      for(int z = 0; z < this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponentCount(); z++)

            if(this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].getComponent(z).getClass().toString().indexOf("JLabel") > -1)

            {

                  this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].remove(z);

                  this.pnlChessCells[this.pntMoveFrom.y][this.pntMoveFrom.x].repaint();

            }

          this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].add(this.getPieceObject(this.strChessBoard[this.pntMoveTo.y][this.pntMoveTo.x]), BorderLayout.CENTER);

      this.pnlChessCells[this.pntMoveTo.y][this.pntMoveTo.x].validate();

      }

      // Given the code of a piece as a string, this method instantiates

      // a label object with the right image inside it

      private JLabel getPieceObject(String strPieceName)

      {

            JLabel lblTemp;

      if(strPieceName.equals("RB"))

            lblTemp = new JLabel(this.rookBlack);

      else if(strPieceName.equals("BB"))

            lblTemp = new JLabel(this.bishopBlack);

      else if(strPieceName.equals("NB"))

            lblTemp = new JLabel(this.knightBlack);

      else if(strPieceName.equals("QB"))

            lblTemp = new JLabel(this.queenBlack);

      else if(strPieceName.equals("KB"))

            lblTemp = new JLabel(this.kingBlack);

      else if(strPieceName.equals("PB"))

            lblTemp = new JLabel(this.pawnBlack);

      else if(strPieceName.equals("RW"))

            lblTemp = new JLabel(this.rookWhite);

      else if(strPieceName.equals("BW"))

            lblTemp =  new JLabel(this.bishopWhite);

      else if(strPieceName.equals("NW"))

            lblTemp = new JLabel(this.knightWhite);

      else if(strPieceName.equals("QW"))

            lblTemp = new JLabel(this.queenWhite);

      else if(strPieceName.equals("KW"))

            lblTemp = new JLabel(this.kingWhite);

      else if(strPieceName.equals("PW"))

            lblTemp = new JLabel(this.pawnWhite);

      else

            lblTemp = new JLabel();

            return lblTemp;

      }

      // This method reads strChessBoard two-dimensional array of string

      // and places chess pieces at their right positions

      private void arrangeChessPieces()

      {                      

            for(int y = 0; y < 8; y++)       

            for(int x = 0; x < 8; x++) 

            {                

                  this.pnlChessCells[y][x].add(this.getPieceObject(strChessBoard[y][x]), BorderLayout.CENTER);

                  this.pnlChessCells[y][x].validate();

            }          

      }

      // This method draws chess board, i.e. black and white cells on the board

      private void drawChessBoard()

      {

            for (int y = 0; y < 8; y++)

                  for (int x = 0; x < 8; x++)

                  {

                        pnlChessCells[y][x] = new JPanel(new BorderLayout());

                        pnlChessCells[y][x].addMouseListener(this);

                        pnlMain.add(pnlChessCells[y][x]);

                        if (y % 2 == 0)

                              if (x % 2 != 0)

                                    pnlChessCells[y][x].setBackground(Color.DARK_GRAY);

                              else

                                    pnlChessCells[y][x].setBackground(Color.WHITE);

                        else

                              if (x % 2 == 0)

                                    pnlChessCells[y][x].setBackground(Color.DARK_GRAY);

                              else

                                    pnlChessCells[y][x].setBackground(Color.WHITE);

                  }

      }

      public void mouseEntered(MouseEvent e){} 

      public void mouseReleased(MouseEvent e){}

      public void mouseExited(MouseEvent e){}  

      public void mousePressed(MouseEvent e){}

      private JPanel[][] pnlChessCells = new JPanel[8][8];

      private JPanel pnlMain = new JPanel(new GridLayout(8,8));

      private String[][] strChessBoard = new String[][] { {"RB", "NB", "BB", "QB", "KB", "BB", "NB", "RB" }, {"PB", "PB", "PB", "PB", "PB", "PB", "PB", "PB"}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "}, {"PW", "PW", "PW", "PW", "PW", "PW", "PW", "PW"}, {"RW", "NW", "BW", "QW", "KW", "BW", "NW", "RW"} };

      private ImageIcon rookBlack = new ImageIcon(System.getProperty("user.dir") + "/images/RookBlack.png");

      private ImageIcon rookWhite = new ImageIcon(System.getProperty("user.dir") + "/images/RookWhite.png");

      private ImageIcon bishopBlack = new ImageIcon(System.getProperty("user.dir") + "/images/BishopBlack.png");

      private ImageIcon bishopWhite = new ImageIcon(System.getProperty("user.dir") + "/images/BishopWhite.png");

      private ImageIcon knightBlack = new ImageIcon(System.getProperty("user.dir") + "/images/KnightBlack.png");

      private ImageIcon knightWhite = new ImageIcon(System.getProperty("user.dir") + "/images/KnightWhite.png");

      private ImageIcon kingBlack = new ImageIcon(System.getProperty("user.dir") + "/images/KingBlack.png");

      private ImageIcon kingWhite = new ImageIcon(System.getProperty("user.dir") + "/images/KingWhite.png");

      private ImageIcon queenBlack = new ImageIcon(System.getProperty("user.dir") + "/images/QueenBlack.png");

      private ImageIcon queenWhite = new ImageIcon(System.getProperty("user.dir") + "/images/QueenWhite.png");

      private ImageIcon pawnBlack = new ImageIcon(System.getProperty("user.dir") + "/images/PawnBlack.png");

      private ImageIcon pawnWhite = new ImageIcon(System.getProperty("user.dir") + "/images/PawnWhite.png");

      private boolean boolMoveSelection = false, bWhite = true, bMyTurn = true;

      private Point pntMoveFrom, pntMoveTo;

      private Container c;

}