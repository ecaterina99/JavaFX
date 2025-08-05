package com.internetPackages;

import com.internetPackages.Model.InternetPackageEntity;
import com.internetPackages.Model.InternetPackageModel;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public InternetPackageModel entityToModel(InternetPackageEntity entity) {

        InternetPackageModel model = new InternetPackageModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setAddress(entity.getAddress());
        model.setBandwidth(entity.getBandwidth());
        model.setSpeed(entity.getSpeed());
        model.setDuration(entity.getDuration());
        return model;
    }

    public InternetPackageEntity modelToEntity(InternetPackageModel model) {
        InternetPackageEntity entity = new InternetPackageEntity();
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setAddress(model.getAddress());
        entity.setDuration(model.getDuration());
        entity.setSpeed(model.getSpeed());
        entity.setBandwidth(model.getBandwidth());
        return entity;
    }

}
