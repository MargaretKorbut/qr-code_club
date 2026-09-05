package ru.privateclub.club.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<QrCodeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(qrCodeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<QrCodeDto> create(@Valid @RequestBody QrCodeCreateDto dto) {
        return ResponseEntity.ok(qrCodeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QrCodeDto> update(@PathVariable Long id, @Valid @RequestBody QrCodeCreateDto dto) {
        return ResponseEntity.ok(qrCodeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        qrCodeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/enter/{code}")
    public ResponseEntity<EntryResponseDto> enter(@PathVariable UUID code) {
        return ResponseEntity.ok(qrCodeService.enter(code));
    }
}