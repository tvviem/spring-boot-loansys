package vn.blu.tvviem.loansys.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class AuthRequestInfo implements Serializable {
    private String username;
    private String password;
}
