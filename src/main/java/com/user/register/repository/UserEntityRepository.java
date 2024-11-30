package com.user.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.register.data.UserEntity;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findFirstByEmail(String email);

	
}
