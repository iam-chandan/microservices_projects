package com.ecommerce.usersService.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.usersService.dto.Address;
import com.ecommerce.usersService.dto.Users;
import com.ecommerce.usersService.entity.Role;
import com.ecommerce.usersService.entity.UserAddress;
import com.ecommerce.usersService.entity.UsersEntity;
import com.ecommerce.usersService.repository.RoleRepository;
import com.ecommerce.usersService.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public String createUser(Users users) {
		String userId = UUID.randomUUID().toString();
		UsersEntity user = new UsersEntity();
		user.setUser_id(userId);
		user.setUserName(users.getUserName());
		user.setFirstName(users.getFirstName());
		user.setLastName(users.getLastName());
		user.setEmail(users.getEmail());
		UserAddress  userAddress = mapToUserAddressEntity(users.getAddress());
		user.setUserAddress(userAddress);
		
		Role role = roleRepository.findByRoleName("USER");
		
		user.setRole(role);
		
		UsersEntity userEntity = userRepository.save(user);
		
		return userEntity.getUser_id();
	}

	public UsersEntity getUserById(String userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id : " + userId));
	}

	private UserAddress mapToUserAddressEntity(Address address) {
		UserAddress userAddress = new UserAddress();
		userAddress.setCity(address.getCity());
		userAddress.setCountry(address.getCountry());
		userAddress.setHouseNo(address.getHouseNo());
		userAddress.setPincode(address.getPincode());
		userAddress.setState(address.getState());
		userAddress.setStreet(address.getStreet());
		
		return userAddress;
	}
}
