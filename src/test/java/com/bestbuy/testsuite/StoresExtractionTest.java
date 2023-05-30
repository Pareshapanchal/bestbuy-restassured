package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI ="http://localhost";
        RestAssured.port=3030;
        response= given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

   //   1. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println(limit);
    }
//2. Extract the total
@Test
public void test002(){
    int total = response.extract().path("total");
    System.out.println(total);
}
//3. Extract the name of 5th store
@Test
public void test003(){
    String name = response.extract().path("data[4].name");
    System.out.println(name);
}
//4. Extract the names of all the store
@Test
public void test004(){
    List<String > names = response.extract().path("data.name");
    System.out.println(names);
}
//5. Extract the storeId of all the store
@Test
public void test005(){
    List<String> storeId = response.extract().path("data.services.storeservices.storeId");
    System.out.println(storeId);
}
//6. Print the size of the data list
@Test
public void test006(){
    List<Integer> data = response.extract().path("data.id");
    System.out.println(data.size());
}
//7. Get all the value of the store where store name = St Cloud
@Test
public void test007(){
    List<HashMap<String,?>> allValue = response.extract().path("data.findAll{it.name == 'St Cloud'}");
    System.out.println(allValue);
}
//8. Get the address of the store where store name = Rochester
@Test
public void test008(){
    List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
    System.out.println(address);
}
//9. Get all the services of 8th store
@Test
public void test009(){
    List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
    System.out.println(address);
}
//10. Get storeservices of the store where service name = Windows Store
@Test
public void test010(){
    List<HashMap<String,?>> storeservices = response.extract().path("data.findAll { it.services.find { it.name == 'Windows Store' } != null }.services.storeservices");
    System.out.println("Storeservices of the store where service name = Windows Store is " + storeservices);

}
//11. Get all the storeId of all the store
@Test
public void test011(){
    List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
    System.out.println(storeId);
}
//12. Get id of all the store
@Test
public void test012(){
    List<Integer> allId = response.extract().path("data.id");
    System.out.println(allId);
}

//13. Find the store names Where state = ND
@Test
public void test013(){
   List<String> storeName = response.extract().path("data.findAll{it.state =='ND'}.name");
    System.out.println(storeName);

}
//14. Find the Total number of services for the store where store name = Rochester
@Test
public void test014(){
    List<String> totalNumberOfServices = response.extract().path("data.findAll{it.name =='Rochester'}.services");
    System.out.println(totalNumberOfServices.size());
}
// 15. Find the createdAt for all services whose name = “Windows Store”
@Test
public void test015(){
    List<HashMap<String,?>> storeservices = response.extract().path("data.findAll { it.services.find { it.name == 'Windows Store' } != null }services.createdAt");
    System.out.println("Storeservices of the store where service name = Windows Store is " + storeservices);
}

//16. Find the name of all services Where store name = “Fargo”
@Test
public void test016(){
    List<HashMap<String,?>> nameOfAllServices = response.extract().path("data.findAll{it.name =='Fargo'}.services");
    System.out.println(nameOfAllServices);
}
// 17. Find the zip of all the store
@Test
public void test017(){
    List<String> allZip = response.extract().path("data.zip");
    System.out.println(allZip);
}
//18. Find the zip of store name = Roseville
@Test
public void test018(){
    List<String> zip= response.extract().path("data.findAll{it.name =='Roseville'}.zip");
    System.out.println(zip);
}
//19. Find the storeservices details of the service name = Magnolia Home Theater
@Test
public void test019(){
    List<HashMap<String,?>> storeservicesDetail = response.extract().path("data.findAll { it.services.find { it.name == 'Magnolia Home Theater' } != null }.services.storeservices");
    System.out.println(storeservicesDetail);
}
//20. Find the lat of all the stores
@Test
public void test020(){
    List<String> lat = response.extract().path("data.lat");
    System.out.println(lat);
}
}
