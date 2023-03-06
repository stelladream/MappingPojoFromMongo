package org.example.model.users;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ReferringFriend {
    private ObjectId id;
    private String profile_picture;
    private ObjectId friend_id;
    private String username;
}
