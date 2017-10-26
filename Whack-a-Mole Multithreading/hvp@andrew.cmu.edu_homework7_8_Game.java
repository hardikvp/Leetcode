import java.time.Instant;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * HW-7.
 * @author Hardik Panchal
 */

public class Game implements ActionListener {
    /**
     * interval .
     */
    private static int time;
    /**
     * Random numbers.
     */
    private Random random = new Random();
    /**
     * buttons.
     */
    private JButton[] buttons;
    /**
     * Font specifications.
     */
    private int j;
     /**
     * Font specifications.
     */
    private Font font = new Font(Font.SERIF, Font.BOLD, 15);
    /**
     * Text field for timeLeft.
     */
    private static JTextArea timeLeft = new JTextArea(1, 4);
    /**
     * TIme left Name Label.
     */
    private JLabel one = new JLabel("Time Left ");
    /**
     * Score text field.
     */
    private static JTextArea score = new JTextArea(1, 4);
    /**
     * Scorelabel.
     */
    private JLabel two = new JLabel("Score ");
    /**
     * Start button.
     */
    private static JButton start = new JButton("Start");
    /**
     * Main panel.
     */
    private JPanel mainPanel = new JPanel();
    /**
     * New Panel for top.
     */
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    /**
     * New Panel.
     */
    private JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    /**
     * score tracker.
     */
    private int scoreTrack = 0;
    /**
     * Constructor Directory Driver taking no parameters.
     */
    public Game() {
        JFrame window = new JFrame("Whack-a-Mole");
        window.setSize(900, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timeLeft.setText("20");
        timeLeft.setEditable(false);
        panel1.setFont(font);
        panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel2.setFont(font);
        panel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        start.addActionListener(this);
        panel1.add(start);
        panel1.add(one);
        panel1.add(timeLeft);
        panel1.add(two);
        panel1.add(score);
        panel2.setLayout((new GridLayout(11, 11)));
        panel2.setPreferredSize(new Dimension(700, 625));
        buttons = new JButton[100];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("   ");
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setFont(font);
            buttons[i].setOpaque(true);
            panel2.add(buttons[i]);
            buttons[i].addActionListener(this);
        }
        window.add(mainPanel);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        window.setContentPane(mainPanel);
        window.setVisible(true);
    }
    /**
     * Thread to add acrions to buttons.
     * @param event event
     */
    public void actionPerformed(ActionEvent event) {
        final Thread[] buttonThread = new Thread[buttons.length];
        if (event.getSource() == start) {
            start.setEnabled(false);
            final String secs = "20";
            time = Integer.parseInt(secs);
            Thread timerThread = new Thread(new Runnable() {
               public void run() {
                    timeLeft.setText(Integer.toString(time));
                    while (time > 0) {
                        try {
                            --time;
                            Thread.sleep(1000);
                            timeLeft.setText(Integer.toString(time));
                            } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time = 20;
                    scoreTrack  = 0;
                    timeLeft.setText(Integer.toString(time));
                    score.setText(Integer.toString(scoreTrack));
                    start.setEnabled(true);
                }
               });
            timerThread.start();
            /**
             * Thread to add acrions to buttons.
             */
            class MoleThread implements Runnable {
                private JButton button;
                /**
                 * Thread to add acrions to buttons.
                 * @param button1 button
                 */
                MoleThread(JButton button1) {
                    this.button = button1;
                    }
                /**
                 * Thread to add acrions to buttons.
                 */
                public void run() {
                    long lStartTime = Instant.now().toEpochMilli();
                    int min = 500;
                    int max = 4000;
                    int randTime = min + random.nextInt(max);
                    long lEndTime = Instant.now().toEpochMilli();
                    synchronized (button) {
                        try {
                            while ((lEndTime - lStartTime) / 1000 <= 20) {
                                if (button.getText().equals("   ")) {
                                    button.setText(":)");
                                    button.setBackground(Color.GREEN);
                                    } else {
                                        button.setText("   ");
                                        button.setBackground(Color.LIGHT_GRAY);
                                        }
                                Thread.sleep(randTime);
                                lEndTime = Instant.now().toEpochMilli();
                                }
                            } catch (InterruptedException e) {
                                throw new AssertionError("Game Ended");
                                }
                            if ((lEndTime - lStartTime) / 1000 >= 20) {
                                button.setText("   ");
                                button.setBackground(Color.LIGHT_GRAY);
                                }
                            }
                    }
                }
            for (j = 0; j < buttons.length; j++) {
                JButton button = buttons[random.nextInt(buttons.length)];
                buttonThread[j] = new Thread(new MoleThread(button));
                buttonThread[j].start();
                }
            }
        for (int i = 0; i < buttons.length; i++) {
            if (event.getSource() == buttons[i]) {
                if (buttons[i].getText().equals(":)")) {
                    if (!(time < 1)) {
                        buttons[i].setText(":(");
                        buttons[i].setBackground(Color.RED);
                        String scoreCard = new String();
                        scoreTrack++;
                        scoreCard = Integer.toString(scoreTrack);
                        score.setText(scoreCard);
                        }
                    }
                }
            }
        }
    /**
     * Main Method.
     * @param args number of lights and sleep milliseconds
     */
    public static void main(String[] args) {
        new Game();
        // TODO Auto-generated method stub
    }
}



