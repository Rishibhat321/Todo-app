package com.springboot.cruddemo.repository;

import com.springboot.cruddemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
