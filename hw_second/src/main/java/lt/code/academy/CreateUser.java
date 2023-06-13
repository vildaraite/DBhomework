package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lt.code.academy.mongo.MongoClientProvider;
import org.bson.Document;

import java.util.List;

public class CreateUser {
    public static void main(String[] args) {


        MongoClient client = MongoClientProvider.getClient();
        MongoDatabase database = client.getDatabase("exam");
        MongoCollection<Document> users = database.getCollection("users");

        Document student = new Document("name", "Vilda");

        Document studentTwo = new Document("name", "Lukas");

        Document studentThree = new Document("name", "Simonas");
        users.insertMany(List.of(student, studentTwo, studentThree));

        System.out.println("Vartotojai buvo uzregistruoti sekmingai");
    }
}
