package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long>{
	
	public Package findByPackageName(String packageName);

}
