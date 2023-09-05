package com.microservice.userservice.service.impl;

import com.microservice.userservice.dto.DepartmentDto;
import com.microservice.userservice.dto.ResponseDto;
import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
//    private RestTemplate restTemplate;

    static RestTemplate restTemplate;

    static {
        restTemplate= createRestTemplateWithTimeout(50000);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {

        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);
        ResponseEntity<DepartmentDto> responseEntity = null;
        try {
            responseEntity = restTemplate
                    .getForEntity("http://localhost:9993/api/departments/" + user.getDepartmentId(),
                            DepartmentDto.class);
            DepartmentDto departmentDto = responseEntity.getBody();

            System.out.println(responseEntity.getStatusCode());

            responseDto.setUser(userDto);
            responseDto.setDepartment(departmentDto);
    } catch (Exception e) {
        // Handle exceptions if needed
        System.err.println("Error while calling the microservice: " + e.getMessage());
        e.printStackTrace();
    }


        return responseDto;
    }

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }


    private static RestTemplate createRestTemplateWithTimeout(int timeoutMillis) {
        // Create a SimpleClientHttpRequestFactory with the specified timeout
        ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        ((SimpleClientHttpRequestFactory) factory).setConnectTimeout(timeoutMillis);
        ((SimpleClientHttpRequestFactory) factory).setReadTimeout(timeoutMillis);

        // Create a RestTemplate instance with the custom request factory
        return new RestTemplate(factory);
    }
}