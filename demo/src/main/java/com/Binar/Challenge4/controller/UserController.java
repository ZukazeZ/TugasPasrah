package com.Binar.Challenge4.controller;

import com.Binar.Challenge4.SpringBootJasperReportApplication;
import com.Binar.Challenge4.entity.UserEntity;
import com.Binar.Challenge4.repository.UserRepository;
import com.Binar.Challenge4.service.ReportService;
import com.Binar.Challenge4.service.UserService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private ReportService service;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity create(@RequestBody UserEntity user){
        return userService.saveUser(user);
    }
    @GetMapping("/getall")
    public List<UserEntity> getall(){
        return userService.findalluser();
    }
    @PutMapping("/update/{id}")
    public UserEntity update(@PathVariable Long id, @RequestBody UserEntity user){
        return userService.updaterUser(id,user);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return userService.deleteUser(id);
    }


    @GetMapping("/test")
    public String testEndpoint(){
        return "JALAN GAK KAU";
    }


    @GetMapping("/getUser")
    public List<UserEntity> getUser(){
        return repository.findAll();
    }
    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJasperReportApplication.class, args);
    }

}
