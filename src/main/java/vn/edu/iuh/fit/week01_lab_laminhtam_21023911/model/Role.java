package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long role_id;
    private String role_name;
    private String description;
    private Status status;
}
