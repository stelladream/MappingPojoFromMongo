package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.model.users.User;

import java.util.ArrayList;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class GenerateClassFromMongoDB {

    private static final String DATABASE_NAME = "mydatabase";
    private static final String COLLECTION_NAME = "users";

    public static void main(String[] args) {
        // MongoDB 연결
        MongoDatabase database = MongoClients.create().getDatabase(DATABASE_NAME);

        // 자동으로 POJO 클래스 생성을 위한 Codec Provider 등록
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        database = database.withCodecRegistry(CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider)));

        // users 컬렉션에서 데이터 가져오기
        ArrayList<Object> userList = database.getCollection(COLLECTION_NAME, User.class)
                .find(Filters.eq("username", "sewoong"))
                .projection(Projections.exclude("_id"))
                .sort(Sorts.ascending("username"))
                .into(new ArrayList<>());

        // 가져온 데이터 출력
        for (Object user : userList) {
            System.out.println(user);
        }

        // User 클래스 자동 생성 및 출력
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        for (Document document : database.getCollection(COLLECTION_NAME).find()) {
            User user = mapper.convertValue(document, User.class);
            System.out.println(user);
        }
    }
}