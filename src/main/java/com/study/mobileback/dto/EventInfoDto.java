package com.study.mobileback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventInfoDto {

    private Long id;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String eventType;
    @Length(max = 255, message = "Описание не может превышать более 255 символов")
    private String description;
    private byte[] image;
    private String phone;
    private LocationDto location;
}
