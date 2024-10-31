package com.obify.rms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestReviewDTO {
    @NotNull(message = "Organization is mandatory")
    private Long organizationId;
    @NotNull(message = "Review is mandatory")
    private Long reviewId;
    @NotNull(message = "Status is mandatory")
    private String status;
    private int pageNo;
    private int pageSize;
}
