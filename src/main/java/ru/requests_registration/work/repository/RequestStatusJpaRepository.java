package ru.requests_registration.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.requests_registration.work.model.RequestStatus;

@Repository
public interface RequestStatusJpaRepository extends JpaRepository<RequestStatus, Integer> {

    RequestStatus getByName(String name);
}
