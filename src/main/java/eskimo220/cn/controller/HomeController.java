package eskimo220.cn.controller;

import com.google.common.collect.Sets;
import eskimo220.cn.service.CacheTestService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private CacheManager cacheManager;

    @Autowired
    private CacheTestService cacheTestService;

    @RequestMapping(value = "/")
    public String test(Model model) {

        List<String> list = new ArrayList<>();
        list.add(cacheTestService.getCacheString("test"));
        list.add(cacheTestService.getCacheString("test1"));
        list.add(cacheTestService.getCacheString("test2"));
        list.add(cacheTestService.getCacheString("test3"));
        list.add(cacheTestService.getCacheString("test4"));
        list.add(cacheTestService.getCacheString("test5"));

        model.addAttribute("getCacheString", list);

        return "home";
    }

    @RequestMapping(value = "/get-all")
    public String test2(Model model) {

        RMap rmap = (RMap) cacheManager.getCache("getCacheString-key").getNativeCache();

        Object o = rmap.getAll(Sets.newHashSet("test1", "test3", "test5"));

        model.addAttribute("getCacheString", o);

        return "home";
    }

}
