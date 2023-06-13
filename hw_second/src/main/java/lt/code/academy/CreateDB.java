package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lt.code.academy.mongo.MongoClientProvider;
import org.bson.Document;

public class CreateDB {

    public static void main(String[] args) {

        MongoClient client = MongoClientProvider.getClient();
        MongoDatabase database = client.getDatabase("exam");
        MongoCollection<Document> allQuestions = database.getCollection("questions");


        Document question1 = new Document("question", "Kur yra Didysis barjerinis rifas?")
                .append("Answer_a", "Australija")
                .append("Answer_b", "Naujoji Zelandija")
                .append("Answer_3", "JAV Utah")
                .append("Correct_answer", "1");

        Document question2 = new Document("question", "Kokia yra rečiausia kraujo grupė?")
                .append("Answer_a", "B-neigiama")
                .append("Answer_b", "AB-neigiama")
                .append("Answer_c", "A-neigiama")
                .append("Correct_answer", "2");

        Document question3 = new Document("question", "Kuri JAV valstija yra žinoma dėl persikų?")
                .append("Answer_a", "Dzordzija")
                .append("Answer_b", "Nevada")
                .append("Answer_c", "Florida")
                .append("Correct_answer", "1");

        Document question4 = new Document("question", "Kurios atmosferos dujos yra labiausiai paplitusios?")
                .append("Answer_a", "Azotas")
                .append("Answer_b", "Deguonis")
                .append("Answer_c", "Argonas")
                .append("Correct_answer", "1");

        Document question5 = new Document("question", "Kuriais metais buvo išleistas pirmasis „iPhone“ modelis?")
                .append("Answer_a", "2008")
                .append("Answer_b", "2010")
                .append("Answer_c", "2007")
                .append("Correct_answer", "3");

        allQuestions.insertOne(question1);
        allQuestions.insertOne(question2);
        allQuestions.insertOne(question3);
        allQuestions.insertOne(question4);
        allQuestions.insertOne(question5);

        System.out.println(database.getName() + " duomenu baze buvo sukurta");
        System.out.println(allQuestions.getNamespace().getCollectionName() + " kolekcija buvo sukurta");
    }
}