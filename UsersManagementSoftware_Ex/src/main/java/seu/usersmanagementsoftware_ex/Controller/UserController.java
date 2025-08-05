package seu.usersmanagementsoftware_ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.usersmanagementsoftware_ex.Api.ApiResponse;
import seu.usersmanagementsoftware_ex.Model.User;
import seu.usersmanagementsoftware_ex.Service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable  Integer id, @Valid@RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable  Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }


    @GetMapping("/check-user-data/{userName}/{password}")
    public ResponseEntity<?> checkUserData(@PathVariable String userName, @PathVariable String password){
        return ResponseEntity.status(200).body(userService.checkUserByUsernameAndPassword(userName,password));
    }


    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }


    @GetMapping("/get-user-by-role/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.getUserByRole(role));
    }

    @GetMapping("/get-users-by-age-or-above/{age}")
    public ResponseEntity<?> getAllUserByAgeOrApove(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.getAllUserByAgeOrApove(age));
    }

}
