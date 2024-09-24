package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.CustomerProfileEntity;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfileEntity, Integer>{

}
