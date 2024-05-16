package org.spring.codingStory.approval.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.approval.dto.ApprovalFileDto;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.approval.entity.ApprovalFileEntity;
import org.spring.codingStory.approval.repository.ApprovalFileRepository;
import org.spring.codingStory.approval.repository.ApprovalRepository;
import org.spring.codingStory.approval.serviceImpl.service.ApprovalService;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository approvalRepository;

    private final ApprovalFileRepository approvalFileRepository;

    @Override
    public void apvWrite(ApprovalDto approvalDto) throws IOException {

        if (approvalDto.getApvFile().isEmpty()) {
            approvalDto.setMemberEntity(MemberEntity.builder()
                .id(approvalDto.getMemberId())
                .build());

            ApprovalEntity approvalEntity = ApprovalEntity.toWriteApv(approvalDto);
            approvalRepository.save(approvalEntity);
        } else {
            MultipartFile apvFile = approvalDto.getApvFile();
            String oldFileName = apvFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String newFileName = uuid + "_" + oldFileName;
            String filePath = "C://codingStory" + newFileName;
            apvFile.transferTo(new File(filePath));

            approvalDto.setMemberEntity(MemberEntity.builder()
                .id(approvalDto.getMemberId())
                .build());
            ApprovalEntity approvalEntity1 = ApprovalEntity.toWriteApv1(approvalDto);
            Long id = approvalRepository.save(approvalEntity1).getId();

            Optional<ApprovalEntity> optionalApprovalEntity = approvalRepository.findById(id);
            if (optionalApprovalEntity.isPresent()) {
                ApprovalEntity approvalEntity2 = optionalApprovalEntity.get();
                ApprovalFileDto approvalFileDto = ApprovalFileDto.builder().avpOldFileName(oldFileName)
                    .apvNewFileName(newFileName)
                    .approvalEntity(approvalEntity2)
                    .build();
                ApprovalFileEntity approvalFileEntity = ApprovalFileEntity.toApvWriteFile(approvalFileDto);

                approvalFileRepository.save(approvalFileEntity);
            } else {
                throw new IllegalArgumentException("없음!");
            }
        }
    }

    @Override
    public Page<ApprovalDto> apvList(Pageable pageable, String subject, String search) {
        Page<ApprovalEntity> approvalEntityPage = null;

        if(subject==null || search==null){
            approvalEntityPage = approvalRepository.findAll(pageable);
        }else {
            if (subject.equals("apvTitle")){
                approvalEntityPage=approvalRepository.findByApvTitleContains(pageable,search);
            } else if (subject.equals("apvContent")) {
                approvalEntityPage=approvalRepository.findByApvContentContains(pageable,search);
            }else {
                approvalEntityPage= approvalRepository.findAll(pageable);
            }
        }

        Page<ApprovalDto> approvalDtoPage = approvalEntityPage.map(ApprovalDto::toApvDto);

        return approvalDtoPage;
    }
}

