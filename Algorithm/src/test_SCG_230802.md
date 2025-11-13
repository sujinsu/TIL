

```
입력예제1
Spring-Cloud-Gateway
출력예제1
SCG
```


```java

import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String data = br.readLine();

        String answer = Character.toString(data.charAt(0));

        int idx = data.indexOf('-');
        while(idx != -1){
            answer += Character.toString(data.charAt(idx + 1));
            idx = data.indexOf('-', idx + 1);
        }

        System.out.println(answer);
    }
}
```