package com.rahulsmgv.weekone.repository;

import com.rahulsmgv.weekone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
