package org.example.model.users;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Data
public class ReferringFriend {
    @BsonProperty(value="_id")
    private ObjectId id;
    @BsonProperty(value="id")
    private ObjectId eid;
    private String profile_picture;
    private ObjectId friend_id;
    private String username;
}
