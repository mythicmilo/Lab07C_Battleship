import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleshipBoard
{
    private BattleshipTile[][] board;
    private String[][] shipBoard;
    private ImageIcon icon;
    private final int[] ships = {5, 4, 3, 3, 2};
    private final int ROW = 10;
    private final int COL = 10;

    public BattleshipBoard(JPanel boardPnl)
    {
        board = new BattleshipTile[ROW][COL];
        icon = new ImageIcon("src/ocean.jpg");
        ActionListener tileListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        };
        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                board[row][col] = new BattleshipTile(row, col);
                board[row][col].setIcon(icon);
                board[row][col].addActionListener(tileListener);
                boardPnl.add(board[row][col]);
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
        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                shipBoard[row][col] = "empty";
            }
        }
    }

    private void resetRowPlacement(int row, int colStart, int shipLength)
    {
        for (int r = 0; r < shipLength; r++)
        {
            if (shipBoard[row][colStart + r].equals("placed"))
            {
                shipBoard[row][colStart + r] = "empty";
            }
        }
    }

    private void resetColPlacement(int col, int rowStart, int shipLength)
    {
        for (int r = 0; r < shipLength; r++)
        {
            if (shipBoard[rowStart + r][col].equals("placed"))
            {
                shipBoard[rowStart + r][col] = "empty";
            }
        }
    }

    private boolean scanShipBoardHorz(int row, int colStart, int shipLength)
    {
        for (int i = 0; i < shipLength; i++)
        {
            if (shipBoard[row][colStart + i].equals("empty"))
            {
                shipBoard[row][colStart + i] = "placed";
            }
            else
            {
                resetRowPlacement(row, colStart, shipLength);
                return false;
            }
        }
        return true;
    }

    private boolean scanShipBoardVert(int col, int rowStart, int shipLength)
    {
        for (int i = 0; i < shipLength; i++)
        {
            if (shipBoard[rowStart + i][col].equals("empty"))
            {
                shipBoard[rowStart + i][col] = "placed";
            }
            else
            {
                resetColPlacement(col, rowStart, shipLength);
                return false;
            }
        }
        return true;
    }
}
