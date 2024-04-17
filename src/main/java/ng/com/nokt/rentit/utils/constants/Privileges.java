package ng.com.nokt.rentit.utils.constants;

import lombok.Getter;

@Getter
public enum Privileges {
    RESET_ANY_USER_PASSWORD(1L, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2L, "ACCESS_ADMIN_PANEL");

    private final Long id;
    private final String privilege;

    Privileges(Long id, String privilege) {
        this.id = id;
        this.privilege = privilege;
    }

}
