CREATE TABLE "mood" (
    "ID" INT NOT NULL GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    --ID of the user the mood belongs to FOREIGN KEY = accounts.ID  
    "USERID" INT NOT NULL REFERENCES app."accounts"(ID),
    "MOOD" VARCHAR(50) NOT NULL,
    --Severity of mood 
    "SEVERITY" VARCHAR (10) NOT NULL,
    --Mood Description max 250 characters
    "DESCRIPTION" VARCHAR(250),
    --Time mood occured (hh:mm:ss) 
    "TIME" TIME,
    PRIMARY KEY (ID)
);