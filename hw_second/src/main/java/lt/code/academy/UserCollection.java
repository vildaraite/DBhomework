package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lt.code.academy.mongo.MongoClientProvider;
import org.bson.Document;

public class UserCollection {

    private static final String DATABASE_NAME = "exam";
    private static final String COLLECTION_NAME = "users";

    private final MongoCollection<Document> collection = null;

    public UserCollection() {
        MongoClient client = MongoClientProvider.getClient();
        MongoDatabase database = client.getDatabase(DATABASE_NAME);
        MongoCollection<Document> users = database.getCollection(COLLECTION_NAME);
    }

    public void addUser(String username) {
        Document document = new Document("username", username);
        collection.insertOne(document);
    }
}
