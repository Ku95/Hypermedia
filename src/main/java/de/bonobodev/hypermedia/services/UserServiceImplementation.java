package de.bonobodev.hypermedia.services;

import de.bonobodev.hypermedia.dtos.User;
import de.bonobodev.hypermedia.exceptions.InvalidLoginException;
import de.bonobodev.hypermedia.exceptions.NotFoundException;
import de.bonobodev.hypermedia.exceptions.UsernameAlreadyExistsException;
import de.bonobodev.hypermedia.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;

    /**
     * Generates a new random token for the specified user.
     *
     * @param user the user, who's token gets replaced
     * @return the updated user
     */
    private User updateToken(User user) {
        user.setToken(UUID.randomUUID().toString());
        return user;
    }

    @Override
    public User authenticate(String token) throws NotFoundException {
        return repository.findFirstByToken(token).orElseThrow(NotFoundException::new);
    }

    @Override
    public User register(String username, String password) throws UsernameAlreadyExistsException {
        boolean taken = false;
        try {
            repository.findFirstByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user with username=" + username));
            taken = true;
        } catch (UsernameNotFoundException ignored) {
        }

        if (taken) {
            throw new UsernameAlreadyExistsException();
        }

        return repository.save(updateToken(new User(username, password)));
    }

    @Override
    public User login(String username, String password) throws UsernameNotFoundException, InvalidLoginException {
        User user = repository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with username=" + username));

        if (user.getPassword().equals(password)) {
            return repository.save(updateToken(user));
        }

        throw new InvalidLoginException();
    }

    @Override
    public void logout(User user) {
        repository.save(updateToken(user));
    }
}