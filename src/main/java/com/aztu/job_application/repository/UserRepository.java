package com.aztu.job_application.repository;

import com.aztu.job_application.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from _user u where u.email =:email and u.isEnable = true")
    Optional<User> findByEmailAndIsEnableTrue(@Param("email") String email);

    @Query("select u from _user u where u.name = :key or u.surname = :key")
    List<User> findByNameOrSurname(String key);

    List<User> findByUserInformation_EducationLevelDetail_EducationLevel_Id(long educationLevelId);

    List<User> findByUserInformation_Address(String address);

    List<User> findByUserInformation_MilitaryQualification_Id(long militaryQualificationId);

}
