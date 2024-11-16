package com.obify.rms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDTO {
    private Long id;
    @NotNull(message = "Rating is mandatory")
    private Float rating;
    @NotNull(message = "User is mandatory")
    private String userId;
    @NotNull(message = "Product is mandatory")
    private String productId;
    @NotNull(message = "Review is mandatory")
    private String review;
    @NotNull(message = "Organization is mandatory")
    private Long organizationId;
    @NotNull(message = "Status is mandatory")
    private String status;
    private LocalDateTime createdAt;
}
