import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 정렬 + Top K개 출력
 *
 * 입력
 * category minRating limit K
 * 예:
 * beauty 4.0 30 3
 *
 * 조건
 * category가 같고
 * rating >= minRating
 *
 * 출력
 * 조건을 만족하는 상품을 아래 기준으로 정렬한 뒤 상위 K개를 출력한다.
 *
 * 정렬 기준:
 * rating 내림차순
 * rating 같으면 price 내림차순
 * 그래도 같으면 title 오름차순(사전순)
 *
 * 출력 형식(한 줄에 하나):
 * <title> | <price> | <rating>
 *
 * 단,
 * 조건 만족 상품이 0개면 EMPTY
 * K가 개수보다 크면 있는 만큼만 출력
 */
public class REST_http_sort {
    private static String REQUEST_URL = "https://dummyjson.com/products";

    private static String httpGet(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int code = conn.getResponseCode();
        InputStream is = code >= 200 && code < 300 ? conn.getInputStream() : conn.getErrorStream();

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
        } finally {
            conn.disconnect();
        }

        if (code < 200 || code >= 300) {
            // 코테에서는 대충 던져도 됨(원인 파악용)
            throw new RuntimeException("HTTP " + code + " RESP_HEAD=" +
                    sb.substring(0, Math.min(200, sb.length())));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String categoryCond = st.nextToken();
        double minRating = Double.parseDouble(st.nextToken());
        int limit  = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int skip = 0;
        int total = Integer.MAX_VALUE;
        List<Product> products = new ArrayList<>();

        while(skip < total){
            String urlstr = REQUEST_URL + "?limit=" + limit + "&skip=" + skip;
            JSONObject jsonObject = new JSONObject(httpGet(urlstr));
            if(skip == 0) total = jsonObject.getInt("total");
            skip += limit;
            JSONArray jsonArray = jsonObject.getJSONArray("products");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                String category = item.optString("category", null);
                double rating = item.optDouble("rating", 0.0);
                if(category == null || !category.equals(categoryCond) || rating < minRating) continue;
                String title = item.optString("title","UNKNOWN");
                int price = item.optInt("price", -1);
                products.add(new Product(title, category, price, rating));
            }
        }
        products.sort((a,b) -> {
            if(a.rating != b.rating) return Double.compare(b.rating, a.rating);
            if(a.price != b.price) return Integer.compare(b.price, a.price);
            return a.title.compareTo(b.title);
        });

        if (products.isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        int end = Math.min(k, products.size());
        for(int i=0;i<end;i++){
            Product p = products.get(i);
            System.out.println(p.title + " | " + p.price + " | "+p.rating);
        }
    }

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
}
