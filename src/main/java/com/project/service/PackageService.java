package com.project.service;

import java.util.List;

import com.project.entity.Package;

public interface PackageService {
	
	public Package createPackage(Package packages);
	public List<Package> findAllPackages();
	public Package findByName(String packageName);
	public Package deletePackage(Long id);
	public Package findByPackageId(Long id);
	public Package updatePackage(Package packages);

}
