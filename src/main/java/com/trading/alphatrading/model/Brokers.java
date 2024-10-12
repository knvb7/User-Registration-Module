package com.trading.alphatrading.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "broker_table")
@Data
public class Brokers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_id")
    private Integer broker_id;

    @Column(name = "name")
    private String name;
}
