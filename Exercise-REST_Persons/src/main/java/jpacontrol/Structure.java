package jpacontrol;

import java.util.HashMap;
import javax.persistence.Persistence;

public class Structure {
    public static void main(String[] args) {
        
        HashMap purproperties = new HashMap();
        
        purproperties.put("javax.persistence.sql-load-script-source", "META-INF/createData.sql");
        Persistence.generateSchema("jpaPU", purproperties);
        
        purproperties.remove("javax.persistence.sql-load-script-source");
        Persistence.generateSchema("jpaPU", purproperties);
    }
}