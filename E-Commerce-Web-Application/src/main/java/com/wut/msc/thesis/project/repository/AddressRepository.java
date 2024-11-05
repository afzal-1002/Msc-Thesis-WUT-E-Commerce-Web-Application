package com.wut.msc.thesis.project.repository;

import com.wut.msc.thesis.project.bean.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
