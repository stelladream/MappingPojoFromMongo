package org.example.model.users;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private ObjectId id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;

    private Address address;
    private String gender;
    private String phone;

    private Date loggedInTime;
    private ObjectId lastVistedListingId;
    private Date birthdate;

    private ChattingChannels chatting_channels;
    private List<ObjectId> direct_friends;
    private List<ObjectId> incoming_friends_requests;
    private List<ObjectId> outgoing_friends_requests;

    private List<ObjectId> tenant_listing;
    private List<ObjectId> landlord_listing;
    private List<ObjectId> _3rdparty_listing;


    private List<IncomingTenantListing> incoming_tenant_listing;
    private List<IncomingLandlordListing> incoming_landlord_listing;

    //---- next
    private String salt;
    private String hash;
    private int __v;
    private String profile_picture;

    private String expoPushToken;
    private List<String> events;
    private Places places;
    private List<IncomingEvent> incoming_events;
    private List<String> phonebook;
    private String signupType;
    private ResetPassword resetPasswordExpires;
    private String resetPasswordToken;
    private LastReportedLocation lastReportedLocation;
    private List<String> labels;



    private static class Places {
        private List<String> restaurant;
    }

    private static class IncomingEvent {
        private String status;
        private String _id;
        private String id;
        private ReceivedDate received_date;

        private static class ReceivedDate {
            private String $date;

        }
    }

    private static class ResetPassword {
        private String $date;

    }

    private static class LastReportedLocation {
        private double lat;
        private double lng;

    }
}