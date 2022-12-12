package com.garden;

import com.garden.datastorage.LocalStorage;
import com.garden.datastorage.LocalStorageMap;
import com.garden.serialport.SerialPortReader;
import com.garden.thingspeak.DataSender;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        LocalStorage localStorage = new LocalStorageMap();
        SerialPortReader.run(localStorage); // Gets data through serial port and puts it to the local storage
        DataSender.run(localStorage); // Sends the local storage data to ThingSpeak channel

    }
}
