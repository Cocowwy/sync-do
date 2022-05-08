package cn.cocowwy.suonatestserver2.test;

import cn.cocowwy.suona.annotation.Suona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cocowwy
 * @create 2022-05-05-19:04
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    /**
     * 测试 1：
     * 标注在业务接口上 Suona注解是否生效
     */
    @GetMapping("test1")
    public void testUseSuona() {
        testService.sayHi();
    }

    /**
     * 测试 2：
     * Suona注解使用在controller层是否有效，
     * 因为标记是在controller做的
     */
    @Suona
    @GetMapping("test2")
    public void testSuonaInController() {
        System.out.println("Hello suona!");
    }

    /**
     * 测试 3：
     * Suona被标记的业务开启了新的线程
     */
    @GetMapping("test3")
    public void testThread() {
        testService.syncSayHi();
    }

    /**
     * 测试使用指定的url唤醒指定IP方法
     * 192.168.1.14:8082
     * 192.168.1.14:8081
     * 192.168.1.14:8080
     */
    @Suona(url = {"192.168.1.14:8082", "192.168.1.14:8081"})
    @GetMapping("test4")
    public void testUseUrl() {
        System.out.println("Hello Suona~~");
    }

    /**
     * 测试5：
     * 测试 Suona 名相同的情况 是否报错
     * 测试的时候需放开 testIdenticalName2
     */
    @Suona(name = "testIdenticalName")
    public void testIdenticalName1() {
    }

    //    @Suona(name = "testIdenticalName")
    public void testIdenticalName2() {
    }

    /**
     * 测试 6：
     * server:
     *   servlet:
     *     context-path: test
     * 是否兼容使用如上配置
     */
    @GetMapping("test6")
    public void testUseContextPath() {
        testService.sayHi();
    }

    /**
     * 测试 7：
     * 控制层和业务层均使用了 Suona
     */
    @Suona
    @GetMapping("test7")
    public void doubleSuona() {
        testService.sayHi();
    }
}
