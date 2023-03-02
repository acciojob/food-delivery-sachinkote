package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
     
	@Autowired
	UserService service;
	
	
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
      UserDto dto=service.getUserByUserId(id);
      UserResponse reponse=new UserResponse();
      reponse.setEmail(dto.getEmail());
      reponse.setFirstName(dto.getFirstName());
      reponse.setLastName(dto.getLastName());
      reponse.setUserId(dto.getUserId());
     
		return reponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
        UserDto dto=new UserDto();
        dto.setEmail(userDetails.getEmail());
        dto.setFirstName(userDetails.getFirstName());
        dto.setLastName(userDetails.getLastName());
        UserDto dto1=service.createUser(dto);
        UserResponse res=new UserResponse();
        res.setEmail(dto1.getEmail());
        res.setFirstName(dto1.getFirstName());
        res.setLastName(dto1.getLastName());
        res.setUserId(dto1.getUserId());
       
		return res;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
        UserDto dto=new UserDto();
        dto.setEmail(userDetails.getEmail());
        dto.setFirstName(userDetails.getFirstName());
        dto.setLastName(userDetails.getLastName());
        dto.setUserId(id);
       UserDto dto1=service.updateUser(id, dto);
       UserResponse res=new UserResponse();
       res.setEmail(dto1.getEmail());
       res.setFirstName(dto1.getFirstName());
       res.setLastName(dto1.getLastName());
       res.setUserId(dto1.getUserId());
		return res;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
        service.deleteUser(id);
        OperationStatusModel model=new OperationStatusModel();
        model.setOperationName("DELETE");
        model.setOperationResult("SUCCESS");
        return model;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserResponse>l=new ArrayList<>();
		List<UserDto> list=service.getUsers();
		for(UserDto dto1:list)
		{
			UserResponse res=new UserResponse();
		       res.setEmail(dto1.getEmail());
		       res.setFirstName(dto1.getFirstName());
		       res.setLastName(dto1.getLastName());
		       res.setUserId(dto1.getUserId());
		       l.add(res);
		}
		return l;
	}
	
}
