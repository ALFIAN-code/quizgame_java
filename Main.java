import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String correctAnswer;

    public Question(String questionText, String optionA, String optionB, String optionC, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

class QuizGUI extends JFrame implements ActionListener {
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JRadioButton optionA, optionB, optionC;
    private ButtonGroup optionsGroup;
    private JButton nextButton;

    public QuizGUI() {
        setTitle("Game Kuis Sederhana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize questions
        questions = new ArrayList<>();
        populateQuestions();

        // Question label
        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        // Options panel with radio buttons
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(3, 1));
        
        optionA = new JRadioButton();
        optionB = new JRadioButton();
        optionC = new JRadioButton();
        
        optionsGroup = new ButtonGroup();
        optionsGroup.add(optionA);
        optionsGroup.add(optionB);
        optionsGroup.add(optionC);
        
        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        
        add(optionsPanel, BorderLayout.CENTER);

        // Next button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        // Load the first question
        loadQuestion(currentQuestionIndex);

        setVisible(true);
    }

    private void populateQuestions() {
        questions.add(new Question("Apa ibu kota dari negara Indonesia?", "Jakarta", "Bandung", "Surabaya", "A"));
        questions.add(new Question("Siapa penemu bahasa pemrograman Java?", "James Gosling", "Dennis Ritchie", "Guido van Rossum", "A"));
        questions.add(new Question("Berapa hasil dari 9 + 10?", "19", "21", "20", "A"));
        questions.add(new Question("Apa bahasa pemrograman yang sering digunakan untuk pengembangan aplikasi Android?", "Python", "Java", "Ruby", "B"));
        questions.add(new Question("Hewan nasional dari negara Australia adalah?", "Koala", "Kanguru", "Panda", "B"));
    }

    private void loadQuestion(int index) {
        if (index < questions.size()) {
            Question question = questions.get(index);
            questionLabel.setText(question.getQuestionText());
            optionA.setText("A. " + question.getOptionA());
            optionB.setText("B. " + question.getOptionB());
            optionC.setText("C. " + question.getOptionC());
            optionsGroup.clearSelection();
        }
    }

    private void checkAnswer() {
        Question question = questions.get(currentQuestionIndex);
        String selectedAnswer = "";

        if (optionA.isSelected()) {
            selectedAnswer = "A";
        } else if (optionB.isSelected()) {
            selectedAnswer = "B";
        } else if (optionC.isSelected()) {
            selectedAnswer = "C";
        }

        // Cek jawaban dan tampilkan pop-up jika benar
        if (selectedAnswer.equals(question.getCorrectAnswer())) {
            JOptionPane.showMessageDialog(this, "Anda menjawab benar!!");
            score++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentQuestionIndex < questions.size()) {
            checkAnswer();
            currentQuestionIndex++;

            if (currentQuestionIndex < questions.size()) {
                loadQuestion(currentQuestionIndex);
            } else {
                showResult();
            }
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Kuis selesai! Skor Anda adalah " + score + " dari " + questions.size());
        System.exit(0); // Tutup aplikasi setelah selesai
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizGUI());
    }
}
