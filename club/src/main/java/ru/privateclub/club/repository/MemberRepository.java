package ru.privateclub.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.privateclub.club.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
