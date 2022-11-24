package com.garden;

import com.garden.httpconnector.HttpURLConnector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // SENDING DATA TO SERVER
        //HttpURLConnector connector = new HttpURLConnector("https://api.thingspeak.com/update");
        //connector.addParameter("api_key", "94QWFOP77TOLIIZJ");
        //connector.addParameter("field1", "2048");
        //connector.sendData();
        //System.out.println("Response code: " + connector.getResponseCode());
        //System.out.println(connector.getResponseBody());

        // RECEIVING DATA FROM SERVER
        HttpURLConnector connector = new HttpURLConnector("https://api.thingspeak.com/channels/1908887/feeds.json?");
        connector.addParameter("api_key", "SJ7A4MBZIJCCDCFQ");
        connector.addParameter("results", "2");
        String response = connector.receiveData();
        System.out.println(response);
    }
}
