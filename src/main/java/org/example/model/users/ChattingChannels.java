package org.example.model.users;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChattingChannels {
    private int nextGroupChannelIndex;
    private ArrayList<DMChannel> dm_channels;
    private ArrayList<String> tenant_dashboard_level_1;
    private ArrayList<String> landlord_dashboard_level_1;
}