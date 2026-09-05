package ru.privateclub.club.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeCreateDto {

    @NotNull(message = "ID участника обязателен")
    private Long memberId;
}