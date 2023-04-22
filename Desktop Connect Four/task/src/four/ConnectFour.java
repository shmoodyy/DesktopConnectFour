package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {

    static boolean isFirstPlayer = true;
    static boolean gameOver = false;

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("Connect 4");

        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel borderPanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(6, 7));
        JButton[][] cellButton = new JButton[6][7];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                String buttonName = String.format("%c" + (6 - row), (char) ('A' + col));
                gridPanel.add(cellButton[row][col] = new JButton(" ")).setName("Button" + buttonName);
                cellButton[row][col].setBackground(Color.GRAY);
                cellButton[row][col].addActionListener(e -> {
                    char[] cell = buttonName.replaceAll("Button", "").toCharArray();
                    int foundCol = cell[cell.length - 2] - 65;
                    for (int insertRow = 5; insertRow >= 0; insertRow--) {
                        String player = isFirstPlayer ? "X" : "O";
                        if (cellButton[insertRow][foundCol].getText().equals(" ") && !gameOver){
                            cellButton[insertRow][foundCol].setText(player);
                            gameOver = hasWon(cellButton, player);
                            break;
                        }
                    }
                    isFirstPlayer = !isFirstPlayer;
                });
            }
        }

        borderPanel.add(gridPanel, BorderLayout.CENTER);
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(e -> {
            gameOver = false;
            isFirstPlayer = true;
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7; col++) {
                    cellButton[row][col].setBackground(Color.GRAY);
                    cellButton[row][col].setText(" ");
                }
            }
        });
        borderPanel.add(resetButton, BorderLayout.SOUTH);

        getContentPane().add(borderPanel);
        setVisible(true);
    }

    public static boolean hasWon(JButton[][] board, String player) {
        int numRows = board.length;
        int numCols = board[0].length;

        // Horizontal
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols - 3; col++) {
                if (board[row][col].getText().equals(player) &&
                        board[row][col + 1].getText().equals(player) &&
                        board[row][col + 2].getText().equals(player) &&
                        board[row][col + 3].getText().equals(player)) {
                    board[row][col].setBackground(Color.CYAN);
                    board[row][col + 1].setBackground(Color.CYAN);
                    board[row][col + 2].setBackground(Color.CYAN);
                    board[row][col + 3].setBackground(Color.CYAN);
                    return true;
                }
            }
        }

        // Vertical
        for (int row = 0; row < numRows - 3; row++) {
            for (int col = 0; col < numCols; col++) {
                if (board[row][col].getText().equals(player) &&
                        board[row + 1][col].getText().equals(player) &&
                        board[row + 2][col].getText().equals(player) &&
                        board[row + 3][col].getText().equals(player)) {
                    board[row][col].setBackground(Color.CYAN);
                    board[row + 1][col].setBackground(Color.CYAN);
                    board[row + 2][col].setBackground(Color.CYAN);
                    board[row + 3][col].setBackground(Color.CYAN);
                    return true;
                }
            }
        }

        // Diagonal (ascending)
        for (int row = 3; row < numRows; row++) {
            for (int col = 0; col < numCols - 3; col++) {
                if (board[row][col].getText().equals(player) &&
                        board[row - 1][col + 1].getText().equals(player) &&
                        board[row - 2][col + 2].getText().equals(player) &&
                        board[row - 3][col + 3].getText().equals(player)) {
                    board[row][col].setBackground(Color.CYAN);
                    board[row - 1][col + 1].setBackground(Color.CYAN);
                    board[row - 2][col + 2].setBackground(Color.CYAN);
                    board[row - 3][col + 3].setBackground(Color.CYAN);
                    return true;
                }
            }
        }


        // Diagonal (descending)
        for (int row = 3; row < numRows; row++) {
            for (int col = 3; col < numCols; col++) {
                if (board[row][col].getText().equals(player) &&
                        board[row - 1][col - 1].getText().equals(player) &&
                        board[row - 2][col - 2].getText().equals(player) &&
                        board[row - 3][col - 3].getText().equals(player)) {
                    board[row][col].setBackground(Color.CYAN);
                    board[row - 1][col - 1].setBackground(Color.CYAN);
                    board[row - 2][col - 2].setBackground(Color.CYAN);
                    board[row - 3][col - 3].setBackground(Color.CYAN);
                    return true;
                }
            }
        }

        // Otherwise
        return false;
    }
}