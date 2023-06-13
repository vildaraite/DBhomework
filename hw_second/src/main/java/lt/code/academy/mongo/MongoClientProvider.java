package lt.code.academy.mongo;


import com.mongodb.MongoClient;

public class MongoClientProvider {
    private static MongoClient client;

    private MongoClientProvider() {
    }

    public static MongoClient getClient() {
        if (client == null) {
            client = new MongoClient();
        }

        return client;
    }
}