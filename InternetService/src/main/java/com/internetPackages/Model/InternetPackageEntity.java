package com.internetPackages.Model;

import jakarta.persistence.*;


@Entity(name = "packages")
public class InternetPackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "speed",nullable = false)
    private String speed;

    @Column(name = "duration",nullable = false)
    private String duration;

    @Column(name = "bandwidth",nullable = false)
    private String bandwidth;

    public InternetPackageEntity() {
    }

    public InternetPackageEntity(String firstName, String lastName, String address,
                                 String speed, String bandwidth, String duration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.speed = speed;
        this.bandwidth = bandwidth;
        this.duration = duration;
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