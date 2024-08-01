package com.hibernate.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class ElectronicBook extends Book{

    @Column(name = "download_link")
    private String downloadLink;
}
