package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 1. @GetMapping
    // 뷰에 변수를 사용하고 컨트롤러에서 변수 할당
    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // 2. @RequestParam
    // 뷰에 변수를 사용하고 파라미터로 변수 할당
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        // RequestParam 일 때, 파라미터가 전달되지 않으면 400 Error 가 발생하므로 required 옵션을 false 로 지정해 에러처리.(이 경우, 파라미터 값은 null 할당)
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API
    // 3. @ResponseBody
    // 뷰 없이 http 에 문자를 그대로 출력
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
       return "hello " + name; // "hello spring"
    }

    // 4. 객체 파라미터
    // 파라미터로 객체가 오면 JSON 형식으로 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();  // 객체 생성
        hello.setName(name);
        return hello;               // 객체 반환
    }


    static class Hello {
        private  String name;

        // 자바빈 표준 방식 - 게터세터
        public String getName(){
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }








}
