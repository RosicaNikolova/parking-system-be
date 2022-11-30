package org.onlydevs.outlook;
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import org.onlydevs.domain.Appointment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Component
public class OutlookCalendarService {

    private static String authority;
    private static String clientId;
    private static String secret;
    private static String scope;
    private static ConfidentialClientApplication app;

    public OutlookCalendarService(){

        try {
            setUpSampleData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
        	BuildConfidentialClientObject();
            IAuthenticationResult result = getAccessTokenByClientCredentialGrant();
            //String usersListFromGraph = getUsersListFromGraph(result.accessToken());
            //createAppointment(result.accessToken());
            //System.out.println("Users in the Tenant = " + usersListFromGraph);
//            System.out.println("Press any key to exit ...");
//            System.in.read();

        } catch(Exception ex){
            System.out.println("Oops! We have an exception of type - " + ex.getClass());
            System.out.println("Exception message - " + ex.getMessage());
        }
    }
    private void BuildConfidentialClientObject() throws Exception {
        
    	// Load properties file and set properties used throughout the sample
    	app = ConfidentialClientApplication.builder(
                clientId,
                ClientCredentialFactory.createFromSecret(secret))
                .authority(authority)
                .build();		        
    }

    private IAuthenticationResult getAccessTokenByClientCredentialGrant() throws Exception {
    	
    	// With client credentials flows the scope is ALWAYS of the shape "resource/.default", as the
        // application permissions need to be set statically (in the portal), and then granted by a tenant administrator
        ClientCredentialParameters clientCredentialParam = ClientCredentialParameters.builder(
                Collections.singleton(scope))
                .build();
    	
        CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParam);
        return future.get();
    }

    private String getUsersListFromGraph(String accessToken) throws IOException {
        URL url = new URL("https://graph.microsoft.com/v1.0/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Accept","application/json");

        int httpResponseCode = conn.getResponseCode();
        if(httpResponseCode == HTTPResponse.SC_OK) {

            StringBuilder response;
            try(BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))){

                String inputLine;
                response = new StringBuilder();
                while (( inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response.toString();
        } else {
            return String.format("Connection returned HTTP code: %s with message: %s",
                    httpResponseCode, conn.getResponseMessage());
        }
    }

    public void createAppointment(Appointment appointment) throws IOException {
        //URL url = new URL("https://graph.microsoft.com/beta/users/mikewangfontystest_outlook.com#EXT#@mikewangfontystestoutlook.onmicrosoft.com/calendar/events");
        IAuthenticationResult result = null;
        try {
            result = getAccessTokenByClientCredentialGrant();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String accessToken = result.accessToken();

        URL url = new URL("https://graph.microsoft.com/v1.0/users/ca0dfa2b-a687-4448-95cf-c66cd08daf96/calendar/events");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestProperty("Accept","application/json");
        conn.setRequestProperty("Prefer", "outlook.timezone=\"W. Europe Standard Time\"");
        String jsonInputString = "{\n" +
                "  \"subject\": \"Meeting with "+appointment.getVisitor().getFirstName()+" "+appointment.getVisitor().getLastName()+"\", " +
                "  \"body\": {\n" +
                "    \"contentType\": \"HTML\",\n" +
                "    \"content\": \"Does this time work for you?\"\n" +
                "  },\n" +
                "  \"start\": {\n" +
                "      \"dateTime\": \""+appointment.getDateTime().toString()+":00\",\n" +
                "      \"timeZone\": \"W. Europe Standard Time\"\n" +
                "  },\n" +
                "  \"end\": {\n" +
                "      \"dateTime\": \""+appointment.getDateTime().plusMinutes(30).toString()+":00\",\n" +
                "      \"timeZone\": \"W. Europe Standard Time\"\n" +
                "  },\n" +
                "  \"location\":{\n" +
                "      \"displayName\":\"Sioux building\"\n" +
                "  },\n" +
                "  \"attendees\": [\n" +
                "    {\n" +
                "      \"emailAddress\": {\n" +
                "        \"address\":\"mw-3@hotmail.nl\",\n" +
                "        \"name\": \"Mike Wang\"\n" +
                "      },\n" +
                "      \"type\": \"required\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int httpResponseCode = conn.getResponseCode();
        if(httpResponseCode == HTTPResponse.SC_OK) {

            StringBuilder response;
            try(BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))){

                String inputLine;
                response = new StringBuilder();
                while (( inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } else {
            System.out.println(String.format("Connection returned HTTP code: %s with message: %s",
                    httpResponseCode, conn.getResponseMessage()));
        }
    }

    public List<Appointment> getAppointments() throws IOException {
        //URL url = new URL("https://graph.microsoft.com/beta/users/mikewangfontystest_outlook.com#EXT#@mikewangfontystestoutlook.onmicrosoft.com/calendar/events");
        IAuthenticationResult result = null;
        try {
            result = getAccessTokenByClientCredentialGrant();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String accessToken = result.accessToken();

        URL url = new URL("https://graph.microsoft.com/v1.0/users/ca0dfa2b-a687-4448-95cf-c66cd08daf96/calendar/events");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestProperty("Accept","application/json");
        conn.setRequestProperty("Prefer", "outlook.timezone=\"W. Europe Standard Time\"");

        int httpResponseCode = conn.getResponseCode();
        if(httpResponseCode == HTTPResponse.SC_OK) {
            StringBuilder response;
            try(BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))){

                String inputLine;
                response = new StringBuilder();
                while (( inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println( response.toString());
            }
        } else {
            System.out.println(String.format("Connection returned HTTP code: %s with message: %s",
                    httpResponseCode, conn.getResponseMessage()));
        }
        return null;
    }

    /**
     * Helper function unique to this sample setting. In a real application these wouldn't be so hardcoded, for example
     * different users may need different authority endpoints or scopes
     */
    public void setUpSampleData() throws IOException {
        // Load properties file and set properties used throughout the sample
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        authority = properties.getProperty("AUTHORITY");
        clientId = properties.getProperty("CLIENT_ID");
        secret = properties.getProperty("SECRET");
        scope = properties.getProperty("SCOPE");
    }
}
