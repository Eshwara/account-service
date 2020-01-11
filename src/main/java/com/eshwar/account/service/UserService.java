package com.eshwar.account.service;

import com.eshwar.account.model.User;
import com.eshwar.account.repository.UserRepository;
import com.eshwar.account.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(User user){
        UserResponse userResponse = new UserResponse();
        if(isuserExists(user)){
            userResponse.setStatus("error");
            userResponse.setMessage("Account already exists");
            return userResponse;
        }
        User temp =  userRepository.save(user);
        System.out.println("temp : "+ temp);

        if(temp != null){
          userResponse.setStatus("ok");
          userResponse.setMessage("Account Created");
          return userResponse;
        }

        userResponse.setStatus("error");
        userResponse.setMessage("user creation error");

        return userResponse;

    }

    private boolean isuserExists(User user){

        Optional<User> optemp = userRepository.findById(user.getUserName());

        if(optemp.isPresent()){
            return true;
        }

        return false;

    }

    public UserResponse sigin(User user){

        UserResponse userResponse = new UserResponse();

         User temp = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
         if(temp != null){
             temp.setToken(UUID.randomUUID().toString());
             userRepository.save(temp);
             userResponse.setStatus("ok");
             userResponse.setMessage("Sign In Successful");
             userResponse.setToken(temp.getToken());

             return userResponse;
         }

         userResponse.setStatus("error");
         userResponse.setMessage("Username Password Mismatch");

         return userResponse;
    }

    public UserResponse findUserbyToken(String token){

        User user = userRepository.findByToken(token);
        UserResponse userResponse = new UserResponse();
       if(user != null){
           userResponse.setStatus("ok");

           return userResponse;

       }

       userResponse.setStatus("error");
        return userResponse;
    }

}
