package com.craftsmengroup.paymentsystem.authserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ps_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique=true, nullable = false)
    private Long id;
    @Column(name = "client_id")
    private String clientId;
    private String secret;
    
    private String scopes = StringUtils.arrayToCommaDelimitedString(new String[]{"openid"});

    @Column(name = "authorized_grand_types")
    private String authorizedGrandTypes = StringUtils.arrayToCommaDelimitedString(new String[]{"authorization_code", "refresh_token", "password", "code", "implicit"});
    
    private String authorities = StringUtils.arrayToCommaDelimitedString(new String[]{"ROLE_USER", "ROLE_ADMIN"});

    @Column(name = "auto_approve_scopes")
    private String autoApproveScopes = StringUtils.arrayToCommaDelimitedString(new String[]{".*"});

    public Client(String clientId, String secret) {
        this.clientId = clientId;
        this.secret = secret;
    }
}
