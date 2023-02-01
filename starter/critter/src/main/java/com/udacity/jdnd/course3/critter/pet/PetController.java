package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.exceptions.PetErrorResponse;
import com.udacity.jdnd.course3.critter.exceptions.PetNotFoundException;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PetService petService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO)  {
        Pet pet = petService.savePet(new Pet(petDTO.getType(), petDTO.getName(), petDTO.getOwnerId(), petDTO.getBirthDate(), petDTO.getNotes(), false));
        return new PetDTO(pet.getId(), pet.getType(), pet.getName(), pet.getOwnerId(), pet.getBirthDate(), pet.getNotes());
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petService.getPetById(petId);
    }
    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAllPets().stream()
                .map(pet -> new PetDTO(pet.getId(), pet.getType(), pet.getName(), pet.getOwnerId(), pet.getBirthDate(),pet.getNotes()))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getAllPets().stream()
                .map(pet -> new PetDTO(pet.getId(), pet.getType(), pet.getName(), pet.getOwnerId(), pet.getBirthDate(),pet.getNotes()))
                .filter(p -> p.getOwnerId() == ownerId)
                .collect(Collectors.toList());
    }
    @ExceptionHandler
    public ResponseEntity<PetErrorResponse> handleException(PetNotFoundException ex){
            PetErrorResponse error  = new PetErrorResponse();
            error.setMessage(ex.getMessage());
            error.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
