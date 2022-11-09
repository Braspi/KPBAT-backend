package tech.wiktor.apis.kpbatapi.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.wiktor.apis.kpbatapi.controllers.user.CreateUserRequest;
import tech.wiktor.apis.kpbatapi.controllers.user.CreateUserResponse;
import tech.wiktor.apis.kpbatapi.controllers.user.UsersListResponse;
import tech.wiktor.apis.kpbatapi.models.User;
import tech.wiktor.apis.kpbatapi.repositories.UserRepository;
import tech.wiktor.apis.kpbatapi.utils.StringUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
        String password = StringUtils.getPassword(16);

        Optional<User> user = this.userRepository.findByEmail(createUserRequest.getEmail());
        if (!user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "eee");
        }

        User newUser = new User(createUserRequest.getEmail(), new BCryptPasswordEncoder().encode(password), createUserRequest.getRole());
        this.userRepository.save(newUser);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setEmail(createUserRequest.getEmail());
        createUserResponse.setPassword(password);
        return ResponseEntity.ok(createUserResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getUsers(){
        List<UsersListResponse> response = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        users.forEach(user -> response.add(new UsersListResponse(user)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId){
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
        try {
            this.userRepository.deleteById(userId);
            return ResponseEntity.ok(userId);
        }catch (EmptyResultDataAccessException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
    }
}
