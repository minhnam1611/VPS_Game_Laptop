package com.example.vps_game_flatform.Entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens",schema = "demo_jwt")
public class Tokens {
    @Id
    private Long iduser;

    @Column(length = 1000)
    private String token;
    @Column(length = 1000)
    private String refreshtoken;
    @Column(name = "token_exp_date")
    private Date tokenExpDate;
    @Column(name = "rf_token_exp_date")
    private Date rfTokenExpDate;
}
