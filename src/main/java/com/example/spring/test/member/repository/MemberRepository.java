package com.example.spring.test.member.repository;

import com.example.spring.test.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
