package com.vrcs.livemenu.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResponse {
    private String JWTToken;
    private String username;
    private String firstName;
    private String role;
}
