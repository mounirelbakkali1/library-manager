package com.hcl.liberaryManager.repository;

import com.hcl.liberaryManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
   boolean existsByName(String name);
   boolean existsByUsername(String username);
}
