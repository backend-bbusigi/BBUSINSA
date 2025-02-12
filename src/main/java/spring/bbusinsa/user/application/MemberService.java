package spring.bbusinsa.user.application;

import spring.bbusinsa.user.domain.dto.request.MemberSignUpDto;
import spring.bbusinsa.user.domain.dto.response.MemberDetailDto;

public interface MemberService {
    MemberDetailDto signUp(MemberSignUpDto memberSignUpDto);
    MemberDetailDto getMemberDetail(Long memberId);
}
