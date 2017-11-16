package com.project.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Package;
import com.project.repository.PackageRepository;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired PackageRepository packagerepo;

	@Override
	public Package createPackage(@Valid Package packages) {
		Date date = DateUtils.addDays(packages.getValidFrom(), packages.getValidTill());
		packages.setExpiresOn(date);
		List<Package> packagess = packagerepo.findAll();
		for(Package packs : packagess){
			if(packs.getPackageName().equals(packages.getPackageName())){
				System.out.println("package alresdy Exist");
				throw new EntityExistsException();
			}
		}
		if((packages.getPackageName()=="") || (packages.getPackageId()=="") || (packages.getValidTill()==0)) {
			throw new NullPointerException();
		}
		packagerepo.save(packages);
		return packages;
	}

	@Override
	public List<Package> findAllPackages() {
		List<Package> packages = packagerepo.findAll();
		return packages;
	}

	@Override
	public Package findByName(String packageName) {
		Package packages = packagerepo.findByPackageName(packageName);
		return packages;
	}

	@Override
	public Package deletePackage(Long id) {
		Package packages = packagerepo.findOne(id);
		packagerepo.delete(id);
		return packages;
	}

	@Override
	public Package findByPackageId(Long id) {
		Package packages = packagerepo.findOne(id);
		return packages;
	}

	@Override
	public Package updatePackage(Package packages) {
		Date date = DateUtils.addDays(packages.getValidFrom(), packages.getValidTill());
		packages.setExpiresOn(date);
		packagerepo.save(packages);
		return packages;
	}

}
