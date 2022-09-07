package com.jbond.shaurmito.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(schema = "shaverma_schm")
public class Shaverma {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shaverma_seq")
    @SequenceGenerator(name = "shaverma_seq", sequenceName = "shaverma_id_seq", schema = "shaverma_schm")
    private Long id;

    private Date createdAt;

    @PrePersist
    public void createdAt() {
        this.createdAt = new Date();
    }

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(name = "shaverma_ingredients", schema = "shaverma_schm")
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
