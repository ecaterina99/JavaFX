package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final IntegerProperty age = new SimpleIntegerProperty(this, "age", 0);
  /*  private IntegerProperty age;
    int _age;
   */
    public Person(String firstName, String lastName, int age) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.age.set(age);
    }

    public Person() {
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public int getAge() {
      //  return (age == null) ? (_age) : (age.get());
        return age.get();
    }

    public IntegerProperty ageProperty() {
   /*     if (age == null) {
            age = new SimpleIntegerProperty(this, "age", 0);
        }
        return age;
    */
        return age;
    }


    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setAge(int age) {
        //half lazy
       /* if (age != null || !(_age == newAge) {
            ageProperty().set(newAge);
        }
        */
        //full lazy
      /*  if (age == null) {
            _age = newAge;
        } else {
            age.set(newAge);
        }
       */
        this.age.set(age);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
}

