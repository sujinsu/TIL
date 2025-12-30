import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class REST_countStatus {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String jsonText = br.readLine().trim();

        JSONObject root = new JSONObject(jsonText);
        JSONArray data = root.getJSONArray("data");
        int available = 0;
        int soldOut = 0;
        int discontinued = 0;
        for(int i=0;i<data.length();i++){
            JSONObject item = data.getJSONObject(i);
            String status = item.getString("status");
            switch (status){
                case "AVAILABLE" -> available++;
                case "SOLD_OUT" -> soldOut++;
                case "DISCONTINUED" -> discontinued++;
            }
        }

        System.out.print("AVAILABLE="+available
                +" SOLD_OUT="+soldOut
                +" DISCONTINUED="+discontinued);
    }
}
