package com.StajcellProject.Stajcell.API.Project.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Task {
    private Long userId;
    private Long id;
    private String title;
    private boolean completed;
}