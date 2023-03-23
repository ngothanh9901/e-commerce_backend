package com.example.mediamarkbe.model;

import com.example.mediamarkbe.model.support.UserDateAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=200)
    private String name;
    private String email;
    @NotBlank
    @Size(max=100)
    private String username;
    @NotBlank
    @Size(max=100)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @Column(name = "login_times")
    private Integer loginTimes = 0;

    private int loginFailedTimes = 0;
}
