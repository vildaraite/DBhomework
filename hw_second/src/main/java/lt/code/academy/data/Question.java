package lt.code.academy.data;
public class Question {
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String correctAnswer;

    public Question(String question, String answer_a, String answer_b, String answer_c, String correct_answer) {
        this.question = question;
        this.answerA = answer_a;
        this.answerB = answer_b;
        this.answerC = answer_c;
        this.correctAnswer = correct_answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}