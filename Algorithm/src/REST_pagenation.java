import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class REST_pagenation {
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
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pages = Integer.parseInt(br.readLine().trim());

        String[] pageJson = new String[pages];
        for (int i = 0; i < pages; i++) {
            pageJson[i] = br.readLine().trim();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        String categoryCond = st.nextToken();
        double minRating = Double.parseDouble(st.nextToken());

        int count = 0;
        int maxPrice = -1;
        String maxTitle = "NA";

        for (int i = 0; i < pages; i++) {
            JSONObject json = new JSONObject(pageJson[i]);
            JSONArray arr = json.optJSONArray("products");
            if (arr == null) continue;

            for (int j = 0; j < arr.length(); j++) {
                JSONObject item = arr.getJSONObject(j);

                String category = item.optString("category", null);
                if (category == null || !category.equals(categoryCond)) continue;

                double rating = item.optDouble("rating", 0.0);
                if (rating < minRating) continue;

                // 조건 만족하면 count는 무조건 증가 (price 없어도)
                count++;

                // 최고가 후보는 price 있는 경우만
                if (!item.has("price") || item.isNull("price")) continue;
                int price = item.optInt("price", -1);
                if (price < 0) continue;

                if (price > maxPrice) {
                    maxPrice = price;
                    maxTitle = item.optString("title", "UNKNOWN");
                }
            }
        }

        if (count == 0) {
            System.out.println("COUNT=0 MAX_TITLE=NA");
        } else {
            if (maxPrice == -1) maxTitle = "NA"; // 가격 후보가 하나도 없었다면
            System.out.println("COUNT=" + count + " MAX_TITLE=" + maxTitle);
        }
    }
}
