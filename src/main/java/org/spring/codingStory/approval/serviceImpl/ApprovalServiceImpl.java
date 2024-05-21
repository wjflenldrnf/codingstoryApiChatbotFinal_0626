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

import javax.transaction.Transactional;
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
            String filePath = "C:/codingStory/" + newFileName;
            apvFile.transferTo(new File(filePath));

            approvalDto.setMemberEntity(MemberEntity.builder()
                .id(approvalDto.getMemberId())
                .build());
            ApprovalEntity approvalEntity1 = ApprovalEntity.toWriteApv1(approvalDto);
            Long id = approvalRepository.save(approvalEntity1).getId();

            Optional<ApprovalEntity> optionalApprovalEntity = approvalRepository.findById(id);
            if (optionalApprovalEntity.isPresent()) {
                ApprovalEntity approvalEntity2 = optionalApprovalEntity.get();
                ApprovalFileDto approvalFileDto = ApprovalFileDto.builder().apvOldFileName(oldFileName)
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


    // 내가 결재자인 보고서
    @Transactional
    @Override
    public Page<ApprovalDto> apvList(Pageable pageable, String subject
        , String search, String name) {
        Page<ApprovalEntity> approvalEntityPage;

        if (subject == null || search == null) {
            approvalEntityPage = approvalRepository.findByApvFnl(pageable, name);
        } else {
            if (subject.equals("apvTitle")) {
                approvalEntityPage = approvalRepository.findByApvFnlAndApvTitleContaining(pageable, name , search);
            } else if (subject.equals("apvContent")) {
                approvalEntityPage = approvalRepository.findByApvFnlAndApvContentContaining(pageable, name, search);
            } else {
                approvalEntityPage = approvalRepository.findByApvFnl(pageable, name);
            }
        }
        Page<ApprovalDto> approvalDtoPage = approvalEntityPage.map(ApprovalDto::toApvDtoList);

        return approvalDtoPage;
    }

    //
//    //내가 작성한 보고서
    @Override
    public Page<ApprovalDto> myApvList(Pageable pageable, String subject, String search, Long memberId) {

        Page<ApprovalEntity> approvalEntityPage = null;

        if (subject == null || search == null) {
            approvalEntityPage = approvalRepository.findByMemberEntity_Id(pageable, memberId);
        } else {
            if (subject.equals("apvTitle")) {
                approvalEntityPage = approvalRepository.findByMemberEntity_IdAndApvTitleContaining(pageable, memberId, search);
            } else if (subject.equals("apvContent")) {
                approvalEntityPage = approvalRepository.findByMemberEntity_IdAndApvContentContaining(pageable, memberId, search);
            } else {
                approvalEntityPage = approvalRepository.findByMemberEntity_Id(pageable, memberId);
            }
        }
        Page<ApprovalDto> approvalDtoPage = approvalEntityPage.map(ApprovalDto::toApvDtoList);

        return approvalDtoPage;
    }

    @Override
    public ApprovalDto apvDetail(Long id) {

        Optional<ApprovalEntity> optionalApprovalEntity
            = approvalRepository.findById(id); //1.id를 찾는데 있는지 없는지부터 확인

        if (optionalApprovalEntity.isPresent()) {
            //조회할 게시글이 있으면
            ApprovalEntity approvalEntity
                = optionalApprovalEntity.get(); //2.  BoardEntity를 받아와서

            ApprovalDto approvalDto = ApprovalDto.toApvDtoDetail(approvalEntity);//3. entity -> dto로 바꿔주는 작업
            return approvalDto;
        }
        throw new IllegalArgumentException("글이 없습니다.");
    }


    @Override
    public void apvDeleteById(Long id) {

        Optional<ApprovalEntity> optionalApprovalEntity = approvalRepository.findById(id);

        if (optionalApprovalEntity.isPresent()) {
            approvalRepository.deleteById(id);
        } else {
            System.out.println("해당 보고서가 없습니다.");
        }

    }

    @Override
    public void apvOk(ApprovalDto approvalDto) {
        ApprovalEntity approvalEntity = new ApprovalEntity();
        approvalEntity = ApprovalEntity.toApvOkEntity(approvalDto);
        approvalRepository.save(approvalEntity);
    }


}

