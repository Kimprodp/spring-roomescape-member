package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.domain.member.Member;
import roomescape.exception.ClientErrorExceptionWithLog;
import roomescape.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ClientErrorExceptionWithLog(
                        "[ERROR] 존재하지 않는 사용자 입니다.",
                        "member_id : " + id
                ));
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new ClientErrorExceptionWithLog(
                        "[ERROR] 존재하지 않는 아이디(이메일) 입니다.",
                        "email : " + email
                ));
    }
}
