package org.example.model.users;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
public class IncomingTenantListing {
    private ObjectId id;
    private String status;
    private List<ReferringFriend> list_of_referring_friends;
    private Date received_date;
}
