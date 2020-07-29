package com.sereda.sweater.repos;

import com.sereda.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
