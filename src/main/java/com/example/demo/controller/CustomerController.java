package com.example.demo.controller;

import com.example.demo.domain.constants.CustomerConstants;
import com.example.demo.domain.dto.Customer;
import com.example.demo.domain.request.CustomerRegistration;
import com.example.demo.domain.request.Form;
import com.example.demo.domain.response.CommonResponseDto;
import com.example.demo.domain.response.RegisteredCustomer;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resources;
import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping(CustomerBaseController.BASE_URL)
public class CustomerController extends CustomerBaseController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Get all Customers
     *
     * @return
     */
    @GetMapping()
    ResponseEntity<CommonResponseDto> getAllCustomer() {
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        RegisteredCustomer registeredCustomer = new RegisteredCustomer();
        commonResponseDto.setStatus(CustomerConstants.SUCCESS);
        registeredCustomer.setCustomer(customerService.findAllCustomers());
        commonResponseDto.setResult(registeredCustomer);
        return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
    }

    /**
     * Get a customer by id
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    ResponseEntity<CommonResponseDto> getCustomerByid(@RequestParam("id") Long id) {
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        RegisteredCustomer registeredCustomer = new RegisteredCustomer();
        commonResponseDto.setStatus(CustomerConstants.SUCCESS);
        registeredCustomer.setCustomer(Arrays.asList(customerService.findCustomerById(id)));
        commonResponseDto.setResult(registeredCustomer);
        return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
    }

    @PostMapping("/registerCustomer")
    ResponseEntity<CommonResponseDto> registercustomer(@Valid @RequestBody Form<CustomerRegistration> form) {
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        RegisteredCustomer registeredCustomer = new RegisteredCustomer();
        commonResponseDto.setStatus(CustomerConstants.SUCCESS);
        registeredCustomer.setCustomer(Arrays.asList(customerService.saveCustomer(form.getForm())));
        commonResponseDto.setResult(registeredCustomer);
        return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    ResponseEntity<CommonResponseDto> updateCustomer(@Valid @RequestBody Form<CustomerRegistration> form){
        CommonResponseDto commonResponseDto = new CommonResponseDto();
        RegisteredCustomer registeredCustomer = new RegisteredCustomer();
        commonResponseDto.setStatus(CustomerConstants.SUCCESS);
        registeredCustomer.setCustomer(Arrays.asList(customerService.updateCustomer(form.getForm())));
        commonResponseDto.setResult(registeredCustomer);
        return new ResponseEntity<>(commonResponseDto, HttpStatus.OK);
    }


}

