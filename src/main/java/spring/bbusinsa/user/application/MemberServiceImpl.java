package spring.bbusinsa.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.bbusinsa.global.error.BbusinsaException;
import spring.bbusinsa.global.error.ErrorType;
import spring.bbusinsa.user.domain.dto.request.MemberSignUpDto;
import spring.bbusinsa.user.domain.dto.response.MemberDetailDto;
import spring.bbusinsa.user.domain.entity.Member;
import spring.bbusinsa.user.domain.enums.Gender;
import spring.bbusinsa.user.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public MemberDetailDto signUp(MemberSignUpDto memberSignUpDto) {
        Member member = Member.builder()
                .age(memberSignUpDto.age())
                .gender(Gender.getGender(memberSignUpDto.gender()))
                .name(memberSignUpDto.name())
                .email(memberSignUpDto.email())
                .build();

        memberRepository.save(member);

        return MemberDetailDto.of(member);
    }

    @Override
    public MemberDetailDto getMemberDetail(Long memberId) {
        Member member = findMemberById(memberId);
        return MemberDetailDto.of(member);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BbusinsaException(ErrorType.MEMBER_NOT_FOUND));
    }
}
