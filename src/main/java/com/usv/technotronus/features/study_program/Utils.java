package com.usv.technotronus.features.study_program;

import com.usv.technotronus.features.study_program.dto.StudyProgramDto;
import com.usv.technotronus.features.user.User;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.util.Objects;

public class Utils {

    public static final PropertyMap<StudyProgram, StudyProgramDto> studyProgramDtoPropertyMap = new PropertyMap<>() {
        @Override
        protected void configure() {

            map().setSecretaryId(source.getSecretary().getId());
            using(converter).map(source.getSecretary(), destination.getSecretaryName());
        }
    };


    public static String generateFullName(User user) {
        String fatherInitial = Objects.isNull(user.getFatherInitial()) ? "" : user.getFatherInitial() + " ";
        return user.getFirstName() + " " + fatherInitial + user.getLastName();
    }

    static Converter<User, String> converter = ctx -> generateFullName(ctx.getSource());
}
