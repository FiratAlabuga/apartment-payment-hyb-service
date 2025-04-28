package com.apms.apartmentservice.apartment.model.domain;

import com.apms.apartmentservice.common.model.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "APARTMENT")
public class Apartment extends BaseEntity {
    private String apartmentId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String block;
    private String number;
    private int totalUnits;
    private int availableUnits;

    @Builder.Default
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resident> residents = new ArrayList<>();

}
