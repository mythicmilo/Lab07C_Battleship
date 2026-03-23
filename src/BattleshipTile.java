import javax.swing.*;

public class BattleshipTile extends JButton
{
    private int row;
    private int col;

    public BattleshipTile(int row, int col)
    {
        super();
        this.row = row;
        this.col = col;
    }
}
