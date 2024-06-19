package com.example.DataBasa.SA.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TreatmentSession> treatmentSessions;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TreatmentSession> getTreatmentSessions() {
        return treatmentSessions;
    }

    public void setTreatmentSessions(Set<TreatmentSession> treatmentSessions) {
        this.treatmentSessions = treatmentSessions;
    }

    public Long getDescription() {
        return null;
    }

    public void setDescription(Long description) {
    }
}


