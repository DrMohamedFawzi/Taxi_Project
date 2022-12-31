package com.msn.taxi.repo;

import com.msn.taxi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<User, String> {
}
