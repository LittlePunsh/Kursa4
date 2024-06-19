package com.example.DataBasa.SA.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stay> stays;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicalExamination> medicalExaminations;

    // Constructors, getters, setters

    public Guest() {
        // Default constructor
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Stay> getStays() {
        return stays;
    }

    public void setStays(Set<Stay> stays) {
        this.stays = stays;
    }

    public Set<MedicalExamination> getMedicalExaminations() {
        return medicalExaminations;
    }

    public void setMedicalExaminations(Set<MedicalExamination> medicalExaminations) {
        this.medicalExaminations = medicalExaminations;
    }
}
