package org.loginsystem.data.repositeries;

import org.loginsystem.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {

    User findByEmailAddress(String emailAddress);
    User findByEmailAddressAndPassword(String emailAddress, String password);
    
}
