# SC2006 Software Engineering Project
##CarparkNearU


Before Running the application
1. Open Postgresql

2. Create new Database:
   a. Right Click Database Tab ("Databases" -> "Create" -> Database...)
   b. Enter name : "user_storage"
   c. click save
   <img width="331" alt="image" src="https://github.com/Halogen117/final_car_project/assets/105267690/1f0f4995-2d40-4a96-8da7-4fd1d56a67ff">
   <img width="523" alt="image" src="https://github.com/Halogen117/final_car_project/assets/105267690/937c4c95-0e52-4a1a-886a-149ccfee91c1">

4. Open user_storage->schemas->Tables
   a. Right click Tables Tab (Select "QueryTool")
   <img width="331" alt="image" src="https://github.com/Halogen117/final_car_project/assets/105267690/9dfa2dfb-9e96-4858-a6bb-3601d377ec5c">


6. Create databases for (User, History, Favourite & Carpark database)

//Copy this string on text and run it in your postgresql QueryTool
CREATE TABLE user_DB (user_ID TEXT, name TEXT,email TEXT, password TEXT, phoneNum TEXT, sec1 TEXT, sec2 TEXT, sec3 TEXT, ans1 TEXT, ans2 TEXT, ans3 TEXT);
CREATE TABLE history_DB (history_id BIGSERIAL, user_ID TEXT, carpark_ID TEXT, time_stamp TIMESTAMP WITH TIME ZONE);
CREATE TABLE favourite_DB (user_ID TEXT, carpark_ID TEXT);
Create TABLE carpark_db (carpark_id TEXT,address TEXT,x_coord DOUBLE PRECISION,y_coord DOUBLE PRECISION,car_park_type TEXT,type_of_parking_system TEXT,short_term_parking TEXT,free_parking TEXT,night_parking TEXT,car_park_decks integer,gantry_height DOUBLE PRECISION,car_park_basement TEXT,is_central BOOLEAN,outside_central_rate DOUBLE PRECISION,central_mon_to_fri_rate DOUBLE PRECISION,central_other_rate DOUBLE PRECISION);

<img width="777" alt="image" src="https://github.com/Halogen117/final_car_project/assets/105267690/5d660a2c-2da8-4051-a19e-460d93800d7f">

RESULT:
<img width="143" alt="image" src="https://github.com/Halogen117/final_car_project/assets/105267690/d128596a-7c7a-4ff0-84d1-a49e43a39826">




