import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class REST_filter {
    /**
     * 문제
     * 입력으로 JSON 문자열 1개와, 그 다음 줄에 category maxPrice가 주어진다.
     *
     * JSON 구조:
     * 최상위에 products 배열이 있다.
     * 각 product는 title(문자열), category(문자열), price(정수)를 가진다.
     *
     * 조건:
     * category가 입력과 같고
     * price <= maxPrice 인 상품들만 골라서
     *
     * 정렬:
     * price 오름차순
     * 가격 같으면 title 사전순
     *
     * 출력:
     * 조건 만족 상품의 title을 한 줄씩 출력
     * 없으면 EMPTY 출력
     *
     * 입력
     * 1줄: JSON 문자열
     * 2줄: category maxPrice
     *
     * 예제 입력
     * {"products":[{"title":"toner","category":"BEAUTY","price":12000},{"title":"mask","category":"BEAUTY","price":5000},{"title":"snack","category":"FOOD","price":3000},{"title":"cream","category":"BEAUTY","price":12000}]}
     * BEAUTY 12000
     *
     * 예제 출력
     * mask
     * cream
     * toner
     *
     * (12000 두 개는 title 사전순: cream → toner)
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String body = br.readLine().trim();
        String[] condition = br.readLine().trim().split(" ");
        /**
         * 아래 방식 (한 줄에 2~5개 토큰 정도 있고, 공백이 불규칙할 수 있으면)
         * StringTokenizer st = new StringTokenizer(br.readLine());
         * String filterCategory = st.nextToken();
         * int maxPrice = Integer.parseInt(st.nextToken());
         */

        JSONObject jsonObject = new JSONObject(body);
        JSONArray jsonArr = jsonObject.getJSONArray("products");
        List<Product> products = new ArrayList<>();

        for(int i=0;i< jsonArr.length();i++){
            JSONObject item = jsonArr.getJSONObject(i);
            String title = item.getString("title");
            String category = item.getString("category");
            int price = item.getInt("price");
            if(category.equals(condition[0]) && price <= Integer.parseInt(condition[1])){
                products.add(new Product(title, category, price));
            }

        }
        products.sort((a,b)->{
            if(a.price == b.price) return a.title.compareTo(b.title);
            return Integer.compare(a.price,b.price);
        });

        for(Product p:products){
            System.out.println(p.title );
        }
        if (products.isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
    }

    static class Product{
        String title;
        String category;
        int price;

        Product(String title, String category, int price){
            this.title=  title;
            this.category = category;
            this.price = price;
        }
    }
}
