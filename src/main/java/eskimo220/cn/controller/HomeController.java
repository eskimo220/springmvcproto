package eskimo220.cn.controller;

import eskimo220.cn.service.CacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Conditional(HomeController.ShouldSkip.class)
public class HomeController {

    public static class ShouldSkip implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return true;
        }
    }

    @Autowired
    private CacheTestService cacheTestService;

    @RequestMapping(value = "/")
    public String test(Model model) {

        model.addAttribute("getCacheString", cacheTestService.getCacheString());

        return "home";
    }
}
