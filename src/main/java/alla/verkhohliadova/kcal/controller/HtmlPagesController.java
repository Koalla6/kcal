package alla.verkhohliadova.kcal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlPagesController {

    @RequestMapping("kcal")
    public String kcal(){
        return "kcal.html";
    }
}
