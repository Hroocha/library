package com.hibernate.library.entities;

import com.hibernate.library.entities.enums.BindingType;
import jakarta.persistence.*;

@Entity
public class PaperBook extends Book{

    @Column(name = "weight")
    private Float weight;

    @Column(name = "binding_type")
    private Enum <BindingType> bindingType;

}
