package org.onlydevs.business;

import org.onlydevs.domain.User;


public interface CreateAccountUseCase {
    Long register(User user);
}
