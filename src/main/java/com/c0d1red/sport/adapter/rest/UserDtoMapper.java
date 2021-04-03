package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.domain.user.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    private final ModelMapper modelMapper;

    public UserDtoMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper.typeMap(User.class, UserDto.class)
                .addMapping(user -> user.getUsername().getName(), UserDto::setUsername)
                .addMapping(User::getRole, UserDto::setRole);
    }

    public UserDto mapDtoFrom(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
