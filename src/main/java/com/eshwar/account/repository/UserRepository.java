package com.eshwar.account.repository;

import com.eshwar.account.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    public User findByUserNameAndPassword(String userName, String password);

    public User findByToken(String token);
}
