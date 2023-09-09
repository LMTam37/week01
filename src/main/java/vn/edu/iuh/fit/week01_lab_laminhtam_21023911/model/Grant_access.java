package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grant_access    {
    private Long account_id;
    private Long role_id;
    private boolean is_grant;
    private String note;
}
