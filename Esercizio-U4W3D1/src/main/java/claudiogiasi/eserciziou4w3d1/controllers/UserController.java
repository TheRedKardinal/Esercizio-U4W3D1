package claudiogiasi.eserciziou4w3d1.controllers;

import claudiogiasi.eserciziou4w3d1.dto.UserRequestDTO;
import claudiogiasi.eserciziou4w3d1.dto.UserResponseDTO;
import claudiogiasi.eserciziou4w3d1.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {
    // POST http://port:3001/api/users + request.body

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public UserResponseDTO create(@RequestBody UserRequestDTO userDTO) {
        return userService.create(userDTO);
    }
}
