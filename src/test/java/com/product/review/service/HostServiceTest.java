package com.product.review.service;

import com.product.review.entity.Host;
import com.product.review.repository.HostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;



import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class HostServiceTest {

    @Mock
    private HostRepository hostRepository;

    @InjectMocks
    private HostServiceImpl hostService;

    private Host host;

    @BeforeEach
    public void setup(){

        host = Host.builder()
                .hostId(1)
                .email("tony@email.com")
                .password("tony")
                .userName("star")
                .build();
    }


    @Test
    public void testRegisterHost(){


        given(hostRepository.save(host)).willReturn(host);

        System.out.println(hostRepository);
        System.out.println(hostService);

        Host savedHost = hostService.registerUser(host);

        System.out.println(savedHost);
        assertThat(savedHost).isNotNull();
    }



    @Test
    public void testGetUsersPositive(){

        Host host1 = Host.builder()
                .hostId(2)
                .email("john@email.com")
                .password("1234")
                .userName("john")
                .build();

        given(hostRepository.findAll()).willReturn(List.of(host,host1));

        List<Host> hostList = hostService.getAllUsers();

        assertThat(hostList).isNotNull();
        assertThat(hostList.size()).isEqualTo(2);
    }


    @Test
    public void testGetUsersNegative(){

        given(hostRepository.findAll()).willReturn(Collections.emptyList());

        List<Host> hostList = hostService.getAllUsers();

        assertThat(hostList).isEmpty();
        assertThat(hostList.size()).isEqualTo(0);
    }

    @Test
    public void testFetchUserByEmailAndPassword() {
        String email="high@email.com";
        String password = "high";

        given(hostRepository.findByEmailAndPassword(email, password)).willReturn(host);
        Host savedHost = hostService.fetchUserByEmailAndPassword(email, password);
        assertThat(savedHost).isEqualTo(host);
    }

    }





