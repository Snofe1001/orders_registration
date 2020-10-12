package ru.requests_registration.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.requests_registration.auth.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

    User getById(Integer id);

    User getByLogin(String login);

    @Modifying
    @Query(value = "INSERT INTO auth.user_role (user_id, role_id)" +
            " VALUES (:userId, (SELECT id FROM auth.roles WHERE name = 'ROLE_OPERATOR'))", nativeQuery = true)
    void setOperatorRoleToUserByUserId(@Param("userId") Integer userId);
}
