package com.product.review.controller;

import com.product.review.entity.Host;
import com.product.review.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:9090", "http://localhost:4200"},allowedHeaders = "*")
@RestController
public class HostController {

    @Autowired
    private HostService hostService;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody Host host) {
        hostService.registerUser(host);
        return new ResponseEntity<>("User added Successfully", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginAUser(@RequestBody Host host) {

        hostService.loginUser(host);
        return new ResponseEntity<>("User Login Successful", HttpStatus.OK);

    }
    @GetMapping("/all/users")
    public List<Host> fetchAllUsers() {
        return hostService.getAllUsers();
    }

    @GetMapping("/sortHost/{field}")
    public List<Host> findHostsWithSorting(@PathVariable String field) {
        return hostService.findHostsWithSorting(field);
    }
    @GetMapping("/paginationHost/{offset}/{pageSize}")
    public Page<Host> findHostsWithPagination(@PathVariable int offset,@PathVariable int pageSize) {
        return hostService.findHostsWithPagination(offset, pageSize);
    }
    @GetMapping("/pageHost/{offset}/{pageSize}/{field}")
    public Page<Host> findHostsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        return hostService.findHostsWithPaginationAndSorting(offset, pageSize, field);
    }
}
