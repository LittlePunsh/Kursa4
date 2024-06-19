package com.example.DataBasa.SA.repository;

import com.example.DataBasa.SA.model.TreatmentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentSessionRepository extends JpaRepository<TreatmentSession, Long> {
}
