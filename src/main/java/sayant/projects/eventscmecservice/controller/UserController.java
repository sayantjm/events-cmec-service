package sayant.projects.eventscmecservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sayant.projects.eventscmecservice.model.UserDTO;
import sayant.projects.eventscmecservice.service.UserService;

import java.util.List;

/**
 * Created by sayantjm on 20/12/20
 */
@RequestMapping("/api/v1/")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("user/{username}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getByUserName(username), HttpStatus.OK);
    }

    @PostMapping("user/")
    public ResponseEntity<UserDTO> saveNewUser(@RequestBody @Validated UserDTO userDTO) {
        return new ResponseEntity<>(userService.saveNewUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("user/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("username") String username, @RequestBody @Validated UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(username, userDTO), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("user/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        userService.removeUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "user/")
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        return new ResponseEntity<List<UserDTO>>(userService.listAll(), HttpStatus.OK);
    }
}
