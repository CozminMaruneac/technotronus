package com.usv.technotronus.features.user;

import com.usv.technotronus.features.user.dto.SecretaryDto;
import com.usv.technotronus.features.user.dto.UserViewDto;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.util.Objects;

public class Utils {


    public static final PropertyMap<User, UserViewDto> userUserViewDtoPropertyMap = new PropertyMap<>() {
        @Override
        protected void configure() {

            map().setStudyProgram(source.getStudyProgram().getName());
            map().setStudyDomain(source.getDomain().getName());
            using(converter).map(source, destination.getName());
        }
    };


    public static String generateFullName(User user) {
        String fatherInitial = Objects.isNull(user.getFatherInitial()) ? "" : user.getFatherInitial() + " ";
        return user.getFirstName() + " " + fatherInitial + user.getLastName();
    }

    static Converter<User, String> converter = ctx -> generateFullName(ctx.getSource());

    public static final PropertyMap<User, SecretaryDto> secretaryDtoPropertyMap = new PropertyMap<>() {
        @Override
        protected void configure() {

            using(converter).map(source, destination.getName());
        }
    };
}
