package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dao.CustomerDAO;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    public Customer saveCustomer(Customer customer){
        customerDAO.save(customer);
        return customer;
    }
    public List<CustomerDTO> getAllCustomers(){
        return customerDAO.findAll().stream()
                .map(c -> new CustomerDTO(c.getId(), c.getName(), c.getPhoneNumber(), c.getNotes(), c.getPetIds()))
                .collect(Collectors.toList());
    }


    public Customer getCustomerById(long ownerId) {
        Customer customer = customerDAO.findById(ownerId).get();
        return customer;
    }
    public CustomerDTO convertCustomerToDTO(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getPhoneNumber(), customer.getNotes(), customer.getPetIds());
    }

    public Customer getCustomerByPetId(long id){
        System.out.println(customerDAO.getCustomerByPet(id));
        return customerDAO.getCustomerByPet(id);
    }
}
