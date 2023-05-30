package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {
    ProductPojo productPojo = new ProductPojo();
            static String name = "Duracell"+ TestUtils.getRandomValue();
            static String type ="HardGood";
            static double price= 5.99;
            static double shipping=0 ;
            static String upc="041333424019" ;
            static String description="Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
            static String manufacturer="Duracell";
            static String model="MN2400B4Z";
            static String url="http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
            static String image="http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
            static int newid;
    @Test
    public void test001(){
        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void test002(){
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        Response response = given()
                .header("Content-Type","application/json")
                .when()
                .body(productPojo)
                .post("/products");

        response.prettyPrint();
        newid = response.then().extract().path("id");
        System.out.println(newid);
        Assert.assertEquals(201,response.getStatusCode());

    }
    @Test
    public void test003(){
        Response response = given()
                .when()
                .get("/products/"+newid);
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void test004(){
        productPojo.setName(name);
        productPojo.setType("SoftGood");
        productPojo.setPrice(6.99);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch("/products/"+newid);
        response.prettyPrint();
        response.then().log().all();
        String actual_Type = response.jsonPath().get("type").toString();
        String actual_price = response.jsonPath().get("price").toString();
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("SoftGood",actual_Type);
        Assert.assertEquals("6.99",actual_price);

    }
    @Test
    public void test005() {
        Response response = given()
                .when()
                .delete("/products/"+newid);
        response.prettyPrint();
        response.then().log().all();
        Assert.assertEquals(200,response.getStatusCode());
    }

}
