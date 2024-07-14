package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First Name cant be empty")
    String firstName;

    @NotBlank(message = "Last Name cant be empty")
    String lastName;

    String customerId;

    @Min(0)
    BigDecimal spendingLimit;

    @Size(min = 10, max = 10, message = "Mobile Number should be of length 10")
    String mobileNumber;

    @Valid
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Address> address=new ArrayList<>();




    public void addAddress(Address address) {
        address.setCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", spendingLimit=" + spendingLimit +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", addressList=" + address +
                '}';
    }


}



