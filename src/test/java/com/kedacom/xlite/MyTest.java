package com.kedacom.xlite;


import com.kedacom.xlite.config.MainConfig;
import com.kedacom.xlite.model.Student;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangshuxuan
 * @date 2018/11/30 13:57
 */
public class MyTest {


    @Test
    public void Test() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Student student = (Student) context.getBean("student");
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }
}
