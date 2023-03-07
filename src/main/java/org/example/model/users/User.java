package org.example.model.users;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
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


    private String salt;
    private String hash;
    @BsonProperty(value="__v")
    private int version;
    private String profile_picture;


    private List<String> phonebook;
    private List<String> labels;

    private Date resetPasswordExpires;
    private String resetPasswordToken;

    private String expoPushToken;
    private Coordinate lastReportedLocation;
    private String signupType;

    //---- next
    private List<IncomingEvent> incoming_events;
    private List<ObjectId> events;
    private Place places;

}