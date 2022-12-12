package org.onlydevs.persistence;

import org.onlydevs.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByEmail (String email);

    Long save(User user);

}
