package com.market.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(final Long id);

    Optional<Member> findByNickname(final String nickname);

    Optional<Member> findByEmail(final String email);

    Member save(final Member member);
}
