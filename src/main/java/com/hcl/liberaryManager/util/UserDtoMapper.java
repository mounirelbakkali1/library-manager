package com.hcl.liberaryManager.util;

import com.hcl.liberaryManager.dto.UserDto;
import com.hcl.liberaryManager.entity.Role;
import com.hcl.liberaryManager.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {


    public UserDto mapToUserDto(User user){
        return UserDto
                .builder()
                .name(user.getName())
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    public User mapToUser(UserDto userDto){
        return User
                .builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .role(Role.valueOf(userDto.getRole()))
                .build();
    }
}
