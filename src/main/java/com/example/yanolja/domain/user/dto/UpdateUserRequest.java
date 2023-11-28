package com.example.yanolja.domain.user.dto;
import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Data
public class UpdateUserRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String phonenumber;



    public User toEntity(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUsername(this.username);
        user.setPassword(passwordEncoder.encode(this.password));
        user.setPhonenumber(this.phonenumber);
        return user;
    }

}
