package com.internetPackages.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "speed", nullable = false)
    private int speed;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "Bandwidth", nullable = false)
    @Enumerated(EnumType.STRING)
    private Bandwidth bandwidth;


    public enum Bandwidth {
        one("1"),
        five("5"),
        ten("10"),
        hundred("100"),
        flat("flat");

        private final String value;

        Bandwidth(String value) {
            this.value = value;
        }

        public static Bandwidth fromString(String value) {
            if (value == null || value.trim().isEmpty()) {
                return null;
            }

            String normalizedInput = value.trim().toLowerCase();
            for (Bandwidth b : Bandwidth.values()) {
                if (b.value.equalsIgnoreCase(normalizedInput)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Invalid speed: " + value +
                    ". Valid values are: 1, 5, 10, 100, flat");
        }
    }
}