package com.stygian.jobtracker;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity // "database table from this class"
@Data // "getters and setters"
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //databse auto-numbers the id
    private Long id;

    private String company;
    private String position;
    private String status; // "applied", "interviewing", "offer", "rejected"
    private LocalDate appliedDate;
}
