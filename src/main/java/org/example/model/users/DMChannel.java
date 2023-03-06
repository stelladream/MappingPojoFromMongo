package org.example.model.users;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class DMChannel {
    private ObjectId id;
    private int lastReadIndex;
    private String name;
}
