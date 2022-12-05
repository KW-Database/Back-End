package com.KWdatabase.teamProject.Model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String pw;
    private String username;
    private int age;
    private String email;
    private String phoneNumber;
    private char sex;
    private long cash;
    private String adminAuth;
}
