package ru.requests_registration.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.requests_registration.auth.model.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Integer> {

    Role getByName(String name);
}
