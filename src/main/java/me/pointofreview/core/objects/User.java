package me.pointofreview.core.objects;

import lombok.NonNull;
import lombok.Value;

@Value
@NonNull
public class User {
    String name;
    String id;
    Reputation reputation;
}
