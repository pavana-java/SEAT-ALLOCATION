package com.tataelxsi.ott.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private int empId;

    private String empName;

    private String emailId;

    private String password;

    @OneToMany(targetEntity = Seat.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id", referencedColumnName = "empId")
    private List<Seat> seat;
}


