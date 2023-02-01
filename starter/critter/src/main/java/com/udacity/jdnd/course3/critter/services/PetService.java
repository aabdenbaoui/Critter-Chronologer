package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dao.PetDAO;
import com.udacity.jdnd.course3.critter.exceptions.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    PetDAO petDAO;

    @Autowired
    CustomerService customerService;
    public Pet savePet(Pet pet){
        petDAO.save(pet);
        Customer customer = customerService.getCustomerById(pet.getOwnerId());
        List<Long> petsIds =  new ArrayList<>();
        petsIds.add(pet.getId());
        customer.setPetIds(petsIds);
        customerService.saveCustomer(customer);
        return pet;
    }
    public List<Pet> getAllPets(){
       return petDAO.findAll();
    }

    public PetDTO getPetById(long petId) {
        List<PetDTO> petsDTO = petDAO.findAll().stream()
                .map(pet -> new PetDTO(pet.getId(), pet.getType(), pet.getName(), pet.getOwnerId(), pet.getBirthDate(), pet.getNotes()))
                .filter(p -> p.getId() == petId)
                .collect(Collectors.toList());
        if (petsDTO.size() == 0) {
            System.out.println("Pet was not found");
            throw new PetNotFoundException("Pet with id "+ petId + " was not found");
        } else {
            return petsDTO.get(0);
        }
    }
}

