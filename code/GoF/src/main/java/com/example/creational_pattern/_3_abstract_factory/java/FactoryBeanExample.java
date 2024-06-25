package com.example.creational_pattern._3_abstract_factory.java;

import com.example.creational_pattern._2_factory.after.Ship;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanExample {

    public static void main(String[] args) {
        // ship 타입으로 가져오지만 factory bean이 내부적으로 white ship 으로 bean을 등록
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        Ship whiteship = applicationContext.getBean("whiteShip", Ship.class);
        System.out.println(whiteship.getName());

        // factory bean이 크게 의미 없음 shipfactory or ship 가져오는 것 확인 가능
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
//        Ship bean = applicationContext.getBean(Ship.class);
//        System.out.println(bean);
    }
}
