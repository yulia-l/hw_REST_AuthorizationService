package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        if ("admin".equals(user) && "qwerty".equals(password)) {
            return List.of(Authorities.values());
        } else if ("guest".equals(user)) {
            return List.of(Authorities.READ);
        } else {
            return Collections.emptyList();
        }
    }
}
