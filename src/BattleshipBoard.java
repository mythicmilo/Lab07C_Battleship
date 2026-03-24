import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BattleshipBoard
{
    private final int[] ships = {5, 4, 3, 3, 2};
    private final int ROWS = 10;
    private final int COLS = 10;
    private BattleshipTile[][] board = new BattleshipTile[ROWS][COLS];
    private String[][] shipBoard = new String[ROWS][COLS];
    private ImageIcon oceanIcon = new ImageIcon("src/ocean.jpg");
    private ImageIcon splashIcon = new ImageIcon("src/splash.jpg");
    private ImageIcon explosionIcon = new ImageIcon("src/explosion.jpg");
    private int missCount = 0;
    private int totalMissCount = 0;
    private int strikeCount = 0;
    private int totalHitCount = 0;
    private int shipCount = 1;
    private int sunkCount = 0;
    private ArrayList<Integer> ship1 = new ArrayList<>();
    private ArrayList<Integer> ship2 = new ArrayList<>();
    private ArrayList<Integer> ship3 = new ArrayList<>();
    private ArrayList<Integer> ship4 = new ArrayList<>();
    private ArrayList<Integer> ship5 = new ArrayList<>();

    public BattleshipBoard(JPanel boardPnl, JTextField totalHitTF,
                           JTextField totalMissTF, JTextField missTF,
                           JTextField strikeTF)
    {
        totalHitTF.setText("" + totalHitCount);
        totalMissTF.setText("" + totalMissCount);
        missTF.setText("" + missCount);
        strikeTF.setText("" + strikeCount);
        ActionListener tileListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object tile = e.getSource();
                checkShipBoard(tile, totalHitTF, totalMissTF, missTF, strikeTF);
            }
        };
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = new BattleshipTile(row, col);
                board[row][col].setIcon(oceanIcon);
                board[row][col].addActionListener(tileListener);
                boardPnl.add(board[row][col]);
            }
        }
        placeShips();
    }

    public void resetGame(JTextField totalHitTF,
                          JTextField totalMissTF, JTextField missTF,
                          JTextField strikeTF)
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col].setIcon(oceanIcon);
                board[row][col].setEnabled(true);
            }
        }
        missCount = 0;
        missTF.setText("" + missCount);
        totalMissCount = 0;
        totalMissTF.setText("" + totalMissCount);
        strikeCount = 0;
        strikeTF.setText("" + strikeCount);
        totalHitCount = 0;
        totalHitTF.setText("" + totalHitCount);
        shipCount = 1;
        placeShips();
    }

    public void checkShipBoard(Object tile, JTextField totalHitTF,
                               JTextField totalMissTF, JTextField missTF,
                               JTextField strikeTF)
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                if (tile == board[row][col])
                {
                    if (shipBoard[row][col].equals("placed"))
                    {
                        shipBoard[row][col] = "hit";
                        board[row][col].setIcon(explosionIcon);
                        board[row][col].setDisabledIcon(explosionIcon);
                        totalHitCount++;
                        totalHitTF.setText("" + totalHitCount);
                        missCount = 0;
                        missTF.setText("" + missCount);
                        checkShipStatus(row, col, totalHitTF, totalMissTF, missTF, strikeTF);
                    }
                    else
                    {
                        board[row][col].setIcon(splashIcon);
                        board[row][col].setDisabledIcon(splashIcon);
                        totalMissCount++;
                        totalMissTF.setText("" + totalMissCount);
                        missCount++;
                        missTF.setText("" + missCount);
                        if (missCount == 5)
                        {
                            strikeCount++;
                            strikeTF.setText("" + strikeCount);
                            missCount = 0;
                            checkStrike(totalHitTF, totalMissTF, missTF, strikeTF);
                        }
                    }
                    board[row][col].setEnabled(false);
                }
            }
        }
    }

    public void checkShipStatus(int row, int col, JTextField totalHitTF,
                                JTextField totalMissTF, JTextField missTF,
                                JTextField strikeTF)
    {
        int hitCount = 1;
        if (ship1.contains(row) && ship1.contains(col))
        {
            for (int i = 1; i < ship1.size(); i++)
            {
                if (shipBoard[ship1.getFirst()][ship1.get(i)].equals("hit"))
                {
                    hitCount++;
                }
            }
            if (hitCount == ship1.size())
            {
                sunkCount++;
                JOptionPane.showMessageDialog(null, "The ship has sunk!");
                checkWin(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        }
        else if (ship2.contains(row) && ship2.contains(col))
        {
            for (int i = 1; i < ship2.size(); i++)
            {
                if (shipBoard[ship2.getFirst()][ship2.get(i)].equals("hit"))
                {
                    hitCount++;
                }
            }
            if (hitCount == ship2.size())
            {
                sunkCount++;
                JOptionPane.showMessageDialog(null, "The ship has sunk!");
                checkWin(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        }
        else if (ship3.contains(row) && ship3.contains(col))
        {
            for (int i = 1; i < ship3.size(); i++)
            {
                if (shipBoard[ship3.getFirst()][ship3.get(i)].equals("hit"))
                {
                    hitCount++;
                }
            }
            if (hitCount == ship3.size())
            {
                sunkCount++;
                JOptionPane.showMessageDialog(null, "The ship has sunk!");
                checkWin(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        }
        else if (ship4.contains(row) && ship4.contains(col))
        {
            for (int i = 1; i < ship4.size(); i++)
            {
                if (shipBoard[ship4.getFirst()][ship4.get(i)].equals("hit"))
                {
                    hitCount++;
                }
            }
            if (hitCount == ship4.size())
            {
                sunkCount++;
                JOptionPane.showMessageDialog(null, "The ship has sunk!");
                checkWin(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        }
        else if (ship5.contains(row) && ship5.contains(col))
        {
            for (int i = 1; i < ship5.size(); i++)
            {
                if (shipBoard[ship5.getFirst()][ship5.get(i)].equals("hit"))
                {
                    hitCount++;
                }
            }
            if (hitCount == ship5.size())
            {
                sunkCount++;
                JOptionPane.showMessageDialog(null, "The ship has sunk!");
                checkWin(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        }
    }
    public void checkWin(JTextField totalHitTF,
                         JTextField totalMissTF, JTextField missTF,
                         JTextField strikeTF)
    {
        if (sunkCount == shipCount)
        {
            JOptionPane.showMessageDialog(null, "You won!");
            int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Confirm Play Again", JOptionPane.YES_NO_OPTION);
            if (playAgain == JOptionPane.YES_OPTION)
            {
                resetGame(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        }
    }

    public void checkStrike(JTextField totalHitTF,
                            JTextField totalMissTF, JTextField missTF,
                            JTextField strikeTF)
    {
        if (strikeCount == 3)
        {
            JOptionPane.showMessageDialog(null, "You Lost!");
            int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Confirm Play Again", JOptionPane.YES_NO_OPTION);
            if (playAgain == JOptionPane.YES_OPTION)
            {
                resetGame(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        }
    }

    public void placeShips()
    {
        emptyShipBoard();
        for (int i = 0; i < ships.length; i++)
        {
            boolean placed = false;
            do
            {
                Random rnd = new Random();
                int orientation = rnd.nextInt(2);
                if (orientation == 0)
                {
                    int row = rnd.nextInt(10);
                    int colStart = rnd.nextInt(9);
                    placed = scanShipBoardHorz(row, colStart, ships[i]);
                }
                else
                {
                    int col = rnd.nextInt(10);
                    int rowStart = rnd.nextInt(9);
                    placed = scanShipBoardVert(col, rowStart, ships[i]);
                }
            }
            while(!placed);
        }
    }

    private void emptyShipBoard()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                shipBoard[row][col] = "empty";
            }
        }
    }

    private boolean scanShipBoardHorz(int row, int colStart, int shipLength)
    {
        for (int i = 0; i < shipLength; i++)
        {
            if (colStart + i == COLS)
            {
                i--;
                int colStop = colStart + i;
                for (int r = colStart; r <= colStop; r++)
                {
                    shipBoard[row][colStart] = "empty";
                }
                return false;
            }
            else if (shipBoard[row][colStart + i].equals("placed"))
            {
                int colStop = colStart + i;
                for (int r = colStart; r < colStop; r++)
                {
                    shipBoard[row][colStart] = "empty";
                }
                return false;
            }
            else
            {
                shipBoard[row][colStart + i] = "placed";
            }
        }
        if (shipCount == 1)
        {
            ship1.add(row);
            for (int j = 0; j < shipLength; j++)
            {
                int col = colStart + j;
                ship1.add(col);
            }
            shipCount++;
        }
        else if (shipCount == 2)
        {
            ship2.add(row);
            for (int j = 0; j < shipLength; j++)
            {
                int col = colStart + j;
                ship2.add(col);
            }
            shipCount++;
        }
        else if (shipCount == 3)
        {
            ship3.add(row);
            for (int j = 0; j < shipLength; j++)
            {
                int col = colStart + j;
                ship3.add(col);
            }
            shipCount++;
        }
        else if (shipCount == 4)
        {
            ship4.add(row);
            for (int j = 0; j < shipLength; j++)
            {
                int col = colStart + j;
                ship4.add(col);
            }
            shipCount++;
        }
        else
        {
            ship5.add(row);
            for (int j = 0; j < shipLength; j++)
            {
                int col = colStart + j;
                ship5.add(col);
            }
        }
        return true;
    }

    private boolean scanShipBoardVert(int col, int rowStart, int shipLength)
    {
        for (int i = 0; i < shipLength; i++)
        {
            if (rowStart + i == ROWS)
            {
                i--;
                int rowStop = rowStart + i;
                for (int r = rowStart; r <= rowStop; r++)
                {
                    shipBoard[rowStart][col] = "empty";
                }
                return false;
            }
            else if (shipBoard[rowStart + i][col].equals("placed"))
            {
                int rowStop = rowStart + i;
                for (int r = rowStart; r < rowStop; r++)
                {
                    shipBoard[rowStart][col] = "empty";
                }
                return false;
            }
            else
            {
                shipBoard[rowStart + i][col] = "placed";
            }
        }
        if (shipCount == 1)
        {
            ship1.add(col);
            for (int j = 0; j < shipLength; j++)
            {
                int row = rowStart + j;
                ship1.add(row);
            }
            shipCount++;
        }
        else if (shipCount == 2)
        {
            ship2.add(col);
            for (int j = 0; j < shipLength; j++)
            {
                int row = rowStart + j;
                ship2.add(row);
            }
            shipCount++;
        }
        else if (shipCount == 3)
        {
            ship3.add(col);
            for (int j = 0; j < shipLength; j++)
            {
                int row = rowStart + j;
                ship3.add(row);
            }
            shipCount++;
        }
        else if (shipCount == 4)
        {
            ship4.add(col);
            for (int j = 0; j < shipLength; j++)
            {
                int row = rowStart + j;
                ship4.add(row);
            }
            shipCount++;
        }
        else
        {
            ship5.add(col);
            for (int j = 0; j < shipLength; j++)
            {
                int row = rowStart + j;
                ship5.add(row);
            }
            shipCount++;
        }
        return true;
    }
}
