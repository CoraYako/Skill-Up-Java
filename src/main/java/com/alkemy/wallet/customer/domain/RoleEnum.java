package com.alkemy.wallet.customer.domain;

public enum RoleEnum {
     ADMIN, USER;

     private static final String PREFIX = "ROLE_";

     public String getFullRoleName() {
          return PREFIX + this.name();
     }

     public String getSimpleRoleName() {
          return this.name();
     }
}
