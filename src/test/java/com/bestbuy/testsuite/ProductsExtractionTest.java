package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;


public class ProductsExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI ="http://localhost";
        RestAssured.port=3030;
        response= given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    //21. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println("The limit is :  "+ limit);
    }
    //22. Extract the total
    @Test
    public void test002(){
        int total = response.extract().path("total");
        System.out.println("The Total is : " + total);
    }
    //23. Extract the name of 5th product
    @Test
    public void test003(){
        String name = response.extract().path("data[4].name");
        System.out.println("Name of 5th product :  " +name);
    }
    //24. Extract the names of all the products
    @Test
    public void test004(){
        List<String> allTheProductName = response.extract().path("data.name");
        System.out.println("Names of all the products :  " +allTheProductName);
    }
    //25. Extract the productId of all the products
    @Test
    public void test005(){
        List<Integer> allTheProductIds = response.extract().path("data.id");
        System.out.println("ProductId of all the products :" + allTheProductIds);
    }
    //26. Print the size of the data list
    @Test
    public void test006(){
        List<Integer> allTheProductIds = response.extract().path("data.id");
        int size = allTheProductIds.size();
        System.out.println("The size of the data list" + size);
      }
     //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
     @Test
     public void test007() {
         List<HashMap<String,?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

         System.out.println("------------------StartingTest---------------------------");
         System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: "+ values);
         System.out.println("------------------End of Test---------------------------");
     }
    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
        @Test
        public void test008(){
            String model = response.extract().path("data[8].model");
            System.out.println(model);
        }
     //29. Get all the categories of 8th products
    @Test
    public void test009(){
       List<HashMap<String,?>> categories = response.extract().path("data[7].categories");
        System.out.println("All the categores of 8th products :" + categories);
    }
    //30. Get categories of the store where product id = 150115
    @Test
    public void test010(){
        List<HashMap<String,?>> categories = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("Get Categories of the store where product id = 150115 : "+  categories);
    }
    //31. Get all the descriptions of all the products
    @Test
    public void test011(){
        List<String > description = response.extract().path("data.description");
          System.out.println("Get all the descriptions of all the products :  \n\n" +description);
     }
    //32. Get id of all the all categories of all the products
    @Test
    public void test012(){
        List<HashMap<String ,?>> allIdOfCategories = response.extract().path("data.categories.id");
        System.out.println("Id of all Categories of all Products   :\n\n"+ allIdOfCategories);
    }
    //33. Find the product names Where type = HardGood
    @Test
    public void test013(){
        List<String> productNames = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("Product name where type = HardGood  :\n\n"+ productNames);
    }
   //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014(){
        List<String> categories = response.extract().path("data.findAll{it.name =='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.id");
        System.out.println(categories);

        System.out.println("Total Number  of categories :  "+ categories.size());
    }
    //35. Find the createdAt for all products whose price < 5.49
   @Test
    public void test015(){
        List<String> createAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("CreatedAt for all the products whose price < 5.49  : \n\n"+createAt);
     }
    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016(){
     List<String > name = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("Name of all categories where product name = Energizer - MAX Batteries AA (4-Pack) :  \n\n"+name);
    }

//37. Find the manufacturer of all the products
    @Test
    public void test017(){
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("Manufacturer of all the products  : \n\n "+manufacturer);
    }
    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018(){
        List<String> image = response.extract().path("data.image");
        System.out.println("The image of products whose manufacturer is = Energizer  : \n\n"+image);
    }
    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019(){
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("CreatedAt for all categories products whose price > 5.99  : \n\n"+createdAt);
    }
   //40. Find the uri of all the products
    @Test
    public void test020(){
       List<String> url = response.extract().path("data.url");
        System.out.println("The url of all the products  : \n\n"+url);
     }

}
