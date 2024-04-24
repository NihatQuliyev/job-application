package com.aztu.job_application.mapper;

import com.aztu.job_application.model.entity.Otp;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OtpMapper {
    Otp map(@MappingTarget Otp otp, Otp generateOtp);
}
