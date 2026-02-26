import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    char player = 'X';
    JLabel status;

    TicTacToeGUI() {

        setTitle("Tic Tac Toe Game");
        setSize(400,450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        status = new JLabel("Player X Turn");
        status.setFont(new Font("Arial",Font.BOLD,20));
        status.setHorizontalAlignment(JLabel.CENTER);
        add(status,BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));

        for(int i=0;i<9;i++){
            buttons[i]=new JButton("");
            buttons[i].setFont(new Font("Arial",Font.BOLD,40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel,BorderLayout.CENTER);

        JButton reset = new JButton("Restart Game");
        reset.addActionListener(e -> resetGame());
        add(reset,BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        JButton btn=(JButton)e.getSource();

        if(btn.getText().equals("")){

            btn.setText(String.valueOf(player));

            if(checkWinner()){
                status.setText("Player "+player+" Wins!");
                disableButtons();
            }
            else if(draw()){
                status.setText("Match Draw!");
            }
            else{
                player=(player=='X')?'O':'X';
                status.setText("Player "+player+" Turn");
            }
        }
    }

    boolean checkWinner(){

        int[][] win={
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
        };

        for(int[] w:win){

            if(buttons[w[0]].getText().equals(""+player) &&
               buttons[w[1]].getText().equals(""+player) &&
               buttons[w[2]].getText().equals(""+player)){

                return true;
            }
        }

        return false;
    }

    boolean draw(){

        for(JButton b:buttons){

            if(b.getText().equals(""))
                return false;
        }

        return true;
    }

    void disableButtons(){

        for(JButton b:buttons)
            b.setEnabled(false);
    }

    void resetGame(){

        for(JButton b:buttons){

            b.setText("");
            b.setEnabled(true);
        }

        player='X';
        status.setText("Player X Turn");
    }

    public static void main(String[] args){

        new TicTacToeGUI();
    }
}