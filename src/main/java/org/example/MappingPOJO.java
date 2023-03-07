package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.types.ObjectId;
import org.example.model.users.*;


public class MappingPOJO {

    public static void main(String[] args) {
        ConnectionString connectionString =
                new ConnectionString("mongodb://localhost:27017/linkedspaces");

        //configure the CodecRegistry to include a codec to handle the translation to and from BSON for our POJOs.
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        //add the default codec registry, which contains all the default codecs.
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        // wrap all my settings together
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase("linkedspaces");
            MongoCollection<User> users = db.getCollection("test_users", User.class);

            User newUser = new User();

            ChattingChannels chattingChannels = new ChattingChannels();
            chattingChannels.setNextGroupChannelIndex(1);
            chattingChannels.setDm_channels(new ArrayList<DMChannel>());

            // Create a new DMChannel object
            DMChannel dmChannel1 = new DMChannel();
            dmChannel1.setId(new ObjectId());
            dmChannel1.setLastReadIndex(1);
            dmChannel1.setName("hello");
            chattingChannels.getDm_channels().add(dmChannel1);

            DMChannel dmChannel2 = new DMChannel();
            dmChannel2.setId(new ObjectId());
            dmChannel2.setLastReadIndex(2);
            dmChannel2.setName("world");
            chattingChannels.getDm_channels().add(dmChannel2);

            chattingChannels.setTenant_dashboard_level_1(new ArrayList<String>());
            chattingChannels.setLandlord_dashboard_level_1(new ArrayList<String>());

            newUser.setChatting_channels(chattingChannels);

            newUser.setLoggedInTime(new Date());
            newUser.setLastVistedListingId(new ObjectId());
            newUser.setBirthdate(new Date());

            newUser.setUsername("inseo");
            newUser.setFirstname("in");
            newUser.setLastname("seo");
            newUser.setPassword("inseo-pw");
            newUser.setEmail("in.seo@gmail.com");

            Coordinate coordinate = new Coordinate();
            coordinate.setLat(37.1234);
            coordinate.setLng(-122.5678);

            Address address = new Address();
            address.setStreet("Parkside Dr");
            address.setCity("Anytown");
            address.setCountry("USA");
            address.setZipcode(12345.0);
            address.setCoordinate(coordinate);

            newUser.setAddress(address);
            newUser.setGender("M");
            newUser.setPhone("1234");

            newUser.setDirect_friends(new ArrayList<ObjectId>());
            newUser.getDirect_friends().add(new ObjectId());
            newUser.getDirect_friends().add(new ObjectId());

            newUser.setIncoming_friends_requests(new ArrayList<ObjectId>());
            newUser.getIncoming_friends_requests().add(new ObjectId());
            newUser.getIncoming_friends_requests().add(new ObjectId());

            newUser.setOutgoing_friends_requests(new ArrayList<ObjectId>());
            newUser.getOutgoing_friends_requests().add(new ObjectId());
            newUser.getOutgoing_friends_requests().add(new ObjectId());
            newUser.getOutgoing_friends_requests().add(new ObjectId());

            newUser.setTenant_listing(new ArrayList<ObjectId>());
            newUser.getTenant_listing().add(new ObjectId());

            newUser.setLandlord_listing(new ArrayList<ObjectId>());
            newUser.getLandlord_listing().add(new ObjectId());
            newUser.getLandlord_listing().add(new ObjectId());

            newUser.set_3rdparty_listing(new ArrayList<ObjectId>());
            newUser.get_3rdparty_listing().add(new ObjectId());
            newUser.get_3rdparty_listing().add(new ObjectId());

            // _id, id가 왜 두개인가?
            ReferringFriend referringFriend = new ReferringFriend();
            referringFriend.setId(new ObjectId());
            referringFriend.setEid(new ObjectId());
            referringFriend.setUsername("namyun");
            referringFriend.setProfile_picture("path");
            referringFriend.setFriend_id(new ObjectId());

            IncomingTenantListing incomingTenantListing = new IncomingTenantListing();
            incomingTenantListing.setId(new ObjectId());
            incomingTenantListing.setStatus("new");
            incomingTenantListing.setReceived_date(new Date());
            incomingTenantListing.setList_of_referring_friends(new ArrayList<ReferringFriend>());
            incomingTenantListing.getList_of_referring_friends().add(referringFriend);

            newUser.setIncoming_tenant_listing(new ArrayList<IncomingTenantListing>());
            newUser.getIncoming_tenant_listing().add(incomingTenantListing);

            //IncomingLandlordListing
            ReferringFriend referringFriend2 = new ReferringFriend();
            referringFriend2.setId(new ObjectId());
            referringFriend2.setUsername("nykim");
            referringFriend2.setProfile_picture("files");
            referringFriend2.setFriend_id(new ObjectId());

            IncomingLandlordListing incomingLandlordListing = new IncomingLandlordListing();
            incomingLandlordListing.setId( new ObjectId() );
            incomingLandlordListing.setStatus("new");
            incomingLandlordListing.setReceicved_date(new Date());
            incomingLandlordListing.setCover_picture("cover_pic");
            incomingLandlordListing.setList_of_referring_friends(new ArrayList<ReferringFriend>());
            incomingLandlordListing.getList_of_referring_friends().add(referringFriend2);

            newUser.setIncoming_landlord_listing(new ArrayList<IncomingLandlordListing>());
            newUser.getIncoming_landlord_listing().add(incomingLandlordListing);


            newUser.setSalt("c8a0d3223f03dbd4ac6ad8d21441246076cce8986ae9e485c2c91b6a358a449d");
            newUser.setHash("94663274d9b60d942c9820b61dd72a0c9089ea2456b6af57ae49658eac314ea338131");
            newUser.setVersion(6464);
            newUser.setProfile_picture("/public/user_resources/pictures/profile_pictures/606fc");

            /*List<String> phonebook = new ArrayList<>();
            phonebook.add("+21094724348");
            phonebook.add("6785");
            newUser.setPhonebook(phonebook);*/

            List<String> labels = new ArrayList<>();
            labels.add("cafe");
            labels.add("lodging");
            labels.add("shopping");
            newUser.setLabels(labels);

            newUser.setResetPasswordExpires(new Date());
            newUser.setResetPasswordToken("8dac6bbf093fb1aeb1c9c55645e50db6d1224fb7");

            newUser.setExpoPushToken("ExponentPushToken[_exMLMLRiP1PlryY4Zw-T-]");
            newUser.setLastReportedLocation(coordinate);
            newUser.setSignupType("local");

            //incoming_events
            IncomingEvent incoming_event1 = new IncomingEvent()  ;
            incoming_event1.setId("60e0e6d13fa7e90017ac8a16");
            incoming_event1.setEid("60e0e6d13fa7e90017ac8a15");
            incoming_event1.setReceived_date(new Date());
            incoming_event1.setStatus("New");

            IncomingEvent incoming_event2 = new IncomingEvent()  ;
            incoming_event2.setId("60e518979d19a800178fb5d5");
            incoming_event2.setEid("60e518979d19a800178fb5d4");
            incoming_event2.setReceived_date(new Date());
            incoming_event2.setStatus("Old");

            List<IncomingEvent> incoming_events = new ArrayList<>();
            incoming_events.add(incoming_event1);
            incoming_events.add(incoming_event2);
            newUser.setIncoming_events(incoming_events);

            //events
            List<ObjectId> events = new ArrayList<>();
            events.add(new ObjectId());
            events.add(new ObjectId());
            newUser.setEvents(events);


            //place
            List<ObjectId> rests = new ArrayList<>();
            rests.add(new ObjectId());
            rests.add(new ObjectId());
            rests.add(new ObjectId());

            Place place = new Place();
            place.setRestaurant(rests);
            newUser.setPlaces(place);

            System.out.println("new User:\t" + newUser);

            users.insertOne(newUser);

            // find this user.
            User user = users.find(eq("email", "in.seo@gmail.com")).first();
            System.out.println("User found:\t" + user);

            // update this user: adding an phonebook
            List<String> newPhonebook = new ArrayList<>(); // user.getPhonebook());
            newPhonebook.add("1234");
            newPhonebook.add("5678");
            user.setPhonebook(newPhonebook);

            Document filterByGradeId = new Document("_id", user.getId());
            FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
            User updatedUser = users.findOneAndReplace(filterByGradeId, user, returnDocAfterReplace);
            System.out.println("User replaced:\t" + updatedUser);

        /*try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase("linkedspaces");
            MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);

            // create a new grade.
            Grade newGrade = new Grade().setStudentId(10003d)
                    .setClassId(10d)
                    .setScores(singletonList(new Score().setType("homework").setScore(50d)));
            grades.insertOne(newGrade);

            // find this grade.
            Grade grade = grades.find(eq("student_id", 10003d)).first();
            System.out.println("Grade found:\t" + grade);

            // update this grade: adding an exam grade
            List<Score> newScores = new ArrayList<>(grade.getScores());
            newScores.add(new Score().setType("exam").setScore(42d));
            grade.setScores(newScores);
            Document filterByGradeId = new Document("_id", grade.getId());
            FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
            Grade updatedGrade = grades.findOneAndReplace(filterByGradeId, grade, returnDocAfterReplace);
            System.out.println("Grade replaced:\t" + updatedGrade);
*/
            // delete this grade
            //System.out.println(grades.deleteOne(filterByGradeId));
        }
    }
}
