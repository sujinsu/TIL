![image](https://github.com/sujinsu/TIL/assets/87465326/e8ae8233-eb7c-43ac-80d4-9c28662d5ffa)# JVM & GC

![image](https://github.com/sujinsu/TIL/assets/87465326/5678eaf6-79eb-4315-9859-b770d93eaaba)






## JVM

: Java 바이트 코드를 실행할 수 있게 해주는 가상의 컴퓨터 엔진

1. **플랫폼 독립성** : Java 코드는 한 번 컴파일되면 (**`.java`** 소스 파일에서 **`.class`** 바이트코드 파일로), 다양한 플랫폼에서 실행될 수 있습니다. 이는 각 플랫폼마다 JVM이 있기 때문입니다.
2. **실행 엔진** : JVM 내의 핵심 부분으로 Java 바이트 코드를 실행합니다. 실행 엔진은 바이트 코드를 기계어로 해석하거나 JIT (Just-In-Time) 컴파일러를 통해 런타임에 기계어로 번역하여 실행합니다.
3. **메모리 영역** : JVM은 메모리를 몇 개의 영역으로 나눠 관리합니다. ex) 메소드 영역, 힙 영역, 스택 영역, PC 레지스터, 네이티브 메소드 스택 등이 있습니다.
4. **Java API와 Native Interface** : Java 애플리케이션은 Java API를 통해 표준 라이브러리를 사용할 수 있습니다. 또한, JVM은 Java Native Interface (JNI)를 제공하여 네이티브 코드와 상호 작용할 수 있게 해줍니다.
5. **최적화와 보안** : JVM은 가비지 컬렉션과 같은 메모리 관리 기능을 제공하며, 바이트 코드 검증기를 통해 안전하지 않은 코드의 실행을 방지하여 보안을 강화합니다.
6. **호환성** : 새로운 버전의 JVM도 이전 버전의 바이트 코드를 실행할 수 있어야 합니다. 이를 통해 Java는 플랫폼 간 및 버전 간의 호환성을 보장합니다.

JVM은 Java의 "Write Once, Run Anywhere"라는 철학을 실현하는 핵심 요소입니다. JVM 덕분에 Java 애플리케이션은 다양한 플랫폼에서 똑같이 실행될 수 있습니다.







## **JVM 메모리 영역:**

- Runtime Data Area

  ![image](https://github.com/sujinsu/TIL/assets/87465326/3208bb63-236f-4a26-ba24-55503a0de6fd)

  Runtime Data Area : 프로그램을 수행하기 위해 OS에서 할당받은 메모리 공간

1. **PC Registeer :** Thread 시작될 때 생성되며, 생성될 때마다 생성되는 공간. 스레드마다 하나씩 존재.

- 현재 수행 중인 JVM 명령의 주소를 가짐

2. **JVM** **Stack** : 스레드마다 하나씩 존재하며, 메서드 호출 정보, 지역 변수 등이 저장됩니다.
3. **Native method stack :** 바이트 코드가 아닌 실제 실행할 수 있는 기계어로 작성된 프로그램 실행

- java가 아닌 다른 언어로 작성된 코드를 위한 공간

4. **Heap Memory** : 동적으로 할당된 객체가 저장되는 영역입니다. 크게 Young Generation과 Old Generation으로 나뉩니다.

   - heap
     ![image](https://github.com/sujinsu/TIL/assets/87465326/c7c83c56-bfb7-470e-b7f7-01d22c9de682)
     ![image](https://github.com/sujinsu/TIL/assets/87465326/e037201f-5ad6-45d0-bab1-7bbb280d2906)

   - New/**Young Generation** : 새로 생성된 객체들이 위치하는 영역.

   - Eden 영역(최초 생성) / 두 개의 Survivor 영역(S0, S1 : 저장 공간) 으로 나뉩니다.

   - Minor GC

   - **Old Generation (Tenured Generation)** : Young Generation에서 오랫동안 살아남은 객체들이 이동하는 영역.

   - Major GC (Minor GC 보다 느리다)

   ```markdown
   Minor GC
   1. 자바 객체 생성 시 처음 Eden영역 저장
   2. Minor GC 발생할 때, Survivor 이동 & Eden 과 Survivor1 에 활성객체를 Survivor2에 복사
   3. 비활성 객체는 Survivor1에 있음 ->  Eden과 Survivor1 클리어
   4. 1~3번 반복하며 오래된 객체는 Old 영역으로 이동
   
   *속도가 빠르고, 작은 크기 콜렉팅, Stop the world 방식
   ```

   ```markdown
   Major GC
   1. GC Root 로부터 모든 객체 참조 확인, 연결되지 않은 객체 Mark
   2. 1번의 작업이 끝나면 사용되지 않은 객체 표시 & Sweep
   ```

   ```markdown
   Full GC
   - Heap 메모리 전체 영역 발생
   - Old, Young 영역 모두에서 발생하는 GC
   - Minor GC, Major GC 모두 실패 or Young 영역, Old 영역 가득 찼을 때 발생
   - 일어나는 도중에 순간적으로 자바 어플리케이션 중지 -> 성능과 안정성 영향
   ```

5. **Metaspace (Java 8 이후)** : 클래스 메타데이터와 메소드 데이터 등이 저장되는 영역. Java 7까지는 PermGen (Permanent Generation)이라는 이름으로 존재했습니다.

6. **Code Cache** : JIT 컴파일러에 의해 컴파일된 코드가 저장되는 영역입니다.





## GC

: GC(Garbage Collection)는 메모리 관리 기법 중 하나

: 주로 객체 지향 프로그래밍 언어에서 동적으로 할당된 메모리 영역 중 더 이상 사용되지 않는 영역을 자동으로 회수하는 과정

- Java, .NET, Python과 같은 많은 현대 프로그래밍 언어들은 GC를 기본적으로 지원

**GC의 주요 목표**

1. 프로그램이 더 이상 참조하지 않는 메모리를 자동으로 회수하여 재사용할 수 있게 함으로써 메모리 누수 방지
2. 개발자에게 메모리 관리의 부담을 덜어주어 개발 생산성 향상





## **GC의 작동 방식:**

1. **Mark** : GC는 먼저 더 이상 사용되지 않는 객체를 식별하기 위해 "mark" 단계를 수행합니다. 이 단계에서는 루트 객체(root objects)에서 시작하여 접근 가능한 모든 객체를 추적하고, 이러한 객체를 "살아있는(live)" 객체로 표시합니다.
2. **Sweep** : Mark 단계에서 표시되지 않은 객체, 즉 살아있지 않은 객체는 메모리에서 제거되는 "sweep" 단계를 거칩니다.
3. **Compact** : 일부 GC는 메모리의 조각화를 줄이기 위해 "compact" 단계도 수행합니다. 이 단계에서는 살아있는 객체를 메모리의 한쪽 끝으로 이동시켜, 연속된 블록의 메모리를 확보합니다.





## **주요 GC 알고리즘:**

1. **Serial GC** : 단일 스레드로 GC 작업을 수행하는 알고리즘입니다. 주로 단일 스레드 환경의 간단한 어플리케이션에 사용됩니다.
2. **Parallel GC (Throughput GC)** : Java8의 default GC 여러 스레드를 사용하여 GC 작업을 동시에 수행하는 알고리즘입니다. 멀티 스레드 환경의 서버 어플리케이션에 적합합니다.
3. **CMS GC (Concurrent Mark-Sweep)** : Old Generation에 대해 병렬로 GC 작업을 수행하는 알고리즘입니다. 애플리케이션의 응답 시간이 중요할 때 사용됩니다.
4. **G1 GC (Garbage-First)** : 메모리를 여러 영역으로 나누어 각 영역별로 GC를 수행하는 알고리즘입니다. 예측 가능한 퍼즈(pause) 시간을 제공하며, 대용량 힙 메모리를 가진 시스템에 적합합니다.

이러한 GC 알고리즘들은 JVM의 시작 시점에 JVM 옵션을 통해 선택할 수 있으며, 각 알고리즘마다 성능과 반응성에 영향을 미치는 특징들이 있습니다. 따라서 사용 환경과 요구사항에 따라 적절한 GC 알고리즘을 선택하는 것이 중요합니다.





## **GC의 장점과 단점:**

**장점**:

- 개발자의 메모리 관리 부담을 줄여줍니다.
- 메모리 누수와 관련된 버그를 자동으로 방지해줍니다.

**단점**:

- 런타임 중에 GC가 실행되면 애플리케이션의 성능에 영향을 줄 수 있습니다.
- GC 알고리즘의 선택과 설정에 따라 애플리케이션의 성능과 반응성에 큰 차이가 발생할 수 있습니다.

요약하면, GC는 자동 메모리 관리의 일부로, 프로그래밍의 복잡성을 줄이고 메모리 누수를 방지하는 데 중요한 역할을 합니다. 그러나 GC가 언제 발생하고 어떻게 작동하는지 이해하는 것은 시스템의 성능을 최적화하기 위해 필요합니다.
