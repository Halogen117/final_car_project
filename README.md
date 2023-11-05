# SC2006 Software Engineering Project
## CarparkNearU

## Before Running the Application

1. **Open PostgreSQL**

2. **Create a New Database**:

   **a. Right Click Database Tab ("Databases" -> "Create" -> Database...)**
   **b. Enter name: "user_storage"**
   **c. Click Save**

   ![Create Database](https://github.com/Halogen117/final_car_project/assets/105267690/1f0f4995-2d40-4a96-8da7-4fd1d56a67ff)

   ![Create Database 2](https://github.com/Halogen117/final_car_project/assets/105267690/937c4c95-0e52-4a1a-886a-149ccfee91c1)

3. **Open user_storage -> schemas -> Tables**

   **a. Right click Tables Tab (Select "QueryTool")**

   ![Open Tables](https://github.com/Halogen117/final_car_project/assets/105267690/9dfa2dfb-9e96-4858-a6bb-3601d377ec5c)

4. **Create Databases for User, History, Favourite & Carpark Database**

   **Copy and run this SQL script in your PostgreSQL QueryTool:**

   ```sql
   CREATE TABLE user_DB (user_ID TEXT, name TEXT, email TEXT, password TEXT, phoneNum TEXT, sec1 TEXT, sec2 TEXT, sec3 TEXT, ans1 TEXT, ans2 TEXT, ans3 TEXT);
   CREATE TABLE history_DB (history_id BIGSERIAL, user_ID TEXT, carpark_ID TEXT, time_stamp TIMESTAMP WITH TIME ZONE);
   CREATE TABLE favourite_DB (user_ID TEXT, carpark_ID TEXT);
   CREATE TABLE carpark_db (carpark_id TEXT, address TEXT, x_coord DOUBLE PRECISION, y_coord DOUBLE PRECISION, car_park_type TEXT, type_of_parking_system TEXT, short_term_parking TEXT, free_parking TEXT, night_parking TEXT, car_park_decks INTEGER, gantry_height DOUBLE PRECISION, car_park_basement TEXT, is_central BOOLEAN, outside_central_rate DOUBLE PRECISION, central_mon_to_fri_rate DOUBLE PRECISION, central_other_rate DOUBLE PRECISION);

5. **Result:**
   <img width="143" alt="image" src="https://github.com/Halogen117/final_car_project/assets/105267690/e0dc6741-2a4c-45e1-a82d-978dab9cc067">

