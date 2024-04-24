package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.entity.userInformation.SoftSkill;
import com.aztu.job_application.repository.userInformation.SoftSkillRepository;
import com.aztu.job_application.service.userInformation.SoftSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ISoftSkillService implements SoftSkillService {

    private final SoftSkillRepository softSkillRepository;

    @Override
    public List<SoftSkill> findAll(List<Long> softSkillsId) {
        return softSkillRepository.findAllById(softSkillsId);
    }
}
