/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import static com.google.common.base.Predicates.equalTo;
import entity.Person;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class RestPersonIntegrationTest {
    
    public RestPersonIntegrationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/Exercise-REST_Persons";
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void serverIsRunning() {
        System.out.println("ServerIsRunning");
        given().
        when().get().
        then().statusCode(200);
    }

    @Test
    public void postGetDeletePerson() {
        Person p = new Person("Kurt","Wonnegut", "12345678");
        Person newPerson = given()
                .contentType("application/json")
                .body(p)
                .when().post("/api/person")
                .as(Person.class);
        
        assertNotNull(newPerson.getId());
        
        given()
                .contentType(ContentType.JSON)
                .when().get("/api/person/" + newPerson.getId()).then()
                .body("id", notNullValue());
        
        given()
                .contentType(ContentType.JSON)
                .when().delete("/api/person/" + newPerson.getId()).then();
    } 
}
