package com.collegeapp.management.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor implements Persistable<String> {

    @Id
    @Column(name = "professor_id", columnDefinition = "CHAR(6)")
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dni", nullable = false, columnDefinition = "CHAR(8)")
    private String dni;

    @Column(name = "email", nullable = false)
    private String email;

    private String telephone;
    private String address;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Transient
    private Integer age;

    @Transient
    private boolean isNew = true;

    @OneToMany(mappedBy = "professor", cascade = {CascadeType.ALL})
    private List<Classroom> classrooms;

    public Professor() {
    }

    public Professor(String id,
                     String fullName,
                     String dni,
                     String email,
                     String telephone,
                     String address,
                     LocalDate dob) {
        this.id = id;
        this.fullName = fullName;
        this.dni = dni;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.dob = dob;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
