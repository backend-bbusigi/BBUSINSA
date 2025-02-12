package spring.bbusinsa.user.domain.dto.response;

import spring.bbusinsa.user.domain.enums.Gender;

public record MemberDetailDto(Long memberId, String name, String email, int age, Gender gender) {
}
