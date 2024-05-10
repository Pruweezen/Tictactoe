/*TicTac Toe is fun!
Jaquilmac, Fruee Jane*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

class Tictactoe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean playerXTurn;
    private int moveCount;
    private JLabel statusLabel;
    private JButton newGameButton;

    public Tictactoe() {
        buttons = new JButton[3][3];
        playerXTurn = true;
        moveCount = 0;


        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        statusLabel = new JLabel("Player X's Turn");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        gridPanel.setBorder(new LineBorder(Color.BLACK, 2));
        add(gridPanel, BorderLayout.CENTER);
        
    
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.setFocusPainted(false);
                button.setBackground(Color.WHITE);
                button.setBorder(new LineBorder(new Color(36, 61, 20), 2)); // Dark green border
                button.addActionListener(this);

                buttons[row][col] = button;
               gridPanel.add(button);
            }
        }
            newGameButton = new JButton("New Game");
            newGameButton.setFont(new Font("Arial", Font.BOLD, 14));
            newGameButton.addActionListener(e -> resetGame());
            add(newGameButton, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().isEmpty()) {
            return;
        }


        if (playerXTurn) {
            clickedButton.setText("X");
            statusLabel.setText("Player O's Turn");
        } else {
            clickedButton.setText("O");
            statusLabel.setText("Player X's Turn");
        }

        moveCount++;
        playerXTurn = !playerXTurn;


        String winner = checkForWinner();
        if (winner != null) {
            JOptionPane.showMessageDialog(this, winner + " wins!");
            resetGame();
        } else if (moveCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private String checkForWinner() {

        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())
                    && !buttons[i][0].getText().isEmpty()) {
                return buttons[i][0].getText();
            }

            if (buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[1][i].getText().equals(buttons[2][i].getText())
                    && !buttons[0][i].getText().isEmpty()) {
                return buttons[0][i].getText();
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()) {
            return buttons[0][0].getText();
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().isEmpty()) {
            return buttons[0][2].getText();
        }

        return null;
    }

    private void resetGame() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col] != null) {
                    buttons[row][col].setText("");
                }
            }
        }

        
        playerXTurn = true;
        moveCount = 0; 
        statusLabel.setText("Player X's Turn"); // Reset the status label
    }


    public static void main(String[] args) {
        new Tictactoe();
    }
}
