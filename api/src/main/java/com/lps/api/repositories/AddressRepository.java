package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lps.api.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
