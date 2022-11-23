package com.product.review.service;

import com.product.review.entity.Host;
import com.product.review.exception.HostNotFoundException;
import com.product.review.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class HostServiceImpl implements HostService{

    @Autowired
    private HostRepository hostRepository;

    @Override
    public Host registerUser(Host host) {
        String tempEmail = host.getEmail();
        if (tempEmail != null && !"".equals(tempEmail)) {
            if (hostRepository.findByEmail(tempEmail) != null) {
                throw new HostNotFoundException("No user found with given email");

            }
        }
        return hostRepository.save(host);
    }

    @Override
    public List<Host> getAllUsers() {
        return hostRepository.findAll();
    }

    @Override
    public Host fetchUserByEmailAndPassword(String email, String password) {
        return hostRepository.findByEmailAndPassword(email, password);
    }

    public List<Host> findHostsWithSorting(String field){
        return  hostRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    public void loginUser(Host host) {
        String tempEmail = host.getEmail();
        String tempPass = host.getPassword();
        if(tempEmail != null && tempPass != null) {
            if(hostRepository.findByEmailAndPassword(tempEmail, tempPass) == null) {
                throw new HostNotFoundException("Bad Credentials: User Not Registered");
            }
        }
    }




    public Page<Host> findHostsWithPagination(int offset,int pageSize){
        Page<Host> hosts = hostRepository.findAll(PageRequest.of(offset, pageSize));
        return  hosts;
    }



    public Page<Host> findHostsWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Host> hosts = hostRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  hosts;
    }
}
