package com.messageriespring.controlleur;

import com.messageriespring.exception.ResourceNotFoundException;
import com.messageriespring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "localhost:4200")
public class UserController {

    @Autowired
    com.messageriespring.repository.UserRepository UserRepository;

@GetMapping("/User")
public List<User> getUsers() {
    return (List<User>) UserRepository.findAll();
}
@PostMapping("/Users")
public User createUser(@Valid @RequestBody User User) {
    return UserRepository.save(User);
}

@GetMapping("/Users/{id}")
public User getUserById(@PathVariable(value = "id") Long UserId) {
    return UserRepository.findById(UserId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
}
@PutMapping("/Users/{id}")
public User updateUser(@PathVariable(value = "id") Long UserId,
                                        @Valid @RequestBody User UserDetails) {

    User User = UserRepository.findById(UserId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
    User.setNom(UserDetails.getNom());
    User.setPrenom(UserDetails.getPrenom());
    User.setTel(UserDetails.getTel());
    User.setEmail(UserDetails.getEmail());
    User.setCin(UserDetails.getCin());
    User.setPassword(UserDetails.getPassword());
    User.setCodePostal(UserDetails.getCodePostal());
    User.setType(UserDetails.getType());
    User.setVille(UserDetails.getVille());
    User.setPays(UserDetails.getPays());
    User.setLatitude(UserDetails.getLatitude());
    User.setLongitude(UserDetails.getLongitude());
    User updatedUser = UserRepository.save(User);
    return updatedUser;
}
@DeleteMapping("/Users/{id}")
public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long UserId) {
    User User = UserRepository.findById(UserId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
    UserRepository.delete(User);
    return ResponseEntity.ok().build();
}
}