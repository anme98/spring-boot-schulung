package de.anybytes.springbootschulung;

import de.anybytes.springbootschulung.entity.Role;
import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.entity.User;
import de.anybytes.springbootschulung.repository.RoleRepository;
import de.anybytes.springbootschulung.repository.ToDoRepository;
import de.anybytes.springbootschulung.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DatabaseInitiator implements CommandLineRunner {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInitiator(ToDoRepository toDoRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Init todos
        ToDo todo1 = new ToDo("Eiere kaufen", true);
        ToDo todo2 = new ToDo("Spring Boot lernen", false);
        ToDo todo3 = new ToDo("Sleep", true);
        ToDo todo4 = new ToDo("Repeat", false);
        toDoRepository.saveAll(Arrays.asList(todo1, todo2, todo3, todo4));

        // Init roles
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.saveAll(Arrays.asList(userRole, adminRole));

        // init users
        User admin = new User("admin", (passwordEncoder.encode("admin")));
        admin.addRoles(Arrays.asList(adminRole, userRole));
        User user = new User("user", (passwordEncoder.encode("user")));
        user.addRole(userRole);
        userRepository.saveAll(Arrays.asList(admin, user));
    }
}
