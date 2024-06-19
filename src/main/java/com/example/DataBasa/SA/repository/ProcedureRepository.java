package com.example.DataBasa.SA.repository;

import com.example.DataBasa.SA.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
}

