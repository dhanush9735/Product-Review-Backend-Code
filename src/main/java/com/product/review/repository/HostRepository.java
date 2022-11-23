package com.product.review.repository;

import com.product.review.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostRepository extends JpaRepository<Host, Integer> {

//    public List<Host> findByRole(String role);
    public Host findByEmail(String email);

    public Host findByEmailAndPassword(String email, String password);
}
