package claudiogiasi.eserciziou4w3d1.services;

import claudiogiasi.eserciziou4w3d1.entities.User;
import claudiogiasi.eserciziou4w3d1.exceptions.NotFoundException;
import claudiogiasi.eserciziou4w3d1.exceptions.ValidationException;
import claudiogiasi.eserciziou4w3d1.dto.UserRequestDTO;
import claudiogiasi.eserciziou4w3d1.dto.UserResponseDTO;
import claudiogiasi.eserciziou4w3d1.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    final private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserResponseDTO create(UserRequestDTO userDTO) {

        if (userDTO.name() == null || userDTO.name().isBlank()) {
            throw new ValidationException("Il nome è obbligatorio!");
        }

        if (userDTO.surname() == null || userDTO.surname().isBlank()) {
            throw new ValidationException("Il cognome è obbligatorio!");
        }

        if (userDTO.email() == null || userDTO.email().isBlank()) {
            throw new ValidationException("L'indirizzo email è obbligatorio!");
        }

        if(userRepo.findByEmail(userDTO.email()).isPresent()) {
            throw new ValidationException("Email già in uso da un utente!");
        }

        if (userDTO.password() == null || userDTO.password().isEmpty()) {
            throw new ValidationException("La password è obbligatoria!");
        }

       User newUser = new User(
               userDTO.name(),
               userDTO.surname(),
               userDTO.password(),
               userDTO.email()
       );

        User savedUser = userRepo.save(newUser);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getSurname(),
                savedUser.getEmail()
        );
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User update(Long id, User userUpdated) {
        if (userUpdated.getName() == null || userUpdated.getName().isBlank()) {
            throw new ValidationException("Il nome è obbligatorio!");
        }

        if (userUpdated.getSurname() == null || userUpdated.getSurname().isBlank()) {
            throw new ValidationException("Il cognome è obbligatorio!");
        }

        if (userUpdated.getEmail() == null || userUpdated.getEmail().isBlank()) {
            throw new ValidationException("L'indirizzo email è obbligatorio!");
        }

        userRepo.findByEmail(userUpdated.getEmail()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new ValidationException("Email già in uso da un altro utente!");
            }
        });

        if (userUpdated.getPassword() == null || userUpdated.getPassword().isEmpty()) {
            throw new ValidationException("La password è obbligatoria!");
        }

        User existingUser = findById(id);
        existingUser.setName(userUpdated.getName());
        existingUser.setSurname(userUpdated.getSurname());
        existingUser.setEmail(userUpdated.getEmail());
        existingUser.setPassword(userUpdated.getPassword());

        return userRepo.save(existingUser);
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepo.delete(user);
    }

}
