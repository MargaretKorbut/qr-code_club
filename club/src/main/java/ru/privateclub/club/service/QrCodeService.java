package ru.privateclub.club.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.privateclub.club.dto.EntryResponseDto;
import ru.privateclub.club.dto.QrCodeCreateDto;
import ru.privateclub.club.dto.QrCodeDto;
import ru.privateclub.club.entity.Member;
import ru.privateclub.club.entity.QrCode;
import ru.privateclub.club.repository.MemberRepository;
import ru.privateclub.club.repository.QrCodeRepository;
import ru.privateclub.club.exception.EntityNotFoundException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrCodeService {

    private final QrCodeRepository qrCodeRepository;
    private final MemberRepository memberRepository;

    public QrCodeDto getById(Long id) {
        QrCode qrCode = findQrCodeOrThrow(id);
        return toDto(qrCode);
    }

    public QrCodeDto create(QrCodeCreateDto dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Участник с id=" + dto.getMemberId() + " не найден"));

        QrCode qrCode = new QrCode();
        qrCode.setCode(UUID.randomUUID());
        qrCode.setActive(true);
        qrCode.setMember(member);

        QrCode saved = qrCodeRepository.save(qrCode);
        return toDto(saved);
    }

    public QrCodeDto update(Long id, QrCodeCreateDto dto) {
        QrCode qrCode = findQrCodeOrThrow(id);
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Участник с id=" + dto.getMemberId() + " не найден"));
        qrCode.setMember(member);
        QrCode saved = qrCodeRepository.save(qrCode);
        return toDto(saved);
    }

    public void delete(Long id) {
        QrCode qrCode = findQrCodeOrThrow(id);
        qrCodeRepository.delete(qrCode);
    }

    // Главная логика: вход по QR-коду
    public EntryResponseDto enter(UUID code) {
        QrCode qrCode = qrCodeRepository.findByCodeAndActiveTrue(code)
                .orElseThrow(() -> new EntityNotFoundException("QR-код не найден или уже использован"));

        // Старый код деактивируем
        qrCode.setActive(false);
        qrCodeRepository.save(qrCode);

        // Создаём новый код для того же участника
        Member member = qrCode.getMember();
        QrCode newQrCode = new QrCode();
        newQrCode.setCode(UUID.randomUUID());
        newQrCode.setActive(true);
        newQrCode.setMember(member);
        qrCodeRepository.save(newQrCode);

        return new EntryResponseDto(member.getFullName(), newQrCode.getCode());
    }

    private QrCode findQrCodeOrThrow(Long id) {
        return qrCodeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("QR-код с id=" + id + " не найден"));
    }

    private QrCodeDto toDto(QrCode qrCode) {
        return new QrCodeDto(
                qrCode.getId(),
                qrCode.getCode(),
                qrCode.isActive(),
                qrCode.getMember().getId()
        );
    }
}
