package com.StajcellProject.Stajcell.API.Project.model;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Task {
    private Long userId;
    private Long id;
    private String title;
    private boolean completed;
}