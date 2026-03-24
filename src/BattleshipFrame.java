import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BattleshipFrame extends JFrame
{
    JPanel mainPnl, statsPnl, titlePnl, countPnl, boardPnl, ctrlPnl;
    JTextField totalHitTF, totalMissTF, missTF, strikeTF;
    JLabel titleLbl, totalHitLbl, totalMissLbl, missLbl, strikeLbl;
    JButton playAgainBtn, quitBtn;
    public BattleshipFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        setTitle("Battleship Game");
        setVisible(true);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statsPnl = new JPanel();
        statsPnl.setLayout(new BorderLayout());

        titlePnl = new JPanel();
        titleLbl = new JLabel("Battleship");
        titleLbl.setFont(new Font("Serif", Font.BOLD, 24));
        titlePnl.add(titleLbl);
        statsPnl.add(titlePnl, BorderLayout.NORTH);

        countPnl = new JPanel();
        totalHitLbl = new JLabel("Total Hits:");
        totalHitLbl.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        totalMissLbl = new JLabel("Total Misses:");
        totalMissLbl.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        missLbl = new JLabel("Misses in a row:");
        missLbl.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        strikeLbl = new JLabel("Strikes:");
        strikeLbl.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        totalHitTF = new JTextField();
        totalHitTF.setEditable(false);
        totalMissTF = new JTextField();
        totalMissTF.setEditable(false);
        missTF = new JTextField();
        missTF.setEditable(false);
        strikeTF = new JTextField();
        strikeTF.setEditable(false);
        countPnl.add(totalHitLbl);
        countPnl.add(totalHitTF);
        countPnl.add(totalMissLbl);
        countPnl.add(totalMissTF);
        countPnl.add(missLbl);
        countPnl.add(missTF);
        countPnl.add(strikeLbl);
        countPnl.add(strikeTF);
        statsPnl.add(countPnl, BorderLayout.CENTER);

        mainPnl.add(statsPnl, BorderLayout.NORTH);

        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(10, 10));
        BattleshipBoard board = new BattleshipBoard(boardPnl, totalHitTF, totalMissTF, missTF, strikeTF);
        mainPnl.add(boardPnl, BorderLayout.CENTER);

        createControlPanel();
    }

    public void createControlPanel()
    {
        ctrlPnl = new JPanel();
        playAgainBtn = new JButton("Play Again");
        playAgainBtn.addActionListener((ActionEvent e) -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Confirm Play Again", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION)
            {
                BattleshipBoard brd = new BattleshipBoard(boardPnl, totalHitTF, totalMissTF, missTF, strikeTF);
                brd.resetGame(totalHitTF, totalMissTF, missTF, strikeTF);
            }
        });
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent e) -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });
        ctrlPnl.add(playAgainBtn);
        ctrlPnl.add(quitBtn);
        mainPnl.add(ctrlPnl, BorderLayout.SOUTH);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new BattleshipFrame());
    }
}
