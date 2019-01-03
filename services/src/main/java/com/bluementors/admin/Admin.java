package com.bluementors.admin;

import com.bluementors.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ADMINS")
public class Admin {
    @Id
    private Long id;

    @OneToOne
    @NotNull
    private User user;
}
