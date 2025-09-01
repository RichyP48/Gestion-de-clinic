package com.hms.user.UserMS.clients;


import com.hms.user.UserMS.config.FeignClientInterceptor;
import com.hms.user.UserMS.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ProfileMS", configuration = FeignClientInterceptor.class)
public interface ProfileClient {
    @PostMapping("/profile/doctor/add")
    Long addDoctor(@RequestBody UserDto userDto);
    @PostMapping("/profile/patient/add")
    Long addPatient(@RequestBody UserDto userDto);

}
