package de.anybytes.springbootschulung.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateToDoDTO {

    private Boolean isDone;
    private String name;
    private String dueDate;


}
