package com.bluementors.training;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SKILLS")
public class Skill {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String descriptions;

    public Skill(){
    }

    public Skill(String name){
        this.name = name;
    }

    public Skill(String name, String descriptions){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}
