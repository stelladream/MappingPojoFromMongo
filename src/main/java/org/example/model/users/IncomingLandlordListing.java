package org.example.model.users;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class IncomingLandlordListing {
    private ObjectId id;
    private String cover_picture;
    private String status;
    private ArrayList<ReferringFriend> list_of_referring_friends;
    private Date receicved_date;
}
