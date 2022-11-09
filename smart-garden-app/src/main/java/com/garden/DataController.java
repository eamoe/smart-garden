package com.garden;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DataController implements SerialPortMessageListenerWithExceptions {

    private final Map<Long, Integer> data;
    public DataController(Map<Long, Integer> dataCollection) {
        this.data = dataCollection;
    }

    public void addData(long time, int value) {
        this.data.put(time, value);
    }

    private static final byte[] DELIMITER = new byte[]{'\n'};

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

        if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
            return;
        }

        // Bytes received: [0] is MSB last is [CR]
        byte[] data = serialPortEvent.getReceivedData();

        // Convert the received Byte array into a String
        String dataString;
        dataString = null;
        try {
            dataString = new String(data, StandardCharsets.UTF_8);
            dataString  = StringUtils.chop(dataString);  // remove the carriage return (CR)
        }
        catch(Exception e)
        {
            System.out.println("Cannot convert Byte[] to a String.");
        }

        // Convert String into Integer
        assert dataString != null;
        int dataInteger = Integer.parseInt(dataString);

        long time = System.currentTimeMillis();

        this.addData(time, dataInteger);

        // Print out the received data as both integer and String.
        System.out.println("Data received: " + dataInteger + " ... " + dataString);
    }

    @Override
    public void catchException(Exception e) {
        e.printStackTrace();
    }

    @Override
    public byte[] getMessageDelimiter() {
        return DELIMITER;
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }
}