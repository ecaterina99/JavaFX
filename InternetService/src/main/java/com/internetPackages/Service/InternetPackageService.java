package com.internetPackages.Service;

import com.internetPackages.DTO.DTOManager;
import com.internetPackages.DTO.InternetPackageDTO;
import com.internetPackages.Model.InternetPackage;
import com.internetPackages.Repository.InternetPackageRepositoryJPA;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternetPackageService {
    private final InternetPackageRepositoryJPA repository;
    private final DTOManager dtoManager;
    public InternetPackageService(InternetPackageRepositoryJPA repository, DTOManager dtoManager) {
        this.repository = repository;
        this.dtoManager = dtoManager;
    }

    private InternetPackageDTO packageToDto(InternetPackage internetPackage) {
        return  dtoManager.internetPackageToDTO(internetPackage);
    }

    private InternetPackage DTOToInternetPackage (InternetPackageDTO internetPackageDTO) {
        if(internetPackageDTO == null){
            throw new IllegalArgumentException("Internet Package DTO cannot be null");
        }
        InternetPackage internetPackage = new InternetPackage();
        String[] nameParts = internetPackageDTO.getFullName().split(" ");
        internetPackage.setFirstName(nameParts[0]);
        internetPackage.setLastName(nameParts[1]);
        internetPackage.setAddress(internetPackageDTO.getAddress());
        internetPackage.setBandwidth(internetPackageDTO.getBandwidth());
        internetPackage.setDuration(internetPackageDTO.getDuration());
        internetPackage.setSpeed(internetPackageDTO.getSpeed());
        return internetPackage;
    }


    public List<InternetPackageDTO> findAllSales() {
        List<InternetPackageDTO> internetPackageList = new ArrayList<>();
        List<InternetPackage> iterablePackages = repository.findAll();
        for (InternetPackage internetPackage : iterablePackages) {
            InternetPackageDTO internetPackageDTO = packageToDto(internetPackage);
            internetPackageList.add(internetPackageDTO);
        }
        return internetPackageList;
    }

    public InternetPackage save(InternetPackage internetPackage) {
        return repository.save(internetPackage);
    }
    
}
