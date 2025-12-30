import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

public class REST_http_pagenation {
    private static String url = "https://dummyjson.com/products";

    private static String httpGet(String urlStr) throws Exception{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int code = conn.getResponseCode();
        InputStream is = code >= 200 && code < 300 ? conn.getInputStream() : conn.getErrorStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        br.close();
        conn.disconnect();

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
        int limit = Integer.parseInt(st.nextToken());

        int skip = 0;
        int total = Integer.MAX_VALUE;
        int count = 0;
        int maxPrice = -1;
        String maxTitle = "NA";

        while(skip < total){
            String url = "https://dummyjson.com/products?limit=" + limit + "&skip=" + skip;
            String resp = httpGet(url);
            JSONObject jsonObject = new JSONObject(resp);

            if (skip == 0) total = jsonObject.getInt("total");
            skip += limit;

            JSONArray jsonArray = jsonObject.getJSONArray("products");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                String category =item.optString("category", null);
                double rating = item.optDouble("rating", 0.0);
                if(category == null || !category.equals(categoryCond) || rating < minRating) continue;
                count++;
                int price = item.optInt("price", -1);
                if(price > maxPrice){
                    maxPrice = price;
                    maxTitle = item.optString("title","NA");
                }
            }
        }
        System.out.println("COUNT="+count+" MAX_TITLE="+maxTitle);
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
