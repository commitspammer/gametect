package com.gametect.api.enumerations;

public enum UserProjectRoleEnum {
    ADMIN("Administrador", "ADMIN"),
    COLLAB("Colaborador", "COLLAB"),

    SUPERVISOR("Gerente", "SUPERVISOR");

    private String name;
    private String code;

    UserProjectRoleEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
