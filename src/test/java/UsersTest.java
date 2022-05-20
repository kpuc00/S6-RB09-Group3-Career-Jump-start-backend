import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.repository.UserRepository;
import com.bezkoder.spring.login.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private RoleRepository roleRepository;


    @Test
    void serviceCreated(){
        assertThat(service).isNotNull();
    }

    @Test
    void findById() throws ParseException {
        //GIVEN
        User user = createAdminUser();
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        //WHEN
        Optional<User> existingUser = service.findById(user.getId());
        //THEN
        assertThat(existingUser).isEqualTo(Optional.of(user));
        //assertThat(existingUser).isEqualTo(null);
    }

    @Test
    void findByRoleName() throws ParseException {
        //GIVEN
        Role role = createRole();
        when(roleRepository.findByName(role.getName())).thenReturn(Optional.of(role));
        //WHEN
        Optional<Role> existingRole = service.findRoleByName(role.getName());
        //THEN
        assertThat(existingRole).isEqualTo(Optional.of(role));
    }

    @Test
    void findByRoleSet() throws ParseException{
        Set<Role> roles = new HashSet<>();
        Role role = createRole();
        roles.add(role);

        assertThat(roles).isEqualTo(Set.of(role));
    }

    @Test
    void findCandidates() throws ParseException {
        //GIVEN
        Set<Role> roles = service.roleToSet(ERole.ROLE_CANDIDATE);
        User user = createUser(ERole.ROLE_CANDIDATE);
        when(repository.findAllByRolesIn(roles)).thenReturn(List.of(user));
        //WHEN
        List<User> existingUsers = service.findCandidates();
        //THEN
        assertThat(existingUsers).isEqualTo(List.of(user));

    }

    @Test
    void findCompanies() throws ParseException{
        //GIVEN
        Set<Role> roles = service.roleToSet(ERole.ROLE_COMPANY);
        User user = createUser(ERole.ROLE_COMPANY);
        when(repository.findAllByRolesIn(roles)).thenReturn(List.of(user));
        //WHEN
        List<User> existingUsers = service.findCandidates();
        //THEN
        assertThat(existingUsers).isEqualTo(List.of(user));
    }

    @Test
    void deleteUser() throws ParseException {
        User user = createUser(ERole.ROLE_COMPANY);
        service.deleteUser(user.getId());
        verify(repository).deleteById(user.getId());
    }


    private User createAdminUser() throws ParseException{

        List<Role> roles = new ArrayList<>();
        Role role = new Role(ERole.ROLE_ADMIN);

        String email = "Regi3@gmail.com";
        String password = "Regipassword";
        String username = "regi3";
        String firstName = "Regi";
        String lastName = "Nald";
        String phoneNumber = "234762764";
        Date dob = null;
        roles.add(role);

        User user = new User(username, firstName, lastName, phoneNumber, dob, email, password);

        return user;

    }

    private User createCompanyUser() throws ParseException{

        List<Role> roles = new ArrayList<>();
        Role role = new Role(ERole.ROLE_COMPANY);

        String email = "Regi3@gmail.com";
        String password = "Regipassword";
        String username = "regi3";
        String firstName = "Regi";
        String lastName = "Nald";
        String phoneNumber = "234762764";
        Date dob = null;
        roles.add(role);

        User user = new User(username, firstName, lastName, phoneNumber, dob, email, password);

        return user;

    }

    private User createCandidateUser() throws ParseException{

        List<Role> roles = new ArrayList<>();
        Role role = new Role(ERole.ROLE_CANDIDATE);

        String email = "Regi3@gmail.com";
        String password = "Regipassword";
        String username = "regi3";
        String firstName = "Regi";
        String lastName = "Nald";
        String phoneNumber = "234762764";
        Date dob = null;
        roles.add(role);

        User user = new User(username, firstName, lastName, phoneNumber, dob, email, password);

        return user;

    }

    private User createUser(ERole eRole) throws ParseException{

        List<Role> roles = new ArrayList<>();
        Role role = new Role(eRole);

        String email = "Regi3@gmail.com";
        String password = "Regipassword";
        String username = "regi3";
        String firstName = "Regi";
        String lastName = "Nald";
        String phoneNumber = "234762764";
        Date dob = null;
        roles.add(role);

        User user = new User(username, firstName, lastName, phoneNumber, dob, email, password);

        return user;

    }

    private Role createRole() throws ParseException{
        Role role = new Role(ERole.ROLE_CANDIDATE);
        return role;
    }

}
