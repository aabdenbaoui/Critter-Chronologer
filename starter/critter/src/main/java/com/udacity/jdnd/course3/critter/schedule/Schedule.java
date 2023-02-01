package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SCHEDULE")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CollectionTable(name = "schedule_customer_ids", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "customer_id")
    @ElementCollection
    private List<Long> customerIds;
    @CollectionTable(name = "schedule_employee_ids", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "employee_id")
    @ElementCollection
    private List<Long> employeeIds = new ArrayList<>();
    @ElementCollection
    private List<Long> petIds;
    private LocalDate date;
    @Column
    @Enumerated
    @ElementCollection(targetClass = EmployeeSkill.class)
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Schedule( List<Long> customerIds, List<Long> employeeIds, List<Long> petIds, LocalDate date, Set<EmployeeSkill> activities) {
        this.customerIds = customerIds;
        this.employeeIds = employeeIds;
        this.petIds = petIds;
        this.date = date;
        this.activities = activities;
    }

    public long getId() {
        return id;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", employeeIds=" + employeeIds +
                ", petIds=" + petIds +
                ", date=" + date +
                ", activities=" + activities +
                '}';
    }
}
