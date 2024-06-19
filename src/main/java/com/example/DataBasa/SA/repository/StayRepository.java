package com.example.DataBasa.SA.repository;

import com.example.DataBasa.SA.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
}

