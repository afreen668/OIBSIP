
package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exam extends JFrame {

    private JLabel questionLabel;
    private JLabel timerLabel;
    private JRadioButton[] options;
    private ButtonGroup group;

    private JButton nextButton;
    private JButton previousButton;
    private JButton submitButton;

    private int currentQuestion = 0;
    private int[] answers;

    private int timeLeft = 30 * 60;

    private Timer timer;

    private Question[] questions = {

        new Question("Which language is used for Android development?",
                new String[]{"Java", "HTML", "CSS", "SQL"}, 0),

        new Question("Which keyword is used to create a class in Java?",
                new String[]{"function", "class", "define", "new"}, 1),

        new Question("Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"}, 2),

        new Question("Which of these is not a Java data type?",
                new String[]{"int", "float", "string", "boolean"}, 2),

        new Question("Which symbol ends a statement in Java?",
                new String[]{".", ",", ";", ":"}, 2)
    };

    public Exam() {

        setTitle("Online Examination System - Exam");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        answers = new int[questions.length];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = -1;
        }

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 244, 248));

        timerLabel = new JLabel("Time Left: 30:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setBounds(400, 20, 160, 30);
        panel.add(timerLabel);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setBounds(40, 80, 520, 40);
        panel.add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {

            options[i] = new JRadioButton();
            options[i].setBounds(60, 150 + (i * 45), 400, 30);
            options[i].setBackground(new Color(240, 244, 248));

            group.add(options[i]);
            panel.add(options[i]);
        }

        previousButton = new JButton("Previous");
        previousButton.setBounds(60, 350, 110, 35);
        panel.add(previousButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(200, 350, 100, 35);
        panel.add(nextButton);

        submitButton = new JButton("Submit Exam");
        submitButton.setBounds(370, 350, 130, 35);
        panel.add(submitButton);

        previousButton.addActionListener(e -> {

            saveAnswer();

            if (currentQuestion > 0) {
                currentQuestion--;
                displayQuestion();
            }
        });

        nextButton.addActionListener(e -> {

            saveAnswer();

            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                displayQuestion();
            }
        });

        submitButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to submit your exam?",
                    "Confirm Submission",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                submitExam();
            }
        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                int choice = JOptionPane.showConfirmDialog(
                        Exam.this,
                        "Are you sure you want to quit?",
                        "Exit Exam",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    timer.stop();
                    dispose();
                }
            }
        });

        add(panel);

        displayQuestion();

        startTimer();

        setVisible(true);
    }

    private void displayQuestion() {

        Question q = questions[currentQuestion];

        questionLabel.setText(
                "Q" + (currentQuestion + 1) + ". " + q.getQuestionText()
        );

        String[] choices = q.getOptions();

        group.clearSelection();

        for (int i = 0; i < 4; i++) {

            options[i].setText(choices[i]);

            if (answers[currentQuestion] == i) {
                options[i].setSelected(true);
            }
        }

        previousButton.setEnabled(currentQuestion > 0);

        nextButton.setEnabled(currentQuestion < questions.length - 1);
    }

    private void saveAnswer() {

        for (int i = 0; i < 4; i++) {

            if (options[i].isSelected()) {
                answers[currentQuestion] = i;
                return;
            }
        }

        answers[currentQuestion] = -1;
    }

    private void startTimer() {

        timer = new Timer(1000, e -> {

            timeLeft--;

            int minutes = timeLeft / 60;
            int seconds = timeLeft % 60;

            timerLabel.setText(String.format(
                    "Time Left: %02d:%02d", minutes, seconds
            ));

            if (timeLeft <= 0) {

                timer.stop();

                JOptionPane.showMessageDialog(
                        this,
                        "Time is up! Your exam will be submitted."
                );

                submitExam();
            }
        });

        timer.start();
    }

    private void submitExam() {

        saveAnswer();

        timer.stop();

        int score = 0;

        for (int i = 0; i < questions.length; i++) {

            if (answers[i] == questions[i].getCorrectAnswer()) {
                score++;
            }
        }

        int timeTaken = (30 * 60) - timeLeft;

        new Result(score, questions, answers, timeTaken);

        dispose();
    }
}
