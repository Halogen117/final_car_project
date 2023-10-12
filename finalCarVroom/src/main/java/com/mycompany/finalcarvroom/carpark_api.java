/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalcarvroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Halogen
 */
public class carpark_api {
    private String timestamp;
    private JSONArray carpark_data;
    private JSONArray carpark_info;
    private JSONArray items;
    public carpark_api(){

    }
    
    public void retrieve_data(){
                try {
            // Define the API URL
            String apiUrl = "https://api.data.gov.sg/v1/transport/carpark-availability";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful (HTTP status code 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Print entirety of the api response
                System.out.println(response.toString());
                
                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                // Item  contains the timestamp and carpark data
                JSONArray items = jsonResponse.getJSONArray("items");
                //this.items = items;
                // Loop through the items array
                for (int i = 0; i < items.length(); i++) {
                    JSONObject central_data = items.getJSONObject(i);
                    // =========Prints timestamp of when data was acquired at  =============
                    String timestamp = central_data.getString("timestamp");
                    System.out.println("Timestamp of the data being sent: "+timestamp); // Timestamp
                    //this.timestamp = timestamp;
                    
                    JSONArray carpark_data = central_data.getJSONArray("carpark_data");
                    System.out.println(carpark_data); // Acquire carpark_data
                    //this.carpark_data = carpark_data;
                    for(int j=0; j<carpark_data.length(); j++){
                        // =========Prints array of individual carpark lots and information =============
                        JSONObject indv_carpark = carpark_data.getJSONObject(j);
                        System.out.println(indv_carpark); 
                        
                        // =========Prints carpark number of individual carpark lot =============
                        String carpark_number = indv_carpark.getString("carpark_number");
                        System.out.println("Carpark lot number: "+carpark_number);
                        
                        // =========Prints updated datetime of individual carpark lot =============
                        String update_datetime = indv_carpark.getString("update_datetime");
                        System.out.println("Updated datetime of carpark lot: "+update_datetime); 
                        
                        // =========Prints array of individual carpark lot characteristics of specific carpark=============
                        JSONArray carpark_info = indv_carpark.getJSONArray("carpark_info");
                        System.out.println(carpark_info);
                        //this.carpark_info = carpark_info;
                        
                        for(int k=0; k<carpark_info.length(); k++){
                            JSONObject carpark_info_detail = carpark_info.getJSONObject(k);
                            // =========Prints number of available carpark lots of specific carpark=============
                            String lots_avail = carpark_info_detail.getString("lots_available");
                            System.out.println("Available lots of specific carpark: "+lots_avail); 
                            
                            // =========Prints number of total carpark lots of specific carpark=============
                            String total_lots = carpark_info_detail.getString("total_lots");
                            System.out.println("Total lots of specific carpark: "+total_lots);                      
                            
                            // =========Prints Lot type of specific carpark=============
                            String lot_type = carpark_info_detail.getString("lot_type");
                            System.out.println("Lot type of specific carpark: "+lot_type);     
                        }
                    }
                }
            } else {
                // Handle the error here
                System.out.println("HTTP Request Failed with status code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
/*
// First Loop
    public String carpark_api_timestamp(){
        // =========Prints timestamp of when data was acquired at  =============
        String timestamp = this.central_data.getString("timestamp");
        System.out.println("Timestamp of the data being sent: "+timestamp); // Timestamp
        return timestamp;
    }
//===========================SECOND LOOP=================================   
    public String carpark_api_carpark_number(){
        // =========Prints carpark number of individual carpark lot =============
        String carpark_number = this.indv_carpark.getString("carpark_number");
        System.out.println("Carpark lot number: "+carpark_number);
        return carpark_number;
    }
    
    public String carpark_api_update_datetime(){
        // =========Prints updated datetime of individual carpark lot =============
        String update_datetime = this.indv_carpark.getString("update_datetime");
        System.out.println("Updated datetime of carpark lot: "+update_datetime);
        return update_datetime;
    }


                        
    
//===========================FINAL LOOP=================================
    public String carpark_api_lots_available(){
        // =========Prints number of available carpark lots of specific carpark=============
        String lots_avail = this.carpark_info_detail.getString("lots_available");
        System.out.println("Available lots of specific carpark: "+lots_avail); 
        return lots_avail;
    }

    public String carpark_api_total_lots(){
        // =========Prints number of total carpark lots of specific carpark=============
        String total_lots = this.carpark_info_detail.getString("total_lots");
        System.out.println("Total lots of specific carpark: "+total_lots);   
        return total_lots;
    }
    
    public String carpark_api_lot_type(){
        // =========Prints Lot type of specific carpark=============
        String lot_type = this.carpark_info_detail.getString("lot_type");
        System.out.println("Lot type of specific carpark: "+lot_type);  
        return lot_type;
    }

*/

}

