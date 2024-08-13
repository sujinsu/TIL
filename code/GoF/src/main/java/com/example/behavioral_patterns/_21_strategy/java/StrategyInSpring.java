package com.example.behavioral_patterns._21_strategy.java;

import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Strategy pattern은 spring에 굉장히 많음
 * 대표적으로 ApplicationContext
 */
public class StrategyInSpring {

    public static void main(String[] args) {
        // ClassPathXmlApplicationContext : classpath에서 xml 읽어서 설정파일로 찾아서 설정
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        // FileSystemXmlApplicationContext : 파일 시스템 경로 기준으로 xml 파일 찾아서 빈 설정
        ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext();
        // AnnotationConfigApplicationContext : java annotation 설정 파일 사용
        ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext();

        // 위 과정에서 빈 설정파일을 읽으면 Bean Definition 만들어야 함
        // new 하면 여러 전략 보임
        BeanDefinitionParser parser;

        // @Transactional 처리 로직 >>  jdbc, jpa, hibernate ,...
        PlatformTransactionManager platformTransactionManager;

        // cache >> ehcache, jcache, no cache,...
        CacheManager cacheManager;
    }
}
