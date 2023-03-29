package com.chatchatabc.jpademojava.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordUpdateRequest {
    private String oldPassword;
    private String newPassword;
}
