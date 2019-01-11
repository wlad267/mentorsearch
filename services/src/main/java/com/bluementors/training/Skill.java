package com.bluementors.training;

import javax.persistence.*;

@Entity
@Table(name = "SKILLS")
@SequenceGenerator(name="skill_seq", initialValue=10, allocationSize=1000000)
public class Skill {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill_seq")
    private Long id;
    private String name;
    private String description;
    private boolean active = true;

    public Skill(){
    }

    public Skill(String name){
        this.name = name;
    }

    public Skill(String name, String description){
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
