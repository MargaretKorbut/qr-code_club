package ru.privateclub.club.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateDto {

    @NotBlank(message = "ФИО не может быть пустым")
    private String fullName;
}
