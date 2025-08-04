package com.internetPackages.Repository;
import com.internetPackages.Model.InternetPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternetPackageRepositoryJPA extends JpaRepository<InternetPackage, Integer> {
}
