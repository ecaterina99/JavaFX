package com.internetPackages.DTO;

import com.internetPackages.Model.InternetPackage;
import org.springframework.stereotype.Component;

@Component
public class DTOManager {


    public PackageDTO InternetPackageToDTO(InternetPackage internetPackage) {
        PackageDTO packageDTO = new PackageDTO();

        packageDTO.setId(internetPackage.getId());
        packageDTO.setFullName(internetPackage.getFirstName()+" "+internetPackage.getLastName());
        packageDTO.setAddress(internetPackage.getAddress());
        packageDTO.setSpeed(internetPackage.getSpeed());
        packageDTO.setDuration(internetPackage.getDuration());
        packageDTO.setBandwidth(internetPackage.getBandwidth());
        return packageDTO;
    }
}
