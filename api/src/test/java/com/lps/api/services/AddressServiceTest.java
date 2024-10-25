package com.lps.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lps.api.models.Address;
import com.lps.api.repositories.AddressRepository;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Mock some addresses
        List<Address> addresses = Arrays.asList(new Address(), new Address());

        // Mock the repository call
        when(addressRepository.findAll()).thenReturn(addresses);

        // Call the service method
        List<Address> result = addressService.findAll();

        // Verify results
        assertEquals(2, result.size());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        // Mock an Address
        Address address = new Address();
        address.setId(1L);

        // Mock the repository call
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        // Call the service method
        Address result = addressService.findById(1L);

        // Verify results
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Mock the repository call to return empty
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        // This will throw an exception since we're using `.get()` in the service
        assertThrows(java.util.NoSuchElementException.class, () -> {
            addressService.findById(1L);
        });

        // Verify repository was called
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        // Mock an Address
        Address address = new Address();
        address.setId(1L);

        // Mock the repository save call
        when(addressRepository.save(address)).thenReturn(address);

        // Call the service method
        Address result = addressService.save(address);

        // Verify results
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(addressRepository, times(1)).save(address);
    }
}
