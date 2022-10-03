package com.nhom13.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom13.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Boolean existsByPhone(String phone);
	
	Optional<User> findByEmail(String email);
	
	@Query(value = "select * from user where username = :username and roles = :role", nativeQuery = true)
	public User findByUsernameWithRole(@Param("username") String username, @Param("role") String role);
	
	@Query(value = "update user set user.enabled = true where user.id = :id", nativeQuery = true)
	@Modifying
	public void enable (@Param("id") Long id);
	
	@Query(value = "select * from user where verification_code = :verify", nativeQuery = true)
	public User findByVerifyCode(@Param("verify") String verify);
}
