package com.internetPackages;

import com.internetPackages.Model.InternetPackageEntity;
import com.internetPackages.Model.InternetPackageModel;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public InternetPackageModel entityToModel(InternetPackageEntity entity) {

        InternetPackageModel internetPackageModel = new InternetPackageModel();
        internetPackageModel.setFirstName(entity.getFirstName());
        internetPackageModel.setLastName(entity.getLastName());
        internetPackageModel.setAddress(entity.getAddress());
        internetPackageModel.setBandwidth(entity.getBandwidth());
        internetPackageModel.setSpeed(entity.getSpeed());
        internetPackageModel.setDuration(entity.getDuration());
        return internetPackageModel;
    }

    public InternetPackageEntity modelToEntity(InternetPackageModel model) {
        InternetPackageEntity internetPackageEntity = new InternetPackageEntity();
        internetPackageEntity.setFirstName(model.getFirstName());
        internetPackageEntity.setLastName(model.getLastName());
        internetPackageEntity.setAddress(model.getAddress());
        internetPackageEntity.setDuration(model.getDuration());
        internetPackageEntity.setSpeed(model.getSpeed());
        internetPackageEntity.setBandwidth(model.getBandwidth());
        return internetPackageEntity;
    }

}
