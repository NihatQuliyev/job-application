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

    @Query("select u from _user u where u.name LIKE %:key% or u.surname LIKE %:key%")
    List<User> findByNameOrSurname(@Param("key") String key);

    List<User> findByUserInformation_EducationLevelDetail_EducationLevel_Id(long educationLevelId);

    @Query("SELECT u FROM _user u WHERE u.userInformation.address LIKE %:address%")
    List<User> findByUserInformation_Address(@Param("address") String address);

    List<User> findByUserInformation_MilitaryQualification_Id(long militaryQualificationId);

    Optional<User> findByEmail(String email);

}
