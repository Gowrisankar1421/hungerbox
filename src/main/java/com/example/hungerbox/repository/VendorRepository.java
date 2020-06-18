package com.example.hungerbox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hungerbox.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
	Optional<Vendor> findByVendorName(String vendorName);
}
