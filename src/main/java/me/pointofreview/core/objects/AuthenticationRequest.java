package me.pointofreview.core.objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationRequest {
    public String username;
    public String password;
}