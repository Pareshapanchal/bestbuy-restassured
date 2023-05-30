package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {
    StorePojo storePojo = new StorePojo();
       static int newid;
       static    String name ="Man"+ TestUtils.randomStr(5);
       static    String type = "BigBox";
       static    String address=TestUtils.getRandomValue()+","+"Pinner"+TestUtils.randomStr(4);
       static    String address2 ="Pinner";
       static    String city= "London";
       static    String state="Middex";
       static    String zip="HA5 5IU";
       static    double lat = 50.231256;
       static     double lng=-63.235;
       static     String hours="Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8" ;

    @Test
    public void test001(){
        Response response = given()
                .when()
                .get("/stores");
                response.then().log().all();
                response.prettyPrint();
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void test002(){
       storePojo.setName(name);
       storePojo.setType(type);
       storePojo.setAddress(address);
       storePojo.setAddress2(address2);
       storePojo.setCity(city);
       storePojo.setState(state);
       storePojo.setZip(zip);
       storePojo.setLat(lat);
       storePojo.setLng(lng);
       storePojo.setHours(hours);
       Response response = given()
               .contentType(ContentType.JSON)
               .when()
               .body(storePojo)
               .post("/stores");
               response.prettyPrint();
               response.then().log().all().statusCode(201);
               Assert.assertEquals(201,response.getStatusCode());
               newid = response.then().extract().path("id");
              System.out.println(newid);
    }
    @Test

    public void test003(){//patch request
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);

        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-4; Sun: 10-4");
        Response response = given()
                .contentType(ContentType.JSON)
                //.pathParam("id",newid)
                .when()
                .body(storePojo)
                .patch("/stores/"+newid);
        response.prettyPrint();
        response.then().log().all();
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void test004(){
        Response response = given()
                .when()
                .get("/stores/"+newid);
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void test005(){
        Response response = given()
                .when()
                .delete("/stores/"+newid);
        response.prettyPrint();
        response.then().log().all();
        Assert.assertEquals(200,response.getStatusCode());
    }

}
