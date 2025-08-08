package org.github.webhook.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user", schema = "main")
public class User extends PanacheEntity {
    public String name;
    public String email;
    public Number number;
    public String designation;
    public Double latitude;
    public Double longitude;
    
}
