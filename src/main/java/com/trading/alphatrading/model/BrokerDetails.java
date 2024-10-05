package com.trading.alphatrading.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(schema = "broker", name = "BrokerDetails")
@Data
public class BrokerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
