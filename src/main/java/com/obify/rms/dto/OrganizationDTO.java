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
public class OrganizationDTO {
    private Long id;
    @NotNull(message = "Organization name is mandatory")
    @NotEmpty(message = "Organization name cannot be empty")
    private String name;
    @NotNull(message = "Organization email is mandatory")
    @NotEmpty(message = "Organization email cannot be empty")
    private String email;
    @NotNull(message = "Organization phone is mandatory")
    @NotEmpty(message = "Organization phone cannot be empty")
    private String phone;
    private LocalDate createdAt;
}
