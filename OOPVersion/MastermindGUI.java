package OOPVersion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MastermindGUI extends JFrame{

    public MastermindGUI(){
        super("MASTERMIND");

        JPanel board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        board.setBackground(Color.ORANGE);

        board.add(createOptionBar());
        board.add(Box.createHorizontalStrut(30));
        board.add(createGameBoard());

        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(550, 0);
        setSize(500,600);
        setVisible(true);
    }

    public static void main(String[] args) {
        MastermindGUI GUI = new MastermindGUI();
    }

    private JPanel createOptionBar(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("START GAME");
        startButton.setFont(new Font("Serif", Font.PLAIN, 18));
        startButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.DARK_GRAY));
        startButton.setBackground(Color.WHITE);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.print("Play Game");
                //Choose players
                //Choose difficulty level
            }
        });


        JButton leaderBoardButton = new JButton("LEADERBOARD");
        leaderBoardButton.setFont(new Font("Serif", Font.PLAIN, 18));
        leaderBoardButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.DARK_GRAY));
        leaderBoardButton.setBackground(Color.LIGHT_GRAY);
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //open leaderboard
                System.out.print("Open Leaderboard");
            }
        });

        JButton quitButton = new JButton("QUIT");
        quitButton.setFont(new Font("Serif", Font.PLAIN, 18));
        quitButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.DARK_GRAY));
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.WHITE);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int quit = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?",
                        "QUIT",JOptionPane.YES_NO_OPTION);

                if(quit == JOptionPane.YES_OPTION)
                    System.exit(0);

            }
        });

        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(leaderBoardButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(quitButton);
        panel.setBackground(Color.BLUE);

        return panel;
    }
    private JPanel createGameBoard(){

        JPanel boardGame = new JPanel();
        boardGame.setLayout(new FlowLayout());

        boardGame.add(createPanel1());
        boardGame.add(Box.createHorizontalStrut(10));
        boardGame.add(createPanel2());
        boardGame.add(createPanel3());

        boardGame.setBackground(Color.LIGHT_GRAY);

        return boardGame;

    }
    private JPanel createPanel1(){
        JLabel[] holes = new JLabel[40];

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(10, 4, 5, 10));

        //instead of Labels text add shape - white circle
        for(int i = 0; i < holes.length; i++){
            holes[i] = new JLabel("     ");
            holes[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.DARK_GRAY));
            panel1.add(holes[i]);
        }

        panel1.setBackground(Color.YELLOW);

        return panel1;
    }
    private JPanel createPanel2(){

        JLabel[] hints = new JLabel[40];

        JPanel panel2 = new JPanel();

        panel2.setLayout(new GridLayout(10, 4, 5, 10));

        //instead of Labels text add shape
        for(int i = 0; i < hints.length; i++){
            hints[i] = new JLabel("     ");
            hints[i].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.GRAY));
            panel2.add(hints[i]);
        }

        panel2.setBackground(Color.MAGENTA);

        return panel2;

    }

    private JPanel createPanel3(){

        JLabel[] allColours = new JLabel[8];
        String[] colours = {" w ", " y ", " o ", " r ", " p ", " b ", " g ", " v "};

        JPanel panel3 = new JPanel();


        panel3.setLayout(new GridLayout(4,2));

        //colour each label differently and rejig Layout Manager - getting messy

        for(int i = 0; i < allColours.length; i++){
            allColours[i] = new JLabel(colours[i]);
            allColours[i].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.WHITE));
            panel3.add(allColours[i]);
        }

        panel3.setBackground(Color.CYAN);

        return panel3;

    }
}
