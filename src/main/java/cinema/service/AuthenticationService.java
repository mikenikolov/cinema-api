package cinema.service;

import cinema.exception.AuthenticationException;
import cinema.model.User;

public interface AuthenticationService {
    User register(String email, String password);

    User login(String email, String password) throws AuthenticationException;
}
