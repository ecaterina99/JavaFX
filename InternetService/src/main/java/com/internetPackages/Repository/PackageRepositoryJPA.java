package com.internetPackages.Repository;
import com.internetPackages.Model.InternetPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepositoryJPA extends JpaRepository<InternetPackageEntity, Integer> {
}
