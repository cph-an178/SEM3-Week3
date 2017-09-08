/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.google.gson.Gson;
import entity.Person;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class JSONConverter {
    public static Person getPersonFromJson(String js){
        Person p = new Gson().fromJson(js, Person.class);
        return p;
    }  
    public static String getJSONFromPerson(Person p) {
        String json = new Gson().toJson(p);
        return json;
    }  
    public static String getJSONFromPerson(List<Person> persons) {
        String json = new Gson().toJson(persons);
        return json;
    }  
}
