package com.usv.technotronus.features.certificate_request;

import com.usv.technotronus.features.certificate_request.dto.CertificateRequestBySecretaryDto;
import com.usv.technotronus.features.certificate_request.dto.CreateCertificateRequestDto;
import com.usv.technotronus.features.user.User;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

public class Utils {

    public static final PropertyMap<CertificateRequest, CreateCertificateRequestDto> createCertificateRequestDtoPropertyMap = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setUserId(source.getUser().getId());
            map().setSecretaryId(source.getSecretary().getId());
        }
    };

    public static final PropertyMap<CertificateRequest, CertificateRequestBySecretaryDto> certificateRequestBySecretaryDtoPropertyMap = new PropertyMap<>() {
        @Override
        protected void configure() {

            map().setStudentEmail(source.getUser().getEmail());
            map().setStudyProgram(source.getUser().getStudyProgram().getName());
            using(converter).map(source.getUser(), destination.getStudentName());
        }
    };


    public static String generateFullName(User user) {
        return user.getFirstName() + " " +  user.getFatherInitial() + " " + user.getLastName();
    }

    static Converter<User, String> converter = ctx -> generateFullName(ctx.getSource());
}
