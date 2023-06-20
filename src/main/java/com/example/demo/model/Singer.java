package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "singer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotBlank
    private String avatar = "https://firebasestorage.googleapis.com/v0/b/vuongcuti-6ce58.appspot.com/o/images.png?alt=media&token=568b3bcc-c852-4669-9cd3-d4684ce4ad70&_gl=1*1jze9dn*_ga*NTk4MDQwMzA2LjE2ODM3NjczNjA.*_ga_CW55HF8NVT*MTY4NjYzMTIzOS4xNC4xLjE2ODY2MzEzMjMuMC4wLjA.";
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private LocalDate birthDay;
    @ManyToOne
    private User user;
}
