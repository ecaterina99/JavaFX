package com.internetPackages.DTO;

import com.internetPackages.Model.InternetPackage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternetPackageDTO {
    private int id;
    private String fullName;
    private String address;
    private int speed;
    private int duration;
    private InternetPackage.Bandwidth bandwidth;
}
