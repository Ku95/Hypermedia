package de.bonobodev.hypermedia.services;

import de.bonobodev.hypermedia.dtos.User;
import de.bonobodev.hypermedia.exceptions.InvalidLoginException;
import de.bonobodev.hypermedia.exceptions.NotFoundException;
import de.bonobodev.hypermedia.exceptions.UsernameAlreadyExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    User authenticate(String token) throws NotFoundException;

    User register(String username, String password) throws UsernameAlreadyExistsException;

    User login(String username, String password) throws UsernameNotFoundException, InvalidLoginException;

    void logout(User user);
}
