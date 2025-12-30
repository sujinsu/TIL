import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제
 * json 문자열과 그 다음 줄 조건이 주어진다.
 * Product 는 각 필드를 가진다. title(문자열), category(문자열), price(정수), rating(실수)
 * BUT 일부 필드가 누락된 값이 들어올 수 있다.
 *
 *요구사항
 * category가 같고
 * minPrice <= price <= maxPrice 이고
 * rating >= minRating 인 상품만 대상으로 한다.
 *
 * 단, 누락값 처리 규칙:
 * title이 없으면 "UNKNOWN"으로 간주
 * price가 없으면 조건에서 탈락(무시) (즉, 필터 대상 제외)
 * rating이 없으면 0.0으로 간주 (대개 탈락)
 *
 * 예제 입력
 * {"products":[{"title":"toner","category":"BEAUTY","price":12000,"rating":4.2},{"title":"mask","category":"BEAUTY","price":5000,"rating":4.8},{"title":"snack","category":"FOOD","price":3000,"rating":4.6},{"category":"BEAUTY","price":7000,"rating":4.1},{"title":"cream","category":"BEAUTY","price":12000},{"title":"lotion","category":"BEAUTY","rating":4.9}]}
 * BEAUTY 3000 12000 4.0
 *
 * 출력
 *
 * 대상 상품들을 아래 기준으로 정렬해서 출력:
 * price 오름차순
 * 가격 같으면 rating 내림차순
 * 그래도 같으면 title 사전순
 *
 * 출력 형식(한 줄에 하나):
 * <title> <price> <rating>
 * 대상이 하나도 없으면:
 * EMPTY
 */
public class REST_filter_sort_null {
    static class Product{
        String title;
        String category;
        int price;
        double rating;
        Product(String title, String category, int price, double rating){
            this.title=title;
            this.category=category;
            this.price=price;
            this.rating=rating;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String jsonBody = br.readLine().trim();
        StringTokenizer st = new StringTokenizer(br.readLine());
        String categoryCond = st.nextToken();
        int minPrice = Integer.parseInt(st.nextToken());
        int maxPrice = Integer.parseInt(st.nextToken());
        double minRating = Double.parseDouble(st.nextToken());

        JSONObject json = new JSONObject(jsonBody);
        JSONArray jsonArray = json.getJSONArray("products");
        List<Product> products = new ArrayList<>();

        for(int i=0;i< jsonArray.length();i++){
            JSONObject item = jsonArray.getJSONObject(i);
            String category = item.optString("category", null);
            if (category == null) continue;
            if (!item.has("price") || item.isNull("price")) continue;
            int price = item.getInt("price");
            double rating = item.optDouble("rating", 0.0);
            if(price >= minPrice && price <= maxPrice && rating >= minRating){
                String title = item.optString("title", "UNKNOWN");
                products.add(new Product(title, category, price, rating));
            }
        }
        if(products.isEmpty()) System.out.print("EMPTY");
        products.sort((a,b)->{
            if(a.price != b.price) return Integer.compare(a.price, b.price);
            if(a.rating != b.rating) return Double.compare(b.rating,a.rating);
            return a.title.compareTo(b.title);
        });

        for(Product p:products){
            System.out.println(p.title+" "+p.price+" "+p.rating);
        }
    }
}
