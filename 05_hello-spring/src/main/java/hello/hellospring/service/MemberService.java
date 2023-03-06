package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemoryMemberRepository;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }




    /*
    회원가입
     */
    public long join(Member member) {
        //같은 이름이 있으면 안됨;
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//    });
        
        //이미 optional타입이기때문에 바로 ifPresent 사용가능
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    전체 회원 조회
     */
    public List<Member> findMember(){
      return memberRepository.findAll();
    };

    public Optional<Member> findeOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
