package com.internetPackages.Service;

import com.internetPackages.Model.InternetPackageEntity;
import com.internetPackages.Model.InternetPackageModel;
import com.internetPackages.Configuration.PackageMapper;
import com.internetPackages.Repository.PackageRepositoryJPA;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Service layer for Internet Package business operations.
 * Handles CRUD operations and business logic validation.
 */
@Service
public class PackageService {
    private final PackageRepositoryJPA repository;
    private final PackageMapper mapper;

    public PackageService(PackageRepositoryJPA repository,PackageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(InternetPackageModel model) {
        InternetPackageEntity entity = mapper.modelToEntity(model);
        InternetPackageEntity saved = repository.save(entity);
        mapper.entityToModel(saved);
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
