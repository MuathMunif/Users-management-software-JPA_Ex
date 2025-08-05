package seu.usersmanagementsoftware_ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.usersmanagementsoftware_ex.Api.ApiExcpection;
import seu.usersmanagementsoftware_ex.Model.User;
import seu.usersmanagementsoftware_ex.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }


    public void updateUser(Integer id ,User user){
        User oldUser = userRepository.findUserById(id);
        if(oldUser == null){
            throw new ApiExcpection("User not found");
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setName(user.getName());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user == null){
            throw new ApiExcpection("User not found");
        }
        userRepository.delete(user);
    }


    public User checkUserByUsernameAndPassword(String username, String password){
        User user = userRepository.checkUserByUsernameAndPassword(username, password);
        if(user == null){
            throw new ApiExcpection("Invalid username or password");
        }
        return user;
    }


    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new ApiExcpection("User not found");
        }
        return user;
    }


    public List<User> getUserByRole(String role){
        if(!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("user")){
            throw new ApiExcpection("Invalid role");
        }
        List<User> users = userRepository.findUserByRole(role);
        if(users == null){
            throw new ApiExcpection("No user found");
        }
        return users;
    }


    public List<User> getAllUserByAgeOrApove(Integer age){
        List<User> users = userRepository.getAllUserByAgeOrApove(age);
        if(users.isEmpty()){
            throw new ApiExcpection("No user found with this age");
        }
        return users;
    }

}
