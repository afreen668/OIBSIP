
package exam;

import javax.swing.*;
import java.awt.*;

public class Result extends JFrame {

    public Result(int score, Question[] questions,
                  int[] answers, int timeTaken) {

        setTitle("Online Examination System - Result");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 244, 248));

        JLabel heading = new JLabel("EXAMINATION RESULT");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(130, 25, 300, 35);
        panel.add(heading);

        JLabel scoreLabel = new JLabel(
                "Your Score: " + score + " out of " + questions.length
        );

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setBounds(150, 80, 300, 30);
        panel.add(scoreLabel);

        int minutes = timeTaken / 60;
        int seconds = timeTaken % 60;

        JLabel timeLabel = new JLabel(
                String.format("Time Taken: %02d:%02d", minutes, seconds)
        );

        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timeLabel.setBounds(180, 120, 250, 25);
        panel.add(timeLabel);

        JTextArea breakdown = new JTextArea();

        breakdown.setEditable(false);
        breakdown.setFont(new Font("Arial", Font.PLAIN, 13));

        StringBuilder resultText = new StringBuilder();

        resultText.append("ANSWER BREAKDOWN\n\n");

        for (int i = 0; i < questions.length; i++) {

            resultText.append("Question ").append(i + 1).append(": ");

            if (answers[i] == questions[i].getCorrectAnswer()) {

                resultText.append("Correct\n");

            } else {

                resultText.append("Incorrect\n");

                if (answers[i] == -1) {
                    resultText.append("Your Answer: Not Answered\n");
                } else {
                    resultText.append("Your Answer: ")
                            .append(questions[i].getOptions()[answers[i]])
                            .append("\n");
                }

                int correct = questions[i].getCorrectAnswer();

                resultText.append("Correct Answer: ")
                        .append(questions[i].getOptions()[correct])
                        .append("\n");
            }

            resultText.append("\n");
        }

        breakdown.setText(resultText.toString());

        JScrollPane scrollPane = new JScrollPane(breakdown);
        scrollPane.setBounds(50, 165, 440, 260);
        panel.add(scrollPane);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(200, 450, 130, 35);
        panel.add(logoutButton);

        logoutButton.addActionListener(e -> {

            new Login();
            dispose();

        });

        add(panel);
        setVisible(true);
    }
}
