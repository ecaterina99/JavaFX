package com.internetPackages.DTO;

import com.internetPackages.Model.InternetPackage;
import org.springframework.stereotype.Component;

@Component
public class DTOManager {


    public InternetPackageDTO internetPackageToDTO(InternetPackage internetPackage) {
        InternetPackageDTO internetPackageDTO = new InternetPackageDTO();

        internetPackageDTO.setId(internetPackage.getId());
        internetPackageDTO.setFullName(internetPackage.getFirstName()+" "+internetPackage.getLastName());
        internetPackageDTO.setAddress(internetPackage.getAddress());
        internetPackageDTO.setSpeed(internetPackage.getSpeed());
        internetPackageDTO.setDuration(internetPackage.getDuration());
        internetPackageDTO.setBandwidth(internetPackage.getBandwidth());
        return internetPackageDTO;
    }
}
