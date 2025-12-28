package com.rahulsmgv.weekone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // this annotation to your entity class to ignore the lazy initializer.
@Table(name = "users")  //Fix: avoid reserved word.but USER is a reserved keyword in H2 (as well as in many SQL dialects like PostgreSQL, Oracle, etc.).
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    private String address;
    private String role;
    private String contact;
}