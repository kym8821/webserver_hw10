package org.webserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webserver.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
