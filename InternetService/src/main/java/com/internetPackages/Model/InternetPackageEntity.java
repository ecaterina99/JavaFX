package com.internetPackages.Model;

import jakarta.persistence.*;


@Entity(name = "packages")
public class InternetPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "speed", nullable = false, columnDefinition = "ENUM('2','5','10','20','50','100')")
    private String speed;

    @Column(name = "duration", nullable = false, columnDefinition = "ENUM('1 year', '2 years')")
    private String duration;

    @Column(name = "bandwidth", nullable = false, columnDefinition = "ENUM('1','5','10','100','Flat')")
    private String bandwidth;

    public InternetPackageEntity() {
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}