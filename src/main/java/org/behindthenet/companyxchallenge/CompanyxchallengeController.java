package org.behindthenet.companyxchallenge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class CompanyxchallengeController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAllUsers() {
        return userRepository.findAll();
        //return userRepository.findAllOrderByLastnameFirstname();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value="id") long id) {
        Optional<User> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
        // you could do this with a uniqueness constraint on the User object annotation itself, but it's a little
        // complicated to implement case insensitivity that way, and that's what you probably want.
        
        if (userRepository.findByFirstNameAndLastNameAllIgnoreCase(user.getFirstName(), user.getLastName()).size() > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        } else {
            return ResponseEntity.ok().body(userRepository.save(user));
        }
        
    }

    @GetMapping("/delete/{strId}")
    public ResponseEntity<Long> deleteUser(@PathVariable(value="strId") long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        } catch(EmptyResultDataAccessException|IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(id);
        } 
    }
    

}
