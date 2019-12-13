package me.pointofreview.core.objects;

import lombok.Value;

@Value
public class User {
    String name;
    int id;
    Reputation reputation;
}
