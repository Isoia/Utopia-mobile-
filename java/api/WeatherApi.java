package api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;

public class WeatherApi {
    private int T1HValue = -999;
    private int SKYValue = -999;
    private double RN1Value = -999;
	
	public WeatherApi(String x, String y) throws Exception {
		String apiKey = "P8Isyxy1JwQqSc9pGKnnj0SUUGq7Id5wTYbO7LPKoIpQc3D5bsQjavdUvLjyO7NXXzgLPlZJptAy5K6xB0IR2Q%3D%3D";
    	Calendar calendar = Calendar.getInstance();
    	
        String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";
        url += "?ServiceKey=" + apiKey;
        url += "&pageNo=1";
        url += "&numOfRows=1000";
        url += "&dataType=JSON";
        url += "&base_date=" + String.format("%d%02d%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        url += "&base_time=" + String.format("%02d00", calendar.get(Calendar.HOUR_OF_DAY));
        url += "&nx=" + x.substring(0, 2);
        url += "&ny=" + y.substring(0, 3);
        
        URL url2 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.connect();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        
        String line;
        try {
        	while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
        } finally {
        	try {
        		rd.close();
        	} catch (IOException e) {
        		
        	}
        }
        
        conn.disconnect();
        
        System.out.println(sb);
        
        try {
        	JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(sb.toString());
            
            obj = (JSONObject) obj.get("response");
            obj = (JSONObject) obj.get("body");
            obj = (JSONObject) obj.get("items");
            JSONArray arr = (JSONArray) obj.get("item");

            for(int i = 0; i < arr.size(); i++) {
            	obj = (JSONObject) arr.get(i);
            	
            	if(obj.get("category").equals("T1H") && T1HValue == -999) {
            		T1HValue = Integer.parseInt((String)obj.get("fcstValue"));
            	} else if(obj.get("category").equals("SKY") && SKYValue == -999) {
            		SKYValue = Integer.parseInt((String)obj.get("fcstValue"));
            	} else if(obj.get("category").equals("RN1") && RN1Value == -999) {
            		String temp = (String)obj.get("fcstValue");
            		
            		if(temp.equals("강수 없음")) {
            			RN1Value = 0;
            		} else {
            			RN1Value = Double.parseDouble(temp.substring(0, temp.length() - 2));
            		}
            	}
            }
        } catch(Exception e) {
        	T1HValue = T1H.Warm.ordinal();
        	SKYValue = SKY.Sunny.ordinal();
        	RN1Value = 0;
        }
	}
	
	public T1H getT1HValue() {
		if(T1HValue >= 28) {
			return T1H.Hot;
		} else if(T1HValue >= 18) {
			return T1H.Warm;
		} else if(T1HValue >= 8) {
			return T1H.Cool;
		} else {
			return T1H.Cold;
		}
	}

	public void setT1HValue(int t1hValue) {
		T1HValue = t1hValue;
	}

	public SKY getSKYValue() {		
		if(RN1Value > 0) {
			return SKY.Rainy;
		}
		
		switch(SKYValue) {		
		case 1:
			return SKY.Sunny;
		case 3:
			return SKY.Cloudy;
		case 4:
			return SKY.Foggy;
		}
		return SKY.None;
	}

	public void setSKYValue(int sKYValue) {
		SKYValue = sKYValue;
	}
	
	public enum T1H {
		Cold("꽤 추워요"),
		Cool("시원한 날씨에요"),
		Warm("따듯한 날씨에요"),
		Hot("꽤 뜨거워요");
		
		private String getT1H;
		
		T1H(String s) {
			getT1H = s;
		}
		
		@Override
		public String toString() {
			return getT1H;
		}
	}
	
	public enum SKY {
		None("없음"),
		Sunny("맑은 날씨네요"),
		Rainy("비가 오고 있네요"),
		Cloudy("구름 낀 날씨네요"),
		Foggy("안개 낀듯한 날씨네요");
		
		private String getSKY;
		
		SKY(String s) {
			getSKY = s;
		}
		
		@Override
		public String toString() {
			return getSKY;
		}
	}
}

