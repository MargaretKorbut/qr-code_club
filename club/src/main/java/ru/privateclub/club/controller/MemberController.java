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
import ru.privateclub.club.dto.MemberCreateDto;
import ru.privateclub.club.dto.MemberDto;
import ru.privateclub.club.service.MemberService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MemberDto> create(@Valid @RequestBody MemberCreateDto dto) {
        return ResponseEntity.ok(memberService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> update(@PathVariable Long id, @Valid @RequestBody MemberCreateDto dto) {
        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}