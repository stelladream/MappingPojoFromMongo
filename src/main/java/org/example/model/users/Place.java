package org.example.model.users;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Place {
        private List<ObjectId> restaurant;
}
