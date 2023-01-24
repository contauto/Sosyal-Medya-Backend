package com.example.learningSpring.sos;

import com.example.learningSpring.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SosRepository extends JpaRepository<Sos, Long>, JpaSpecificationExecutor<Sos> {

    Page<Sos> findByUser(User user, Pageable pageable);
}
