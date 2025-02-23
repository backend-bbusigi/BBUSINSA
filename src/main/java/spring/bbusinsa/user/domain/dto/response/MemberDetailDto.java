package spring.bbusinsa.user.domain.dto.response;

import spring.bbusinsa.user.domain.entity.Member;
import spring.bbusinsa.user.domain.enums.Gender;

public record MemberDetailDto(Long memberId,
                              String name,
                              String email,
                              int age,
                              Gender gender) {

    public static MemberDetailDto of(Member member) {
        return new MemberDetailDto(
                member.getMemberId(),
                member.getName(),
                member.getEmail(),
                member.getAge(),
                member.getGender()
        );
    }
}
