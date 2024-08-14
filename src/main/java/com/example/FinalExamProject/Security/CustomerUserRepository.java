package com.example.FinalExamProject.Security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerUserRepository extends JpaRepository<CustomUser,String> {
}
