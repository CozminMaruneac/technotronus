package com.usv.technotronus.features.user;

import com.usv.technotronus.features.user.dto.UserViewDto;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

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
        return user.getFirstName() + " " +  user.getFatherInitial() + " " + user.getLastName();
    }

    static Converter<User, String> converter = ctx -> generateFullName(ctx.getSource());
}
