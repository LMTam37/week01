package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long accoun_id;
    private String fullname;
    private String password;
    private String email;
    private String phone;
    private Status status;
}
