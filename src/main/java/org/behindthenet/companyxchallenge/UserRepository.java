package org.behindthenet.companyxchallenge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
    
}
