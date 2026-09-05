package ru.privateclub.club.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.privateclub.club.dto.MemberCreateDto;
import ru.privateclub.club.dto.MemberDto;
import ru.privateclub.club.entity.Member;
import ru.privateclub.club.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto getById(Long id) {
        Member member = findMemberOrThrow(id);
        return toDto(member);
    }

    public MemberDto create(MemberCreateDto dto) {
        Member member = new Member();
        member.setFullName(dto.getFullName());
        Member saved = memberRepository.save(member);
        return toDto(saved);
    }

    public MemberDto update(Long id, MemberCreateDto dto) {
        Member member = findMemberOrThrow(id);
        member.setFullName(dto.getFullName());
        Member saved = memberRepository.save(member);
        return toDto(saved);
    }

    public void delete(Long id) {
        Member member = findMemberOrThrow(id);
        memberRepository.delete(member);
    }

    private Member findMemberOrThrow(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Участник с id=" + id + " не найден"));
    }

    private MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getFullName());
    }
}