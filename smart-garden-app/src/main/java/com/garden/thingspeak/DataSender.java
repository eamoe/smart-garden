package com.garden.thingspeak;

import com.garden.datastorage.LocalStorage;
import com.garden.datastorage.Pair;
import com.garden.httpconnector.HttpURLConnector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DataSender {

    private static Pair<String, String> retrieveDataItem(String dataItem) {

        String sensorName = "";
        String sensorValue = "";

        if (dataItem.contains("=")) {
            String[] params = dataItem.split("=");
            sensorName = params[0];
            sensorValue = params[1];
        }

        return new Pair<>(sensorName, sensorValue);
    }

    public static void run(LocalStorage localStorage) throws InterruptedException, IOException {

        while (true) {
            Thread.sleep(20000);

            Pair<Long, String> item = localStorage.getFirstItem();
            Long key = item.getKey();
            String valueStr = item.getValue();

            if (key != 0L) {

                Pair<String, String> sensor = retrieveDataItem(valueStr);

                Date date = new Date(key);
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateFormatted = formatter.format(date);
                System.out.println(dateFormatted + " Sensor: " + sensor.getKey() + "; Value: " + sensor.getValue());

                HttpURLConnector connector = new HttpURLConnector("https://api.thingspeak.com/update");
                connector.addParameter("api_key", "94QWFOP77TOLIIZJ");
                connector.addParameter("field1", sensor.getValue());
                connector.sendData();
                System.out.println("Response code: " + connector.getResponseCode());
                //System.out.println(connector.getResponseBody());

                if (connector.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    localStorage.removeItem(key);
                    System.out.println("Pair " + key + " --> " + sensor.getValue() + " was removed.");
                }
                else {
                    System.out.println("Error! Cannot send " + key + " --> " + sensor.getValue());
                }
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
