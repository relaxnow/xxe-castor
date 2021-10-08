package com.veracode.xxe.castor;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;

    public Person() {
        super();
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
