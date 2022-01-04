package com.example.domainservice.model;

import lombok.Data;

@Data
public class Domain {
    private String domain;
    private String create_date;
    private String update_date;
    private String country;
    private boolean isDead;
    private String A;
    private String NS;
    private String CNAME;
    private String MX;
    private String TXT;
}
