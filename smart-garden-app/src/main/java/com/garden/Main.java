package com.garden;

import com.garden.httpconnector.HttpURLConnector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        LocalStorageMap localStorage = new LocalStorageMap();
        SerialPortReader.run(localStorage);

        while (true) {
            Thread.sleep(20000);

            Pair<Long, String> item = localStorage.getFirstItem();
            Long key = item.getKey();
            String value = item.getValue();

            if (key != 0L) {
                Date date = new Date(key);
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateFormatted = formatter.format(date);
                System.out.println(dateFormatted + "   " + value);

                HttpURLConnector connector = new HttpURLConnector("https://api.thingspeak.com/update");
                connector.addParameter("api_key", "94QWFOP77TOLIIZJ");
                connector.addParameter("field1", value);
                connector.sendData();
                System.out.println("Response code: " + connector.getResponseCode());
                //System.out.println(connector.getResponseBody());

                if (connector.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    localStorage.removeItem(key);
                    System.out.println("Pair " + key + " --> " + value + " was removed.");
                }
            }
        }

        // SENDING DATA TO SERVER
        //HttpURLConnector connector = new HttpURLConnector("https://api.thingspeak.com/update");
        //connector.addParameter("api_key", "94QWFOP77TOLIIZJ");
        //connector.addParameter("field1", "2048");
        //connector.sendData();
        //System.out.println("Response code: " + connector.getResponseCode());
        //System.out.println(connector.getResponseBody());

        // RECEIVING DATA FROM SERVER
        //HttpURLConnector connector = new HttpURLConnector("https://api.thingspeak.com/channels/1908887/feeds.json?");
        //connector.addParameter("api_key", "SJ7A4MBZIJCCDCFQ");
        //connector.addParameter("results", "2");
        //String response = connector.receiveData();
        //System.out.println(response);
    }
}
