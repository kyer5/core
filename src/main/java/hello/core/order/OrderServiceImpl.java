package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 값을 파라미터로 받는 생성자 생성
public class OrderServiceImpl implements OrderService {

    // 필드 주입 (안티 패턴 -> 외부에서 변경 불가능해서 테스트 어려움)
    /*@Autowired*/ private final MemberRepository memberRepository;
    /*@Autowired*/ private final DiscountPolicy discountPolicy;

    /*@Autowired(required = false) // 수정자 주입 (setter 주입)
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    @Autowired // 생성자 주입, 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입된다. -> @RequiredArgsConstructor가 모든 걸 해결
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
