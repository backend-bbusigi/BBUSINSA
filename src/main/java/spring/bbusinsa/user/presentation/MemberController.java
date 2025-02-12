package spring.bbusinsa.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.bbusinsa.global.response.ApiResponse;
import spring.bbusinsa.user.application.MemberService;
import spring.bbusinsa.user.domain.dto.request.MemberSignUpDto;
import spring.bbusinsa.user.domain.dto.response.MemberDetailDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberDetailDto> signUp(
            @RequestBody MemberSignUpDto memberSignUpDto
    ) {
        return ApiResponse.success(memberService.signUp(memberSignUpDto));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<MemberDetailDto> getMemberDetail(
            @PathVariable(name = "memberId") Long memberId
    ) {
        return ApiResponse.success(memberService.getMemberDetail(memberId));
    }

}
