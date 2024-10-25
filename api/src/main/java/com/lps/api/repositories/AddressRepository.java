package com.lps.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lps.api.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
