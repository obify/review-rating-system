package com.obify.rms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "review")
public class ReviewEntity {
    @Id
    private String id;
    private Float rating;
    private String userId;
    private String productId;
    private String review;
    private String organizationId;
    private String status;
}
