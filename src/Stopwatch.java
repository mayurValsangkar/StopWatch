import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import javax.swing.*;

public class Stopwatch implements ActionListener {

    // Initialize frame
    JFrame frame = new JFrame();

    // Initialize start button
    JButton startButton = new JButton("START");

    // Initialize reset button
    JButton resetButton = new JButton("RESET");

    // Initialize label
    JLabel timeLabel = new JLabel();

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    // we use this, to determine if timer is started or not
    boolean started = false;

    // Format in which seconds, minutes and hours will show.
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    // Creating new object of timer class
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            elapsedTime+=1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);

            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        }
    });

    // Constructor
    Stopwatch(){

        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);


        // Dimension and font type of start button
        startButton.setBounds(100, 200, 100, 50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);


        // Dimension and font type of reset button
        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);



        // Adding start button, reset button and time label in frame.
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setting dimension of frame
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // After hitting start button
        if(e.getSource()==startButton) {
            // If action is start then and started is false, change start button text to stop
            if(started==false) {
                started = true;
                startButton.setText("STOP");
                start();
            }
            // If started is true that means timer is running
            else{
                started = false;
                startButton.setText("START");
                stop();
            }
        }

        // After hitting reset button
        if(e.getSource()==resetButton) {
            started = false;
            startButton.setText("START");
            reset();
        }

    }

    // Start function
    void start() {
        timer.start();
    }

    // Stop function
    void stop() {
        timer.stop();
    }

    // Reset function
    void reset() {
        timer.stop();
        // Resetting all the values to zero
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
    }
}
