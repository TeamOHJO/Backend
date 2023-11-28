package com.example.yanolja.domain.user.dto;
import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotNull
    private String username;

    @NotNull
    private String phonenumber;

    public User toEntity(User user) {
        user.setUsername(this.username);
        user.setPhonenumber(this.phonenumber);
        return user;
    }

}
