package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // '@Component' 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
        // 권장 -> 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것
        // basePakage, basePakageClasses -> 만약 지정하지 않으면 '@Component'이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        /*basePackages = "hello.core.member", // 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지를 탐색 시작 위로 지정한다.*/
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /*@Bean(name="memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
