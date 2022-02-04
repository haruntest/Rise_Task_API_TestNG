package com.trello.tests;


import com.trello.pojo.Card;
import com.trello.utilities.ConfigurationReader;
import com.trello.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Trello extends TestBase {


    static String boardID;
    static String listID;
    static String cardID;

    @Test(priority = 1)
    public void createBoard() {


        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("key", ConfigurationReader.get("key"));
        queryMap.put("token", ConfigurationReader.get("token"));
        queryMap.put("name", "TrelloBoard");

        boardID =
                given()
                        .contentType(ContentType.JSON)
                        .queryParams(queryMap).
                        when()
                        .post("/boards").
                        then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("name", equalTo("TrelloBoard"))
                        .extract().jsonPath().getString("id");
    }

    @Test(priority = 2)
    public void createList() {


        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("key", ConfigurationReader.get("key"));
        queryMap.put("token", ConfigurationReader.get("token"));
        queryMap.put("name", "TrelloList");

         listID =
                given()
                        .contentType(ContentType.JSON)
                        .queryParams(queryMap)
                        .when()
                        .post("/boards/"+boardID+"/lists")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("name", equalTo("TrelloList"))
                        .extract().jsonPath().getString("id");
    }

    @Test(priority = 3)
    public void createCard() {

        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("key", ConfigurationReader.get("key"));
        queryMap.put("token", ConfigurationReader.get("token"));
        queryMap.put("name", "newCard");
        queryMap.put("idList",listID);

         cardID = given()
                .contentType(ContentType.JSON)
                .queryParams(queryMap).
          when()

                .post("/cards").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON).
                assertThat()
                .body("name", equalTo("newCard"))
                .extract().jsonPath().getString("id");
    }

    @Test(priority = 4)
    public void editCard() {
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("key", ConfigurationReader.get("key"));
        queryMap.put("token", ConfigurationReader.get("token"));
        queryMap.put("name", "newCardEdit");
        queryMap.put("desc","edited Test Description");

        given()
                .contentType(ContentType.JSON)
                        .queryParams(queryMap).
                when()
                .put("/cards/"+cardID).
                then()
                .statusCode(200)
                .contentType(ContentType.JSON).
                assertThat()
                .body("desc", equalTo("edited Test Description"));

    }
    @Test(priority = 5)
    public void getCard() {
        JsonPath jp = given()

                .queryParam("key", ConfigurationReader.get("key"))
                .queryParam("token", ConfigurationReader.get("token"))
                .pathParam("id", cardID).
                when()
                .get("/cards/{id}")
                        .
                then()
                .statusCode(200)
                .extract().jsonPath();


        //Example of POJO Class to get data from our response
        Card card = jp.getObject("", Card.class);
        System.out.println("cardID-->"+card.getId());
        System.out.println("getDesc-->"+card.getDescription());


    }

    @Test(priority = 6)
    public void deleteCard() {

        given()
                .contentType(ContentType.JSON)
                .queryParam("key", ConfigurationReader.get("key"))
                .queryParam("token", ConfigurationReader.get("token")).
                when()
                .delete("/cards/"+cardID).
                then()
                .statusCode(200);

    }

    @Test(priority = 7)
    public void deleteBoard() {

        given()
                .contentType(ContentType.JSON)
                .queryParam("key", ConfigurationReader.get("key"))
                .queryParam("token", ConfigurationReader.get("token"))
                .pathParam("id",boardID).
                when()
            .delete("/boards/{id}").
                then()
                .statusCode(200);
    }


}

