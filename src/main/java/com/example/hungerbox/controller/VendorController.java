package com.example.hungerbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hungerbox.dto.VendorDto;
import com.example.hungerbox.service.VendorServiceImpl;

/**
 * 
 * @author Divya
 * @since 1.2
 *
 */
@RestController
@RequestMapping("/vendors")
public class VendorController {
	Logger logger = LoggerFactory.getLogger(VendorController.class);
	@Autowired
	VendorServiceImpl venderServiceImpl;

	/**
	 * 
	 * @param vendorName
	 * @return vendorDto
	 */

	@GetMapping("/vendor/{vendorName}")
	public ResponseEntity<VendorDto> searchforVendorName(@RequestParam String vendorName) {
		logger.info("-----vendor information-----");
		VendorDto vendor = venderServiceImpl.searchforVendorName(vendorName);

		return new ResponseEntity<>(vendor, HttpStatus.OK);

	}

}
