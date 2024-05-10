package com.aztu.job_application.service.impl.userInfoImpl;

import com.aztu.job_application.model.dto.response.userInformation.SoftSkillResponse;
import com.aztu.job_application.model.entity.userInformation.SoftSkill;
import com.aztu.job_application.repository.userInformation.SoftSkillRepository;
import com.aztu.job_application.service.userInformation.SoftSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ISoftSkillService implements SoftSkillService {

    private final SoftSkillRepository softSkillRepository;

    @Override
    public List<SoftSkill> findAll(List<Long> softSkillsId) {
        return softSkillRepository.findAllById(softSkillsId);
    }

    @Override
    public ResponseEntity<List<SoftSkillResponse>> findAll() {

        List<SoftSkillResponse> softSkillResponses = softSkillRepository.findAll()
                .stream()
                .map(softSkill -> SoftSkillResponse.builder()
                        .name(softSkill.getName())
                        .id(softSkill.getId())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(softSkillResponses);
    }
}
