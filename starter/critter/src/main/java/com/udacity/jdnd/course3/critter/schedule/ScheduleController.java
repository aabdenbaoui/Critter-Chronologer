package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        List<Long> customerIds = new ArrayList<>();
        for(Long id : scheduleDTO.getPetIds()){
            if(customerService.getCustomerByPetId(id) != null){
                customerIds.add(customerService.getCustomerByPetId(id).getId());
            }
        }
        Schedule schedule = scheduleService.saveSchedule(new Schedule(customerIds,scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds(), scheduleDTO.getDate(), scheduleDTO.getActivities()));
        return new ScheduleDTO(schedule.getId(), schedule.getEmployeeIds(), schedule.getPetIds(), schedule.getDate(), schedule.getActivities());

    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
                return scheduleService.getScheduleForPet(petId).stream()
                .map(s -> new ScheduleDTO(s.getEmployeeIds(), s.getPetIds(), s.getDate(), s.getActivities()))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
                return scheduleService.getScheduleByEmployeeId(employeeId).stream()
                .map(s -> new ScheduleDTO(s.getEmployeeIds(), s.getPetIds(), s.getDate(), s.getActivities()))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId).stream()
                .map(s -> new ScheduleDTO(s.getEmployeeIds(), s.getPetIds(), s.getDate(), s.getActivities()))
                .collect(Collectors.toList());
    }
}
