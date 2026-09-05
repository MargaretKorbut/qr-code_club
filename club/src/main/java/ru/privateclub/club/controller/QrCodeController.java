package ru.privateclub.club.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.privateclub.club.dto.EntryResponseDto;
import ru.privateclub.club.dto.QrCodeCreateDto;
import ru.privateclub.club.dto.QrCodeDto;
import ru.privateclub.club.service.QrCodeService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/qrcodes")
@RequiredArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @GetMapping("/{id}")
    public QrCodeDto getById(@PathVariable Long id) {
        return qrCodeService.getById(id);
    }

    @PostMapping
    public QrCodeDto create(@Valid @RequestBody QrCodeCreateDto dto) {
        return qrCodeService.create(dto);
    }

    @PutMapping("/{id}")
    public QrCodeDto update(@PathVariable Long id, @Valid @RequestBody QrCodeCreateDto dto) {
        return qrCodeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        qrCodeService.delete(id);
    }

    // Главный эндпоинт — вход по QR-коду
    @PostMapping("/enter/{code}")
    public EntryResponseDto enter(@PathVariable UUID code) {
        return qrCodeService.enter(code);
    }
}