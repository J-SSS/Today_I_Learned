package hello.hellospring.constroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") //hello 경로에 들어오면 실행되는 메서드
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; //뷰 리졸버가 resource/template 경로의 hello.html을 실행하는데, addAttribute에서 정한 속성을 전해준다
    }
}
