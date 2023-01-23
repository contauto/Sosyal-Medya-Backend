package com.example.learningSpring.sos;

import com.example.learningSpring.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SosRepository extends JpaRepository<Sos, Long> {

    Page<Sos> findByUser(User user, Pageable pageable);

    Page<Sos> findByIdLessThan(long id, Pageable page);

    Page<Sos> findByIdLessThanAndUser(long id, Pageable pageable, User user);

    long countByIdGreaterThan(long id);
}
