package ru.requests_registration.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.requests_registration.auth.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

    User getByLogin(String login);
}
