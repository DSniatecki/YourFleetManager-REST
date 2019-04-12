package com.dsniatecki.yourfleetmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "c_name")
    private String name;

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="contact_details_id")
    private ContactDetails contactDetails;

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
    private List<Department> departments;

    public void addDepartment(Department department){
        if(this.departments == null) this.departments = new ArrayList<>();
        department.setCompany(this);
        this.departments.add(department);
    }

    public Company(){
        this.address = new Address();
        this.contactDetails = new ContactDetails();
    }

    public Company(String name, ContactDetails contactDetails, Address address) {
        this.address = new Address();
        this.contactDetails = new ContactDetails();
        this.name = name;
        this.contactDetails = contactDetails;
        this.address = address;
    }
}
