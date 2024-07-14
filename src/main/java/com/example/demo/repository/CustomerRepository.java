package com.example.demo.repository;

import com.example.demo.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "SELECT Distinct c FROM Customer c  JOIN fetch c.address a WHERE a.city = :value")
    public List<Customer> findByParameterCity(String value);

    @Query(value = "SELECT Distinct c FROM Customer c  JOIN fetch c.address a WHERE a.state = :value")
    public List<Customer> findByParameterState(String value);

}

