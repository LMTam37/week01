package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Long log_id;
    private Long account_id;
    private Date login_date;
    private Date logout_date;
    private String description;
}
