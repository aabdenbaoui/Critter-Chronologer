package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.dao.CustomerDAO;
import com.udacity.jdnd.course3.critter.dao.ScheduleDAO;
import com.udacity.jdnd.course3.critter.exceptions.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
   @Autowired
    CustomerDAO customerDAO;
    @Autowired
    ScheduleDAO scheduleDAO;

    public Schedule saveSchedule(Schedule schedule){
         scheduleDAO.save(schedule);
        return schedule;
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleDAO.findAll().stream()
                .map(s -> new ScheduleDTO(s.getEmployeeIds(), s.getPetIds(), s.getDate(), s.getActivities()))
                .collect(Collectors.toList());
    }

    public List<Schedule> getScheduleByEmployeeId(long employeeId) {
        return scheduleDAO.getScheduleByEmployeeId(employeeId);
    }
    private ScheduleDTO convertScheduleToDTO(Schedule schedule){
        return new ScheduleDTO(schedule.getId(), schedule.getEmployeeIds(), schedule.getPetIds(), schedule.getDate(), schedule.getActivities());
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleDAO.getScheduleByForPet(petId);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleDAO.getScheduleByCustomerId(customerId);
    }




}
