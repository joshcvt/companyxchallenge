package org.behindthenet.companyxchallenge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("ORDER BY lastName, firstName")
    //List<User> findAllOrderByLastnameFirstname();

    List<User> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
    
}
