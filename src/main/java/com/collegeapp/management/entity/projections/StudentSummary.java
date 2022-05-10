package com.collegeapp.management.entity.projections;

import java.time.LocalDate;

public interface StudentSummary {
    public void getId();
    public String getFirstName();
    public String getLastName();
    public String getDni();
    public String getEmail();
    public String getTelephone();
    public String getAddress();
    public LocalDate getDob();
    public Integer getAge();
}
