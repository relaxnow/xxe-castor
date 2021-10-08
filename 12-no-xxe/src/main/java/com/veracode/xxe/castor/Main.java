package com.veracode.xxe.castor;

import org.exolab.castor.xml.Unmarshaller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        InputStream fileStream = Main.class.getClassLoader().getResourceAsStream("castor.person.xml");
        Person aPerson = null;
        try
        {
            assert fileStream != null;
            aPerson = (Person) Unmarshaller.unmarshal(Person.class, new InputStreamReader(fileStream));
            logger.log(Level.INFO, "Person name: " + aPerson.getName());
        }
        catch (Exception e)
        {
            System.out.println("Failed to unmarshal the xml");
            e.printStackTrace();
        }
    }
}
