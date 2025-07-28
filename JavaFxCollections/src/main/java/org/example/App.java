package org.example;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Comparator;


/**
 * JavaFX App
 */
public class App {


    public static void main(String[] args) {
      /*  ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4","5");
        list.add("6");
        list.addListener(new ListChangeListener<String>() {
            public void onChanged(Change<? extends String> c) {
                while (c.next()) {
                    if (c.wasPermutated())  {
                        System.out.println("Permuted from: "+c.getFrom()+" to: "+c.getTo());
                    } else if (c.wasRemoved())  {
                        System.out.println("Removed from: "+c.getFrom()+" to: "+c.getTo());

                    } else if (c.wasAdded())  {
                        System.out.println("Added from: "+c.getFrom()+" to: "+c.getTo());

                    } else if (c.wasReplaced())  {
                        System.out.println("Replaced from: "+c.getFrom()+" to: "+c.getTo());
                    }

                }
            }
        });
        list.sort((o1, o2) -> o1.compareTo(o2));
       // list.sort(String::compareTo);
        list.set(0,"6");
        list.removeAll("2","4");
        list.add("22");
        System.out.println(list);

       */
        IntegerProperty p1 = new SimpleIntegerProperty(1);
        IntegerProperty p2 = new SimpleIntegerProperty(2);
        ObservableList<IntegerProperty> list = FXCollections.observableArrayList(param -> new Observable[]{param});
        list.addAll(p1, p2);
        list.addListener(new ListChangeListener<IntegerProperty>() {
            public void onChanged(Change<? extends IntegerProperty> c) {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        System.out.println("Permuted from: " + c.getFrom() + " to: " + c.getTo());
                    } else if (c.wasRemoved()) {
                        System.out.println("Removed from: " + c.getFrom() + " to: " + c.getTo());

                    } else if (c.wasAdded()) {
                        System.out.println("Added from: " + c.getFrom() + " to: " + c.getTo());

                    } else if (c.wasReplaced()) {
                        System.out.println("Replaced from: " + c.getFrom() + " to: " + c.getTo());
                    }

                }
            }
        });

        p2.set(3);
        System.out.println(list);
    }

}