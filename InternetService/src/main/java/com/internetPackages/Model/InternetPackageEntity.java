package com.internetPackages.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
/**
 * DB Entity for Internet Package data.
 */
@Getter
@Setter
@Entity(name = "packages")
public class InternetPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "This field can't be empty")

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "This field can't be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Invalid email format")
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
}