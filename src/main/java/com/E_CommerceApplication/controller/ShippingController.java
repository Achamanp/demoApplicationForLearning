package com.E_CommerceApplication.controller;

import com.E_CommerceApplication.payloads.ApiResponse;
import com.E_CommerceApplication.payloads.ShippingDto;
import com.E_CommerceApplication.impl.ShippingServiceImpl;
import com.E_CommerceApplication.pagination.ShippingPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippings")
public class ShippingController {

    @Autowired
    private ShippingServiceImpl shippingServiceImpl;

    @PostMapping("/{orderId}")
    public ResponseEntity<ShippingDto> createShipping(@RequestBody ShippingDto shippingDto,
                                                      @PathVariable Integer orderId) {
        ShippingDto createdShipping = shippingServiceImpl.createShipping(shippingDto, orderId);
        return new ResponseEntity<>(createdShipping, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingDto> updateShipping(@RequestBody ShippingDto shippingDto,
                                                      @PathVariable Integer id) {
        ShippingDto updatedShipping = shippingServiceImpl.updateShipping(shippingDto, id);
        return new ResponseEntity<>(updatedShipping, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingDto> getShippingById(@PathVariable Integer id) {
        ShippingDto shippingDto = shippingServiceImpl.getShippingById(id);
        return new ResponseEntity<>(shippingDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteShipping(@PathVariable Integer id) {
        shippingServiceImpl.deleteShipping(id);
        return new ResponseEntity<>(new ApiResponse("Shipping deleted successfully", true),HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<ShippingPageResponse> getAllShippings(
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDir", required = false) String sortDir) {

        // Set default values if parameters are not provided
        pageSize = (pageSize != null) ? pageSize : 10;
        pageNumber = (pageNumber != null) ? pageNumber : 0;
        sortBy = (sortBy != null) ? sortBy : "id";
        sortDir = (sortDir != null) ? sortDir : "asc";

        ShippingPageResponse response = shippingServiceImpl.getAllShippings(pageSize, pageNumber, sortBy, sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ShippingPageResponse> getShippingByOrderId(
            @PathVariable Integer orderId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDir", required = false) String sortDir) {

        // Set default values if parameters are not provided
        pageSize = (pageSize != null) ? pageSize : 10;
        pageNumber = (pageNumber != null) ? pageNumber : 0;
        sortBy = (sortBy != null) ? sortBy : "id";
        sortDir = (sortDir != null) ? sortDir : "asc";

        ShippingPageResponse response = shippingServiceImpl.getShippingByOrderId(orderId, pageSize, pageNumber, sortBy, sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
