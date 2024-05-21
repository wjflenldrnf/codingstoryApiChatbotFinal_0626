package org.spring.codingStory.approval.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.dto.ApprovalDivDto;
import org.spring.codingStory.approval.entity.ApprovalDivEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.spring.codingStory.approval.repository.ApprovalDivRepository;
import org.spring.codingStory.approval.serviceImpl.service.ApprovalDivService;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApprovalDivServiceImpl implements ApprovalDivService {

    private final ApprovalDivRepository approvalDivRepository;

    @Override
    public List<ApprovalDivDto> apvDivList() {

        List<ApprovalDivEntity> approvalDivEntitiyList = approvalDivRepository.findAll();
        List<ApprovalDivDto> approvalDivDtoList = new ArrayList<>();

        for (ApprovalDivEntity approvalDivEntity : approvalDivEntitiyList) {
            ApprovalDivDto approvalDivDto = ApprovalDivDto.toApvDivList(approvalDivEntity);
            approvalDivDtoList.add(approvalDivDto);
        }
        return approvalDivDtoList;
    }
}
