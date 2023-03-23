package com.example.mediamarkbe.dto;

import com.example.mediamarkbe.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {
    @Size(max=200)
    private String name;
    private String email;
    @NotBlank
    @Size(max=100)
    private String username;
    @NotBlank
    @Size(max=100)
    private String password;
    private List<Long> roleId;

    public static User convertToModel(UserPayload payload){
        User user = new User();

        user.setUsername(payload.getUsername());
        user.setEmail(payload.getEmail());
        user.setName(payload.getName());

        return user;
    }
}
