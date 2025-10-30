package com.internetPackages.Service;

import com.internetPackages.Exception.PackageServiceException;
import com.internetPackages.Model.InternetPackageEntity;
import com.internetPackages.Model.InternetPackageModel;
import com.internetPackages.Configuration.PackageMapper;
import com.internetPackages.Repository.PackageRepositoryJPA;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Service layer for Internet Package business operations.
 * Handles CRUD operations and business logic validation.
 */

@Service
@Transactional
@Slf4j
public class PackageService {
    private final PackageRepositoryJPA repository;
    private final PackageMapper mapper;
    private final jakarta.validation.Validator validator;

    public PackageService(PackageRepositoryJPA repository,
                          PackageMapper mapper,
                          jakarta.validation.Validator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    public InternetPackageModel save(InternetPackageModel model) {
        log.debug("Saving package for: {} {}", model.getFirstName(), model.getLastName());
        Set<ConstraintViolation<InternetPackageModel>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));
            log.error("Validation failed: {}", errors);
            throw new PackageServiceException("Validation failed: " + errors);
        }

        try {
            InternetPackageEntity entity = mapper.modelToEntity(model);

            Set<ConstraintViolation<InternetPackageEntity>> entityViolations =
                    validator.validate(entity);
            if (!entityViolations.isEmpty()) {
                String errors = entityViolations.stream()
                        .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                        .collect(Collectors.joining(", "));
                log.error("Entity validation failed: {}", errors);
                throw new PackageServiceException("Validation failed: " + errors);
            }

            InternetPackageEntity saved = repository.save(entity);
            InternetPackageModel result = mapper.entityToModel(saved);
            log.info("Package saved successfully with id: {}", result.getId());
            return result;
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation while saving package", e);
            throw new PackageServiceException("Failed to save package: duplicate entry or constraint violation", e);
        } catch (Exception e) {
            log.error("Unexpected error while saving package", e);
            throw new PackageServiceException("Failed to save package", e);
        }
    }


    @Transactional(readOnly = true)
    public List<InternetPackageModel> findAll() {
        log.debug("Finding all packages");
        try {
            List<InternetPackageModel> packages = repository.findAll().stream()
                    .map(mapper::entityToModel)
                    .collect(Collectors.toList());
            log.info("Found {} packages", packages.size());
            return packages;
        } catch (Exception e) {
            log.error("Error while loading packages", e);
            throw new PackageServiceException("Failed to load packages", e);
        }
    }

    public void delete(Integer id) {
        log.debug("Deleting package with id: {}", id);
        try {
            if (!repository.existsById(id)) {
                log.warn("Package with id {} not found", id);
                throw new EntityNotFoundException("Package with id " + id + " not found");
            }
            repository.deleteById(id);
            log.info("Package with id {} deleted successfully", id);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while deleting package with id: {}", id, e);
            throw new PackageServiceException("Failed to delete package with id " + id, e);
        }
    }
}
