CREATE TABLE "foodmood" (
    "ID" INT NOT NULL GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    --ID of the user the mood belongs to FOREIGN KEY = accounts.ID  
    "USERID" INT NOT NULL REFERENCES app."accounts"(ID),
    "FOODNAME" VARCHAR(50) NOT NULL,
     --Mood Description max 250 characters 
    "FOODMOODDESCRIPTION" VARCHAR (250) NOT NULL,
    --DATE mm/dd/yyyy
    "DATE" VARCHAR(10) NOT NULL,
    --FAVORITE YES/NO
    "ISFAVORITE" BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (ID)
);