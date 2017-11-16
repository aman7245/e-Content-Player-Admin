package com.project.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Login;
import com.project.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired LoginRepository loginrepo;
	
	@Override
	public Login checkLogin(Login login) {
		Login check = loginrepo.findByUsername(login.getUsername());
		if(login.getUsername()==null)
		{
			throw new NullPointerException();
		}
		if(login.getPassword().equals(check.getPassword())){
			System.out.println("Login Successful");
		}
		else{
			throw new EntityNotFoundException();
		}
			
		
		return check;
	}

}
