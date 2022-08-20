package cinema.config;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:credentials.properties")
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final Environment environment;
    private final AuthenticationService authenticationService;

    public DataInitializer(RoleService roleService, UserService userService,
                           Environment environment, AuthenticationService authenticationService) {
        this.roleService = roleService;
        this.userService = userService;
        this.environment = environment;
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);

        User admin = new User();
        admin.setEmail(environment.getProperty("admin.email"));
        admin.setPassword(environment.getProperty("admin.password"));
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);

        authenticationService.register(environment.getProperty("user.email"),
                environment.getProperty("user.password"));
    }
}
