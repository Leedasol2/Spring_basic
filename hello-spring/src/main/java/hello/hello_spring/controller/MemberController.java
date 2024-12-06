package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    /**
     * Member Service 를 new 로 생성해서 사용할 경우 다른 컨트롤러에서도 Service 를 사용해야하는데,그때마다 생성해줘야함.
     * 그렇게 하지말고 외부(Spring bean)에서 하나만 생성해서 공통으로 쓰는 것이 좋은데 이것을 의존성 주입(DI)이라고 한다.
     */
    //private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    // DI 방법 3가지
    // 1. 생성자 주입
    @Autowired
    private MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
    // 2. 필드 주입
    @Autowired
    private MemberService memberService;


    // 3. setter 주입
    private MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // GetMapping 과 같은 주소지만 Post 함수를 통해 들어온 경로만 해당된다.
    // 일반적으로 링크나 엔터로 동작할 때는 Get 함수이다
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }




}
