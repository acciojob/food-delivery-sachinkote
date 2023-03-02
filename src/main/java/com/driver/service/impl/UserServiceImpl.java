package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;

public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository repo;
	

	@Override
	public UserDto createUser(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		UserEntity u=new UserEntity();
		u.setEmail(user.getEmail());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUserId(user.getUserId());
		
		repo.save(u);
		return user;
		
	}

	@Override
	public UserDto getUser(String email) throws Exception {
		// TODO Auto-generated method stub
		UserEntity u=repo.findByEmail(email);
		UserDto dto=new UserDto();
		dto.setEmail(u.getEmail());
		dto.setFirstName(u.getFirstName());
		dto.setId(u.getId());
		dto.setLastName(u.getLastName());
		dto.setUserId(u.getUserId());
		return dto;
	}

	@Override
	public UserDto getUserByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		UserEntity u=repo.findByUserId(userId);
		UserDto dto=new UserDto();
		dto.setEmail(u.getEmail());
		dto.setFirstName(u.getFirstName());
		dto.setId(u.getId());
		dto.setLastName(u.getLastName());
		dto.setUserId(u.getUserId());
		return dto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) throws Exception {
		// TODO Auto-generated method stub
		UserEntity u=repo.findByUserId(userId);
		u.setEmail(user.getEmail());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUserId(user.getUserId());
		repo.save(u);
		return user;
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		UserEntity u=repo.findByUserId(userId);
		repo.delete(u);
	}

	@Override
	public List<UserDto> getUsers() {
		// TODO Auto-generated method stub
		List<UserEntity> list=(List<UserEntity>) repo.findAll();
		List<UserDto> l=new ArrayList<>();
		for(UserEntity u:list)
		{
			UserDto dto=new UserDto();
			dto.setEmail(u.getEmail());
			dto.setFirstName(u.getFirstName());
			dto.setId(u.getId());
			dto.setLastName(u.getLastName());
			dto.setUserId(u.getUserId());
			l.add(dto);
		}
		return l;
	}
	
}