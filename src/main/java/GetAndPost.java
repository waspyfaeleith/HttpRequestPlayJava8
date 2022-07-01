import com.fasterxml.jackson.databind.ObjectMapper;
import models.Employee;
import models.Rates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAndPost {
    public static void MyGETRequest() throws IOException {
        URL urlForGetRequest = new URL("https://api.coingecko.com/api/v3/exchange_rates");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in.close();
            // print result
            //System.out.println("JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());

            String json = response.toString();
            ObjectMapper mapper = new ObjectMapper();

            Rates rates = mapper.readValue(json, Rates.class);

            System.out.println(rates.toString());
            System.out.println("Done!");
        } else {
            System.out.println("GET NOT WORKED");
        }
    }

    public static void PutRequest(Employee employee) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(employee);
        System.out.println(jsonResult);


        System.out.println(jsonResult);
        URL obj = new URL("http://dummy.restapiexample.com/api/v1/update/4710");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("PUT");
        postConnection.setRequestProperty("Content-Type", "application/json");


        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(jsonResult.getBytes());
        os.flush();
        os.close();

        int responseCode = postConnection.getResponseCode();
        System.out.println("PUT Response Code :  " + responseCode);
        System.out.println("PUT Response Message : " + postConnection.getResponseMessage());

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
    }
}


