# AOP

# AOP (**Aspect Oriented Programming)**

> **관점 지향 프로그래밍**

어떤 로직을 기준으로 ***핵심적인 관점**, ***부가적인 관점**으로 나누어서 보고 그 관점을 기준으로 각각 ** **모듈화**

ex) ***핵심적인 관점**  : 핵심 비즈니스 로직/ ***부가적인 관점** : 핵심 로직 실행을 위한 DB연결, 로깅, 파일 입출력

cf ) ** **모듈화** : 어떤 공통된 로직이나 기능을 하나의 단위로 묶는 것

> 

예시 : 실행시간 측정

## 목적


  흩어진 관심사를 aspect로 모듈화하고 핵심적인 비즈니스 로직에서 분리하여 재사용

  - ***흩어진 관심사 :** 다른 부분에 반복적으로 사용하는 코드

![image](https://github.com/sujinsu/TIL/assets/87465326/df560040-0752-49d8-893e-7bf5d3a512bb)


- `Aspect`
  - 위에서 설명한 흩어진 관심사를 모듈화 한 것. 주로 부가기능을 모듈화함.
- `Target`
  - Aspect를 적용하는 곳 (클래스, 메서드 .. )
  - 공통기능을 부여할 대상
- `Advice`
  - 실질적으로 **어떤 일을 해야할 지**에 대한 것, 실질적인 부가기능을 담은 구현체
- `JointPoint`
  - Advice가 **적용될 위치**, 끼어들 수 있는 지점. 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
- `PointCut`
  - JointPoint의 상세한 스펙을 정의한 것. **적용 대상**
  - 'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음
  - **execution** : Advice 를 적용할 메서드를 명시할 때 사용합니다.
  - **within** : 특정 타입에 속하는 메서드를 JoinPoint 로 설정되도록 명시할 때 사용합니다.
  - **bean** : 스프링 버전 2.5 버전부터 지원하기 시작, 스프링 빈을 이용하여 JoinPoint 를 설정







```
💡 execution 명시자

execution([수식어] 리턴 타입 [클래스 이름].이름(파라미터))

- 수식어 : public, private 등 수식어를 명시합니다. (생략가능)
- 리턴 타입 : 리턴 타입을 명시합니다.
- 클래스 이름 및 이름 : 클래스 이름과 메서드 이름을 명시합니다. (클래스 이름은 풀 패키지 명으로 명시해야합니다. 클래스 이름은 생략할 수도 있습니다.)
- 파라미터 : 메서드의 파라미터를 명시합니다.
- " * " : 모든 값을 표현합니다.
- " .. " : 0개 이상을 의미합니다.

ex)

execution(public Integer com.edu.aop.*.*(*))

- com.edu.aop 패키지에 속해있고, 파라미터가 1개인 모든 메서드

execution(* com.edu)..*.get*(..))

- com.edu 패키지 및 하위 패키지에 속해있고, 이름이 get 으로 시작하는 파라미터가 0개 이상인 모든 메서드

execution(* com.edu.aop..*Service.*(..))

- com.edu.aop 패키지 및 하위 패키지에 속해있고, 이름이 Service 로 끝나는 클래스의 파라미터가 0개 이상인 모든 메서드

execution(* com.edu.aop.BoardService.*(..))

- com.edu.aop.BoardService 클래스에 속한 파라미터가 0개 이상인 모든 메서드

execution(* some*(*, *))

- 메서드 이름이 some 으로 시작하고 파라미터가 2개인 모든 메서드

------

💡 within 명시자

ex)

within(com.edu.aop.SomeService)

- com.edu.aop.SomeService 클래스의 모든 메서드

within(com.edu.aop.*)

- com.edu.aop 패키지의 모든 메서드

within(com.edu.aop..*)

- com.edu.aop 패키지 및 하위 패키지의 모든 메서드

------

💡 bean 명시자

ex)

bean(someBean)

- 이름이 someBean인 빈의 모든 메서드

bean(some*)

- 빈의 이름이 some 으로 시작하는 빈의 모든 메서드
```









### 예제

```java
@Component
@Aspect
public class PerfAspect {

    @Around("execution(* me.gracenam..*.EventService.*(..))")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }
}
```







### 예제 설명

```
💡

- `@Aspect`

이 클래스가 Aspect 클래스임을 알려준다.

- `@Component`

  애노테이션 기반의 스프링 IoC를 사용하기 때문에 Component Scan을 통해서 빈 등록

두 가지 정보가 필요한데 **해야할 일**과 **어디에 적용할 것인가**이다.

해야할 일은 **Advice**, 어디에 적용할 것인가는 **Point Cut**에 해당하고 이 두 가지를 정의해야 한다.

- `ProceedingJoinPoint`,

  PJP는 Advice가 적용되는 대상이다.

  즉, Advice가 적용되는 `createEvent`, `publishEvent`와 같은 메서드 자체라고 보면 된다

  . method invocation과 비슷한 개념이다.

시간을 측정하고 걸린 시간을 출력해주는 기능을 추가하면 Advice가 완성이 된다.

완성된 Advice는 Around Advice라고 해서 `@Around` 애노테이션을 붙여준다.

그리고 value에 Point Cut 이름을 주거나 직접 정의할 수도 있다.

- `execution`

  Point Cut 표현식 표현식을 사용해서 어디에 적용할 지를 정의할 수 있다.

  `* me.gracenam..*.EventService.*(..)`는 me.gracenam 패키지 밑에 있는 모든 클래스 중 EventService에 있는 모든 메소드에 Advice를 적용하라고 정의한 것이다.

  만일 `EventService`를 `*`로 변경하면 여러 클래스에 적용할 수 있다.
```



## Aspect 실행 시점 어노테이션

```
- @Before (**이전**)
	어드바이스 타겟 메소드가 호출되기 전에 어드바이스 기능을 수행

- @After (**이후**) 
	타겟 메소드의 결과에 관계없이(즉 성공, 예외 관계없이) 
	타겟 메소드가 완료 되면 어드바이스 기능을 수행

- @AfterReturning (**정상적 반환 이후**)
	타겟 메소드가 성공적으로 결과값을 반환 후에 어드바이스 기능을 수행

- @AfterThrowing (**예외 발생 이후**) 
	타겟 메소드가 수행 중 예외를 던지게 되면 어드바이스 기능을 수행
	- ex) @AfterReturning(value = "@ ~~ ", returning = "returnValue") : 메서드 내에서 returnValue 사용 가능

- @Around (**메소드 실행 전후**) 
	어드바이스가 타겟 메소드를 감싸서 타겟 메소드 호출전과 후에 어드바이스 기능을 수행
```

## 예제

- 원하는 메서드에만 적용이 하고싶을 때

  1. execution을 사용

     1. `@Around("execution(* me.gracenam..*.EventService.*(..))")`

  2. 애노테이션으로 적용

     Aspect에서는 `execution` 대신 `@annotation`이라는 표현식으로 PerfLogging이라는 애노테이션이 달린 곳에 적용되도록 지정

```java
package me.gracenam.demospring51;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface PerfLogging {
}
@Component
@Aspect
public class PerfAspect {

    @Around("@annotation(PerfLogging)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }

}
@Service
public class SimpleEventService implements EventService {

    @PerfLogging
    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Created an event");
    }

    @PerfLogging
    @Override
    public void publishEvent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Published an event");
    }

    public void deleteEvent() {
        System.out.println("Delete an event");
    }

}
```


자료

https://yadon079.github.io/2021/spring/spring-aop-core
https://snoopy81.tistory.com/290
https://icarus8050.tistory.com/7
