package com.example.FinalExamProject.Security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@Table(name = "users")
public class CustomUser {

    @Id
    private String username;
    private String password;
}
