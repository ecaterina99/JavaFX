package com.internetPackages.Service;

import com.internetPackages.Model.InternetPackageEntity;
import com.internetPackages.Model.InternetPackageModel;
import com.internetPackages.PackageMapper;
import com.internetPackages.Repository.PackageRepositoryJPA;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService {
    private final PackageRepositoryJPA repository;
    private final PackageMapper mapper;

    public PackageService(PackageRepositoryJPA repository,PackageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<InternetPackageEntity> findAllSales() {
        List<InternetPackageEntity> iterablePackages = repository.findAll();
        return new ArrayList<>(iterablePackages);
    }

    public InternetPackageModel save(InternetPackageModel model) {
        InternetPackageEntity entity = mapper.modelToEntity(model);
        InternetPackageEntity saved = repository.save(entity);
        return mapper.entityToModel(saved);
    }
    public List<InternetPackageModel> findAll() {
        return repository.findAll().stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }
    public void delete(Integer id) {
        repository.deleteById(id);
    }


}
