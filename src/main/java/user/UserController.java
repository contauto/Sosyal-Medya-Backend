package user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {


    @CrossOrigin
    @PostMapping("/api/1.0/users")
    public void CreateUser(@RequestBody User user){}

}
