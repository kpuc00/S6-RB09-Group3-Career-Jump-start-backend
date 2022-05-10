import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import com.bezkoder.spring.login.models.User;
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

    @Test
    void serviceCreated(){
        assertThat(service).isNotNull();
    }

//    @Test
//    void getUser() throws ParseException{
//        User user = createUser();
//        when(repository.findById((long)3));
//        Optional<User> existU = service.findById((long)3);
//        assertThat(existU).isEqualTo(Optional.of(user));
//    }
//
//    @Test
//    void deleteUser() throws ParseException {
//        User user = createUser();
//        service.deleteUser(user.getId());
//        verify(repository).findById(user.getId());
//    }


//    private User createUser() throws ParseException{
//        List<Role> roles = new ArrayList<>();
//        Role role = new Role(ERole.ROLE_ADMIN);
//
//        String email = "Regi3@gmail.com";
//        String password = "Regipassword";
//        String username = "regi3";
//        String firstName = "Regi";
//        String lastName = "Nald";
//        String phoneNumber = "234762764";
//        LocalDateTime dob = null;
//        roles.add(role);
//
////        User user = new User((long)3, username, firstName, lastName, phoneNumber, dob, email, password);
//
////        return user;
//
//    }
}
