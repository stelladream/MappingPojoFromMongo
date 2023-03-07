package org.example.model.users;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

@Data
public class IncomingEvent {
        @BsonProperty(value="_id")
        private String id;
        @BsonProperty(value="id")
        private String eid;
        private String status;
        private Date received_date;
}