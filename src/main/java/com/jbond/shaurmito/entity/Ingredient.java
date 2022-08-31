package com.jbond.shaurmito.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(schema = "shaverma_schm")
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        SOUSE, WRAP, ADDITIVE
    }
}
