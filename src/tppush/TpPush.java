/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tppush;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Thanh
 */
public class TpPush {

    /**
     * @param args the command line arguments
     */
    private static String SERVER_KEY = "AAAAArGXr1c:APA91bGJ4hHWw67-dB9iLb3v-TX_c33eFf5qhvcwzMG-iqRUFfvOsEWqlWtvEFPq6v_qBEEz7SMRCvc1ZLTSzKOrbsoySJ8n64ceCV_L6yv59D7zioUTtiidk3_LWpHFqWUB4QSwTpwg";
    private static String DEVICE_TOKEN = "eymtobmRxns:APA91bFrpiKN80lL6-p8ZgOCSyPDz67Kn2dQj9LDIVq4hSxq6lJ1Z569YN3RMhmGqbSfgTwQWgOpitGrEvAAs4z1XGgTA94PX6dDwPSr2ZNnhmIwUGvNSxcjE6a2cslmTB-tlCs2yLx5T5EKWsvNONLHmekweeF4HQ";

    public static void main(String[] args) {
        // TODO code application logic here

        String title = "Hello Trader Tuyên. Thay device token để push";
        String message = "This is auto message from Trader Pro Server. Your key already added";

//        title = "BNB |2| ADA";
//        message = "<b> *** ADA *** </b>&nbsp;<b>PRI OP: </b>0.00014540<br/><b>PRI HT: </b>0.00014880&nbsp;<b>PRI 1H: </b>0.00014490<br/><b>PRI 5P: </b>0.00014740&nbsp;<b>PRI 30P: </b>0.00014570<br/><b>PRI 4H: </b>0.00014170&nbsp;<b>PRI 12H: </b>0.00014520<br/><b>VOL HT: </b>8.6542&nbsp;<b>VOL 2H: </b>3.0818<br/><b>VOL 1H: </b>8.2202&nbsp;<b>VOL TB: </b>5.5173<br/><b>Number of Buyer/Seller: </b>881/326<br/><b>Number of Taker/Maker: </b>1152/274<br/> <br/><b></b>";
//
//        title = "BNB ADA BUYYY";
//        message = "PRICE: ~" + "0.00002322";
//
//        title = "BNB TakeProfit ADA";
//        message = "<b> Sell " + "XMR" + ": </b>" + "0.13040304" + "&nbsp;"
//                + "<b> Buy: </b>" + "0.01540900" + "<br/>"
//                + "<b> ===> PROFIT: " + "5.23" + "%</b>";
        androidPush(title, message);
    }

    public static void androidPush(String title, String message) {

        System.out.println("Mess: " + message);
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int min = rightNow.get(Calendar.MINUTE);
        int nam = rightNow.get(Calendar.YEAR);
        int isecond = rightNow.get(Calendar.SECOND);
        int mlsec = rightNow.get(Calendar.MILLISECOND);
        int thang = rightNow.get(Calendar.MONTH) + 1;
        int ngay = rightNow.get(Calendar.DAY_OF_MONTH);
        String strTime = hour + ":" + min + ":" + isecond + " - " + ngay + "." + thang;
        try {
            title = "HM " + title + " *** " + strTime;
            String pushMessage = "{\"data\":{\"title\":\""
                    + title
                    + "\",\"message\":\""
                    + message
                    + "\"},\"to\":\""
                    + DEVICE_TOKEN
                    + "\"}";
            pushMessage = "{\n"
                    + "   \"to\" : \"" + DEVICE_TOKEN + "\",\n"
                    + "   \"data\" : {\n"
                    + "     \"title\" : \"" + title + "\",\n"
                    + "     \"message\" : \"" + message + "\",\n"
                    + "   }\n"
                    + " }";
            JSONObject json = new JSONObject(pushMessage);
            pushMessage = json.toString();
            System.out.println(pushMessage);
            // Create connection to send FCM Message request.
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send FCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(pushMessage.getBytes());
            System.out.println(conn.getResponseCode());
            System.out.println(conn.getResponseMessage());
        } catch (Exception e) {

        }

    }

}
