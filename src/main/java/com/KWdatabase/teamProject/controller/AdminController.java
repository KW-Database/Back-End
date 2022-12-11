package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.Company;
import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.Service.CompanyService;
import com.KWdatabase.teamProject.Service.UserService;
import com.KWdatabase.teamProject.dao.CompanyDao;
import com.KWdatabase.teamProject.dao.ItemCodeDao;
import com.KWdatabase.teamProject.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private CompanyDao companyDao;

    private final ItemCodeDao itemCodeDao;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserList(){
        return ResponseEntity.ok().body(userDao.getUserList());
    }

    @DeleteMapping("/user")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam("id") String id){
        System.out.println(id);
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/company")
    public ResponseEntity<List<Company>> getCompanyList(){
        return ResponseEntity.ok().body(companyDao.getCompanyList());
    }

    @DeleteMapping("/company")
    public ResponseEntity<HttpStatus> deleteCompany(@RequestParam("code") String code){
        companyDao.deleteCompany(code);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private CompanyService companyService;
    @PostMapping("/company")
    public ResponseEntity<HttpStatus> createCompany(@RequestBody Company company) {
        ItemCode itemCode = ItemCode.builder()
                .itemCode(company.getItemCode())
                .itemName(company.getCompanyName())
                .likeNumber(0)
                .build();
        itemCodeDao.insertItemCode(itemCode);
        companyDao.insertCompany(company);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
