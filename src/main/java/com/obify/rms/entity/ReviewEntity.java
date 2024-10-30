package com.obify.rms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@Setter
@Getter
@NoArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float rating;
    private String userId;
    private String productId;
    private String review;
    private Long organizationId;
    private String status;
    private LocalDate createdAt;
}
