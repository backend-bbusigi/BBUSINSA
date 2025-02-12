package spring.bbusinsa.user.domain.dto.response;

import lombok.Builder;
import spring.bbusinsa.user.domain.entity.Member;
import spring.bbusinsa.user.domain.enums.Gender;

@Builder
public record MemberDetailDto(Long memberId, String name, String email, int age, Gender gender) {

    public static MemberDetailDto of(Member member) {
        return MemberDetailDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .gender(member.getGender())
                .build();
    }
}
