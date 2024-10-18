package com.lps.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.models.Address;
import com.lps.api.repositories.AddressRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).get();
    }

    public void deleteByUserId(Long id) {
        Address address = addressRepository.findByStudentId(id);
        if (address != null)
            addressRepository.delete(address);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

}
