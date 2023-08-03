package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }


    // MVC 방식 : ViewResolver를 통해서 웹 띄우기(렌더링이 된 html 전달)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    // API 방식 1 : ViewResolver 안통하고 HttpMessageConverter가 동작
    // API 방식 중 기본 문자처리 StringConverter
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }


    // API 방식 2 : responsebody로 객체 넘기는 것 (HttpMessageConverter 동작) -> 요즘은 json이 디폴트
    // API 방식 중 기본 객체처리  MappingJackson2HttpMessageConverter
    // 객체를 json으로 바꿔주는 라이브러리 (Jackson - 스프링 디폴트, Gson)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
