package com.pavan.journalApp.controllers;

import com.pavan.journalApp.dto.LoginDto;
import com.pavan.journalApp.models.Response;
import com.pavan.journalApp.models.User;
import com.pavan.journalApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/addUser")
    public Response<User> addUser(@RequestBody @Valid User user) {

        User dbUser = userRepo.save(user);

        return new Response<>("Success","User Addedd successfully",dbUser);
    }

    @GetMapping("/getUser/{id}")
    public Response<User> getUser(@PathVariable("id") Long id) {

        Optional<User> result = userRepo.findById(id);
        Response res;

        if(result.isPresent()){

            res = new Response<User>("Success","user added successfully",result.get());

        }else{
            res = new Response("Fail","User not found",null);
        }




        return res;

    }


    @DeleteMapping("/deleteUser/{id}")
    public Response<User> deleteUser(@PathVariable(value = "id") Long id) {

        User user = userRepo.findById(id).get();

        if(user == null){
            return new Response<User>("Failed","user not found",user);
        }
        else{
            userRepo.deleteById(id);
            return new Response<User>("Success","User deleted successfully",user);
        }
    }

        @PutMapping("/updateUser")
     public Response<User> updateUser(@RequestBody User user) {

        Long id = user.getId();
        User db = userRepo.findById(id).get();

        if(db!=null){
            userRepo.save(user);
        }

        return new Response<>("Success","user updated successfully",user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginDto loginDto){
        User dbuser = userRepo.findByEmailAndPassword(loginDto.getUserName(),loginDto.getPassword());
        
        if(dbuser == null)
            return new Response<LoginDto>("Failed","please check username and password",loginDto);

        return new Response<User>("success","login successfull",dbuser);
    }

//    @JsonProperty(access = Access.WRITE_ONLY)


}