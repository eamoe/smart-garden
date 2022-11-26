package com.garden.httpconnector;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class HttpURLConnector {

    private final String baseURL;
    private static final String USER_AGENT = "Mozilla/5.0";
    private final Map<String, String> parameters;

    private int responseCode;
    private String responseBody;

    public HttpURLConnector(String baseURL) {
        this.baseURL = baseURL;
        this.parameters = new HashMap<>();
        this.responseCode = 0;
        this.responseBody = "";
    }

    public int getResponseCode() {
        return responseCode;
    }

    private void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    private void setResponseBody(String response) {
        this.responseBody = response;
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public void sendData() throws IOException {
        URL url = new URL(baseURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());

        String params = ParameterStringBuilder.getParamsString(parameters);
        out.writeBytes(params);
        out.flush();
        out.close();

        this.responseBody = this.getResponse(connection);

    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        StringBuilder fullResponseBuilder = new StringBuilder();

        this.setResponseCode(connection.getResponseCode());

        connection.getHeaderFields()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != null)
                .forEach(entry -> {

                    fullResponseBuilder.append(entry.getKey())
                            .append(": ");

                    List<String> headerValues = entry.getValue();
                    Iterator<String> it = headerValues.iterator();
                    if (it.hasNext()) {
                        fullResponseBuilder.append(it.next());

                        while (it.hasNext()) {
                            fullResponseBuilder.append(", ")
                                    .append(it.next());
                        }
                    }

                    fullResponseBuilder.append("\n");
                });

        Reader streamReader;

        if (connection.getResponseCode() > 299) {
            streamReader = new InputStreamReader(connection.getErrorStream());
        } else {
            streamReader = new InputStreamReader(connection.getInputStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();

        fullResponseBuilder.append("Response: ")
                .append(content);

        return fullResponseBuilder.toString();
    }

    public String receiveData() throws IOException {
        URL obj = new URL(baseURL + ParameterStringBuilder.getParamsString(parameters));
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        return this.getResponse(connection);
    }

}