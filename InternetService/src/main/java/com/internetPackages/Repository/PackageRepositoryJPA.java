package com.internetPackages.Repository;
import com.internetPackages.Model.InternetPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository layer
 */
@Repository
public interface PackageRepositoryJPA extends JpaRepository<InternetPackageEntity, Integer> {
}

