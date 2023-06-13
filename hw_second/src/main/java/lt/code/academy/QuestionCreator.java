package lt.code.academy;

import lt.code.academy.data.Question;
import java.util.Scanner;

public class QuestionCreator {



    public Question createQuestion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Iveskite klausima::");
        String question = scanner.nextLine();

        System.out.println("Iveskite atsakyma 1:");
        String answerA = scanner.nextLine();

        System.out.println("Iveskite atsakyma 2:");
        String answerB = scanner.nextLine();

        System.out.println("Iveskite atsakyma 3:");
        String answerC = scanner.nextLine();

        System.out.println("Iveskite teisinga atsakyma (1, 2 arba 3):");
        String correctAnswer = scanner.nextLine();

        return new Question(question, answerA, answerB, answerC, correctAnswer);
    }
}
