package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ScheduleDAO extends JpaRepository<Schedule, Long> {
    @Query("SELECT e FROM Schedule e JOIN e.employeeIds p WHERE p = :employeeId ")
    List<Schedule> getScheduleByEmployeeId(long employeeId);

    @Query("SELECT e FROM Schedule e JOIN e.petIds p WHERE p = :petId ")
    List<Schedule> getScheduleByForPet(long petId);
    @Query("SELECT e FROM Schedule e JOIN e.customerIds p WHERE p = :customerId ")
    public List<Schedule> getScheduleByCustomerId(long customerId);


}

//    @Query("SELECT e FROM Schedule e JOIN e.petIds")
//    List<Schedule> getAllSchedulePets();


