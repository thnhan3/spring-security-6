package com.thnhan3.practice.payload.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
