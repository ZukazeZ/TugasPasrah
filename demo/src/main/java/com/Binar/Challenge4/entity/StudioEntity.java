package com.Binar.Challenge4.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "nt_Studio")
public class StudioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Studio_ID")
    private long studio_id;
    @Column(name = "Studio_Name")
    private String studio_name;
}
