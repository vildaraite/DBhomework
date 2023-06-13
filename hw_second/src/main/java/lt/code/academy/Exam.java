package lt.code.academy;

import com.mongodb.client.MongoCollection;
import lt.code.academy.data.Question;
import lt.code.academy.data.User;

import java.util.List;
import java.util.Scanner;


public class Exam {

    private final QuestionDao questionDao;


    private MongoCollection<User> userCollection;

    private static final Scanner scanner = new Scanner(System.in);

    private final QuestionCreator creator;

    public Exam() {
        questionDao = new QuestionDao();
        creator = new QuestionCreator();
    }


    public static void main(String[] args) {

        QuestionDao questionDao = new QuestionDao();
        Exam exam = new Exam();
        Scanner sc = new Scanner(System.in);

        String line;
        do {
            exam.menu();
            line = sc.nextLine();
            exam.userSelection(sc, line);
        } while (!line.equals("6"));

    }


    private void userSelection(Scanner sc, String action) {
        switch (action) {
            case "1" -> logging(scanner);
            case "2" -> takeAmExam(scanner);
            case "3" -> createExam();
            case "4" -> updateExam();
            case "5" -> getStatistic();
            case "6" -> {
                System.out.println("Programa baige darba");
            }
            default -> System.out.println("Tokio veiksmo nera!");
        }
    }

    private void logging(Scanner scanner) {

        System.out.println("Iveskite savo varda:");
        String username = scanner.nextLine();

        UserCollection userCollection = new UserCollection();
        userCollection.addUser(username);

        System.out.println("Vartotojas" + username + "sekmingai pridetas i duomenu baze");

    }

    private void takeAmExam(Scanner scanner) {
        List<Question> questions = questionDao.getQuestions();

        for (int i = 0; i< questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println(question.getQuestion());
            System.out.println("1. " + question.getAnswerA());
            System.out.println("2. " + question.getAnswerB());
            System.out.println("3. " + question.getAnswerC());

            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                System.out.println("Teisingai!");
            } else {
                System.out.println("Neteisingai. Teisingas atsakymas: " + question.getCorrectAnswer());
            }
        }
    }

    private void createExam() {
        System.out.println("Sukurkite naujus klausimus (iveskite 'stop', kad baigtumete):");

        QuestionCreator questionCreator = new QuestionCreator();
        String input = "";

        while (!input.equalsIgnoreCase("stop")) {
            Question question = questionCreator.createQuestion();
            questionDao.addQuestion(question);

            System.out.println("Klausimas pridetas i duomenu baze. Iveskite 'stop', jei norite baigti.");
            input = scanner.nextLine();
        }
    }

    private void updateExam() {
        System.out.println("Iveskite klausimo numeri, kuri norite pakeisti:");
        int questionNumber = scanner.nextInt();
        scanner.nextLine();

        List<Question> questions = questionDao.getQuestions();

        if (questionNumber < 1 || questionNumber > questions.size()) {
            System.out.println("Neteisingas klausimo numeris.");
            return;
        }

        Question question = creator.createQuestion();

        questionDao.updateQuestion(question);

        System.out.println("Klausimas atnaujintas duomenu bazeje.");
    }

    private void getStatistic() {
        questionDao.getStatistics();
    }


    private void menu() {
        System.out.println("""
                [1]. Iveskite savo prisijungimo varda: 
                [2]. Spresti egzamino uzduuotis:
                [3]. Sukurti egzamino uzduotis:
                [4]. Redaguoti egzamino klausimus:
                [5]. Perziureti statistikas:
                [6]. Exit
                """);
    }
}
