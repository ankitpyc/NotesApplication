package com.NotesApplication.database.user;

import com.NotesApplication.database.dto.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginResponse {
    private User user;
    private String token;
}
