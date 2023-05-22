package com.usv.technotronus.features.certificate.request;

import com.usv.technotronus.features.certificate.request.dto.CreateCertificateRequestDto;
import org.modelmapper.PropertyMap;

public class Utils {

    public static final PropertyMap<CertificateRequest, CreateCertificateRequestDto> createCertificateRequestDtoPropertyMap = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setUserId(source.getUser().getId());
        }
    };
}
