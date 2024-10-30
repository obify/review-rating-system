package com.obify.rms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDTO {
    private Long id;
    @NotNull(message = "Rating is mandatory")
    @NotEmpty(message = "Rating cannot be empty")
    private Float rating;
    @NotNull(message = "User is mandatory")
    @NotEmpty(message = "User cannot be empty")
    private String userId;
    @NotNull(message = "Product is mandatory")
    @NotEmpty(message = "Product cannot be empty")
    private String productId;
    @NotNull(message = "Review is mandatory")
    @NotEmpty(message = "Review cannot be empty")
    private String review;
    @NotNull(message = "Organization is mandatory")
    @NotEmpty(message = "Organization cannot be empty")
    private Long organizationId;
    @NotNull(message = "Status is mandatory")
    @NotEmpty(message = "Status cannot be empty")
    private String status;
    private LocalDate createdAt;
}
