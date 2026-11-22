package com.stygian.jobtracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Entity // "database table from this class"
@Data // "getters and setters"
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //databse auto-numbers the id
    private Long id;

    @NotBlank(message = "Company is required")
    @Size(max = 100, message = "Company name must be at most 100 characters")
    private String company;
    @Size(max = 100, message = "Position name must be at most 100 characters")
    private String position;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "(APPLIED|INTERVIEW|OFFER|REJECTED)", message = "Status must be APPLIED, INTERVIEW, OFFER, or REJECTED")
    private String status; // "applied", "interviewing", "offer", "rejected"

    @NotNull (message = "Applied date is required")
    @PastOrPresent (message = "Applied date cannot be in the future")
    private LocalDate appliedDate;
}
