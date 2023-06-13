package lt.code.academy;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lt.code.academy.data.Question;
import lt.code.academy.mongo.MongoClientProvider;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuestionDao {
    private static final String DATABASE_NAME = "exam";
    private static final String COLLECTION_NAME = "questions";

    private final MongoCollection<Document> collection;

    private final Scanner scanner;

    public QuestionDao() {
        MongoClient client = MongoClientProvider.getClient();
        MongoDatabase database = client.getDatabase(DATABASE_NAME);
        collection = database.getCollection(COLLECTION_NAME);
        scanner = new Scanner(System.in);
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        for (Document document : collection.find()) {
            Question question = new Question(
                    document.getString("question"),
                    document.getString("Answer_a"),
                    document.getString("Answer_b"),
                    document.getString("Answer_c"),
                    document.getString("Correct_answer")
            );
            questions.add(question);

            Collections.shuffle(questions);
        }

        return questions;
    }

    void addQuestion(Question question) {
        Document document = new Document()
                .append("question", question.getQuestion())
                .append("answer_a", question.getAnswerA())
                .append("answer_b", question.getAnswerB())
                .append("answer_c", question.getAnswerC())
                .append("correct_answer", question.getCorrectAnswer());

        collection.insertOne(document);
    }

    void updateQuestion(Question question) {

        System.out.println("Įveskite klausimo numerį, kurį norite pakeisti:");
        int questionNumber = scanner.nextInt();
        scanner.nextLine(); // nuskaitome likusią eilutę

        BasicDBObject query = new BasicDBObject("question", question.getQuestion());
        Document document = new Document()
                .append("question", question.getQuestion())
                .append("answer_a", question.getAnswerA())
                .append("answer_b", question.getAnswerB())
                .append("answer_c", question.getAnswerC())
                .append("correct_answer", question.getCorrectAnswer());

        collection.replaceOne(query, document);
    }

    void getStatistics() {
        List<Question> questions = getQuestions();
        int totalAttempts = 0;
        int totalCorrectAnswers = 0;
        int totalA = 0;
        int totalB = 0;
        int totalC = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            int attempts = 0;
            int correctAnswers = 0;
            int a = 0;
            int b = 0;
            int c = 0;

            for (Document document : collection.find()) {
                if (document.getString("question").equals(question.getQuestion())) {
                    attempts++;

                    String answer = document.getString("answer");

                    if (answer == null || answer.isEmpty()) {
                        answer = "A";
                    }

                    if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                        correctAnswers++;
                    }

                    if (answer.equalsIgnoreCase("A")) {
                        a++;
                    } else if (answer.equalsIgnoreCase("B")) {
                        b++;
                    } else if (answer.equalsIgnoreCase("C")) {
                        c++;
                    }
                }
            }

            totalAttempts += attempts;
            totalCorrectAnswers += correctAnswers;
            totalA += a;
            totalB += b;
            totalC += c;

            System.out.println("Klausimas " + (i + 1) + ": " + question.getQuestion());
            System.out.println("Teisingų atsakymų: " + correctAnswers + "/" + attempts);
            System.out.println("A: " + a);
            System.out.println("B: " + b);
            System.out.println("C: " + c);
            System.out.println();
        }

        System.out.println("Iš viso egzaminų buvo sprendžiama: " + totalAttempts);
        System.out.println("Iš jų teisingų atsakymų buvo: " + totalCorrectAnswers);
        System.out.println("Vidutiniškai teisingų atsakymų: " + ((double) totalCorrectAnswers / totalAttempts));
        System.out.println("Pasirinkta A: " + totalA);
        System.out.println("Pasirinkta B: " + totalB);
        System.out.println("Pasirinkta C: " + totalC);
    }
}


