package camt.se234.project;

import camt.se234.project.dao.UserDao;
import camt.se234.project.entity.User;
import camt.se234.project.service.AuthenticationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class AuthenticationServiceImplTest {

    UserDao userDao;
    AuthenticationServiceImpl authenticationService;

    @Before
    public void setup(){
        userDao = mock(UserDao.class);
        authenticationService = new AuthenticationServiceImpl();
        authenticationService.setUserDao(userDao);
    }
    @Test
    public void testAuthentication(){
        List<User> mockUser = new ArrayList<>();
        when(userDao.getUser("Black","1234")).thenReturn(new User("Black","1234","Hee"));
        assertThat(authenticationService.authenticate("Black" , "1234"), is(new User("Black","1234","Hee")));
        assertThat(authenticationService.authenticate("White" ,"1234"),is(nullValue()));

    }
}
