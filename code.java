package javaapplication2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JavaTicTacToe extends JFrame {

    private JButton[][] buttons = new JButton[3][3];
    private JLabel jLabelMSG;
    private int x_or_o = 0;
    private boolean win = false;

    public JavaTicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // **Main Panel with Grid + Row & Column Labels**
        JPanel mainPanel = new JPanel(new BorderLayout());

        // **Create Column Labels**
        JPanel topPanel = new JPanel(new GridLayout(1, 4));
        topPanel.add(new JLabel(""));  // Empty space
        for (int i = 0; i < 3; i++) {
            JLabel colLabel = new JLabel("Col " + (i + 1), SwingConstants.CENTER);
            topPanel.add(colLabel);
        }

        // **Create Grid with Row Labels**
        JPanel gridPanel = new JPanel(new GridLayout(3, 4));
        for (int i = 0; i < 3; i++) {
            // Add row label
            JLabel rowLabel = new JLabel("Row " + (i + 1), SwingConstants.CENTER);
            gridPanel.add(rowLabel);

            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].addActionListener(createAction(buttons[i][j]));
                gridPanel.add(buttons[i][j]);
            }
        }

        // **Message Label**
        jLabelMSG = new JLabel("Play", SwingConstants.CENTER);
        jLabelMSG.setFont(new Font("Arial", Font.BOLD, 18));
        jLabelMSG.setForeground(Color.RED);

        // **Add Components**
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        add(jLabelMSG, BorderLayout.SOUTH);
    }

    private ActionListener createAction(JButton button) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText().equals("")) {
                    if ((x_or_o % 2) == 0) {
                        button.setText("X");
                        button.setForeground(Color.BLUE);
                        jLabelMSG.setText("O turn now");
                    } else {
                        button.setText("O");
                        button.setForeground(Color.RED);
                        jLabelMSG.setText("X turn now");
                    }
                    x_or_o++;
                    getTheWinner();
                }
            }
        };
    }

    public void getTheWinner() {
        String[][] grid = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = buttons[i][j].getText();

        // **Check Rows, Columns, and Diagonals**
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].equals("") && grid[i][0].equals(grid[i][1]) && grid[i][0].equals(grid[i][2])) {
                winEffect(buttons[i][0], buttons[i][1], buttons[i][2]);
                return;
            }
            if (!grid[0][i].equals("") && grid[0][i].equals(grid[1][i]) && grid[0][i].equals(grid[2][i])) {
                winEffect(buttons[0][i], buttons[1][i], buttons[2][i]);
                return;
            }
        }
        if (!grid[0][0].equals("") && grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2])) {
            winEffect(buttons[0][0], buttons[1][1], buttons[2][2]);
            return;
        }
        if (!grid[0][2].equals("") && grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])) {
            winEffect(buttons[0][2], buttons[1][1], buttons[2][0]);
            return;
        }

        // **Check Draw**
        if (allButtonsTextLength() == 9 && !win) {
            jLabelMSG.setText("No One Wins!");
        }
    }

    public void winEffect(JButton b1, JButton b2, JButton b3) {
        b1.setBackground(Color.BLACK);
        b2.setBackground(Color.BLACK);
        b3.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        jLabelMSG.setText(b1.getText() + " is the Winner!");
        win = true;
    }

    public int allButtonsTextLength() {
        int count = 0;
        for (JButton[] row : buttons)
            for (JButton button : row)
                if (!button.getText().equals(""))
                    count++;
        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JavaTicTacToe().setVisible(true);
        });
    }
}
