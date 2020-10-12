package ru.requests_registration.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.requests_registration.work.model.Request;

import java.util.List;

@Repository
public interface RequestJpaRepository extends JpaRepository<Request, Integer> {

    List<Request> getByCreatedById(Integer userId);

    Request getById(Integer id);

    @Query(value = "SELECT * FROM work.requests wr " +
            "INNER JOIN work.request_statuses wrs ON wrs.id = wr.request_status_id " +
            "WHERE wrs.name = 'POSTED' " +
            "ORDER BY create_date ", nativeQuery = true)
    List<Request> getAllPostedRequests();

    @Modifying
    @Query(value = "UPDATE work.requests SET request_status_id = " +
            "(SELECT id FROM work.request_statuses WHERE name = 'POSTED')," +
            "update_date = CURRENT_DATE " +
            "WHERE id =:id ", nativeQuery = true)
    void postRequestById(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE work.requests SET request_status_id = " +
            "(SELECT id FROM work.request_statuses WHERE name = 'APPROVED'), " +
            "update_date = CURRENT_DATE " +
            "WHERE id =:id ", nativeQuery = true)
    void approveRequestById(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE work.requests SET request_status_id = " +
            "(SELECT id FROM work.request_statuses WHERE name = 'REFUSE'), " +
            "update_date = CURRENT_DATE " +
            "WHERE id =:id ", nativeQuery = true)
    void refuseRequestById(@Param("id") Integer id);

}
