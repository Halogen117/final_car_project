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
public class find_my_location {
    JSONObject location_entirety = null;
    public find_my_location(){
        try{
        URL url_location = new URL("http://ip-api.com/json/");
        HttpURLConnection connect_to_internet = (HttpURLConnection) url_location.openConnection();
        connect_to_internet.setRequestMethod("GET");
        
        int response_coder  = connect_to_internet.getResponseCode();
        if(response_coder == HttpURLConnection.HTTP_OK){
            // Create a BufferedReader to read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connect_to_internet.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            // Read the response line by line
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // Print entirety of the api response
            System.out.println(response.toString());
            
            this.location_entirety = new JSONObject(response.toString());

        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String return_loc_api_status(){
            //================= Retrieve status of the api ==============
            String status = this.location_entirety.getString("status");
            System.out.println("Status of API retrieval: "+status);
            return status;
    }
    
    public String return_loc_api_country(){
        //================= Retrieve country of the api ==============
        String country = location_entirety.getString("country");
        System.out.println("Country of device origin: "+country); 
        return country;
    }
    
    public String return_loc_api_country_code(){
        //================= Retrieve country code of the api ==============
        String country_code = location_entirety.getString("countryCode");
        System.out.println("Country code of device origin: "+country_code); 
        return country_code;
    }
    
    public String return_loc_api_region(){
        //================= Retrieve region of the api ==============
        String region = location_entirety.getString("region");
        System.out.println("Region of device origin: "+region); 
        return region;
    }

    public String return_loc_api_region_name(){
        //================= Retrieve region name of the api ==============
        String region_name = location_entirety.getString("regionName");
        System.out.println("Region of device origin: "+region_name); 
        return region_name;
    }
    
    
    public String return_loc_api_city(){
    //================= Retrieve city name of the api ==============
            String city = location_entirety.getString("city");
            System.out.println("City of device origin: "+city); 
            return city;
    }
    public String return_loc_api_zip_code(){   
            //================= Retrieve estimated_zipcode of the api ==============
            String zip_code = location_entirety.getString("zip");
            System.out.println("Estimated zip code of device origin: "+zip_code); 
            return zip_code;
            
    }
    public float return_loc_api_latitude(){      
            //================= Retrieve estimated latitude of the api ==============
            float latitude = location_entirety.getFloat("lat");
            System.out.println("Estimated latitude of device origin: "+latitude);
            return latitude;
    }
    public float return_loc_api_longitude(){       
            //================= Retrieve estimated longitude of the api ==============
            Float longitude = location_entirety.getFloat("lon");
            System.out.println("Estimated longitude of device origin: "+longitude); 
            return longitude;
    }
    public String return_loc_api_timezone(){
            //================= Retrieve timezone of the api ==============
            String timezone = location_entirety.getString("timezone");
            System.out.println("Timezone of device origin: "+timezone); 
            return timezone;
    }
    public String return_loc_api_isp(){      
            //================= Retrieve Internet Service Provider of the api ==============
            String isp = location_entirety.getString("isp");
            System.out.println("Internet Service Provider of device origin: "+isp);   
            return isp;
    }
    public String return_loc_api_org(){   
            //================= Retrieve Organization provider of the api ==============
            String org = location_entirety.getString("org");
            System.out.println("Organization of device origin: "+org);    
            return org;
    }
    public String return_loc_api_as(){     
            //================= Retrieve Autonomous System (AS)  provider of the api ==============
            String as = location_entirety.getString("as");
            System.out.println("Autonomous System (AS)  of device origin: "+as);
            return as;
    }
    public String return_loc_api_query(){
            //================= Retrieve Query IP of the api ==============
            String query = location_entirety.getString("query");
            System.out.println("IP Query  of device origin: "+query);  
            return query;
    }
        
}
