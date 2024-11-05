package com.wut.msc.thesis.project.bean;

import com.wut.msc.thesis.project.enums.AddressType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Address {

        @Id
        @GeneratedValue(strategy =GenerationType.IDENTITY)
        @Column(name = "address_id")
        private int addressId;
        private String street;
        private String city;
        private String state;
        private String zip;
        private String country;

        @Enumerated(EnumType.STRING)
        private AddressType addressType;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Address address = (Address) o;
                return addressId == address.addressId && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(zip, address.zip) && Objects.equals(country, address.country) && addressType == address.addressType;
        }

        @Override
        public int hashCode() {
                return Objects.hash(addressId, street, city, state, zip, country, addressType);
        }
}
