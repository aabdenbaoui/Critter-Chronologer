package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    @Query("SELECT e FROM Customer e JOIN e.petIds p WHERE p = :petId ")
    Customer getCustomerByPet(long petId);
}
