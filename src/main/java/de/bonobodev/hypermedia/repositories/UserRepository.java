package de.bonobodev.hypermedia.repositories;

import de.bonobodev.hypermedia.dtos.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsername(String username);

    Optional<User> findFirstByToken(String token);
}
