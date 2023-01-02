package com.msn.taxi.repo;

import com.msn.taxi.entity.CookieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookiesRepo extends JpaRepository<CookieEntity, Long> {
}
