package com.ecommerce.usersService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.usersService.entity.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String>{

}
