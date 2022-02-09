package de.anybytes.springbootschulung.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateToDoDTO {
    private Long id;
    private Boolean isDone;
    private String name;
    private String dueDate;
}
