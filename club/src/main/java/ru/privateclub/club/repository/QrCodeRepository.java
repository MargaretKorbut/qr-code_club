package ru.privateclub.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.privateclub.club.entity.QrCode;

import java.util.Optional;
import java.util.UUID;

public interface QrCodeRepository extends JpaRepository<QrCode, Long> {

    Optional<QrCode> findByCodeAndActiveTrue(UUID code);
}
