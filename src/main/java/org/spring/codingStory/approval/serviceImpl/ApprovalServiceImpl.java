package org.spring.codingStory.approval.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.spring.codingStory.approval.dto.ApprovalDto;
import org.spring.codingStory.approval.dto.ApprovalFileDto;
import org.spring.codingStory.approval.dto.ApprovalStatusDto;
import org.spring.codingStory.approval.entity.ApprovalEntity;
import org.spring.codingStory.approval.entity.ApprovalFileEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.spring.codingStory.approval.repository.ApprovalFileRepository;
import org.spring.codingStory.approval.repository.ApprovalRepository;
import org.spring.codingStory.approval.repository.ApprovalStatusRepository;
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

    private final ApprovalStatusRepository approvalStatusRepository;

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
            String filePath = "C:/codingStory_file/" + newFileName;
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


//    @Override
//    public void apvWriteMany(ApprovalDto approvalDto) throws IOException {
//        if (approvalDto.getApvFile().isEmpty()) {
//            approvalDto.setMemberEntity(MemberEntity.builder()
//                .id(approvalDto.getMemberId())
//                .build());
//
//            ApprovalEntity approvalEntity = ApprovalEntity.toWriteApv(approvalDto);
//            approvalRepository.save(approvalEntity);
//        } else {
//            ArrayList<MultipartFile> multipartFileArrayList = new ArrayList<>();
//            ArrayList<String> arrayList=new ArrayList<String>();
//            for(MultipartFile apvFile:multipartFileArrayList){
//
//            String oldFileName = apvFile.getOriginalFilename();
//            UUID uuid = UUID.randomUUID();
//            String newFileName = uuid + "_" + oldFileName;
//            String filePath = "C:/codingStory/" + newFileName;
//            apvFile.transferTo(new File(filePath));
//            }
//
//            approvalDto.setMemberEntity(MemberEntity.builder()
//                .id(approvalDto.getMemberId())
//                .build());
//            ApprovalEntity approvalEntity1 = ApprovalEntity.toWriteApv1(approvalDto);
//            Long id = approvalRepository.save(approvalEntity1).getId();
//
//            Optional<ApprovalEntity> optionalApprovalEntity = approvalRepository.findById(id);
//            if (optionalApprovalEntity.isPresent()) {
//                for (MultipartFile apvFile:multipartFileArrayList) {
//
//                    String oldFileName = apvFile.getOriginalFilename();
//                    UUID uuid = UUID.randomUUID();
//                    String newFileName = uuid + "_" + oldFileName;
//                    String filePath = "C:/codingStory/" + newFileName;
//                    apvFile.transferTo(new File(filePath));
//                    ApprovalEntity approvalEntity2 = optionalApprovalEntity.get();
//                    ApprovalFileDto approvalFileDto = ApprovalFileDto.builder().apvOldFileName(oldFileName)
//                        .apvNewFileName(newFileName)
//                        .approvalEntity(approvalEntity2)
//                        .build();
//                    ApprovalFileEntity approvalFileEntity = ApprovalFileEntity.toApvWriteFile(approvalFileDto);
//
//                    approvalFileRepository.save(approvalFileEntity);
//                }
//            } else {
//                throw new IllegalArgumentException("없음!");
//            }
//        }
//    }

    // 내가 결재자인 보고서
    @Transactional
    @Override
    public Page<ApprovalDto> apvList(Pageable pageable, String subject
        , String search, String name) {
        Page<ApprovalEntity> approvalEntityPage;
        ApprovalStatusDto approvalStatusDto = new ApprovalStatusDto();
        ApprovalEntity approvalEntity = new ApprovalEntity();


        if (subject == null || search == null) {
//            approvalEntityPage = approvalRepository.findByApvFnl(pageable, name);
            approvalEntityPage = approvalRepository.findByApvFnl(pageable, name);
        } else {
            if (subject.equals("apvTitle")) {
                approvalEntityPage = approvalRepository.findByApvFnlAndApvTitleContaining(pageable, name, search);
            } else if (subject.equals("apvContent")) {
                approvalEntityPage = approvalRepository.findByApvFnlAndApvContentContaining(pageable, name, search);
            } else {
                approvalEntityPage = approvalRepository.findByApvFnl(pageable, name);
            }
        }
        Page<ApprovalDto> approvalDtoPage = approvalEntityPage.map(ApprovalDto::toApvDtoList);

        return approvalDtoPage;
    }


    //상태별로 뽑기
//    @Transactional
//    @Override
//    public Page<ApprovalDto> apvList2(Pageable pageable, String subject
//        , String search, String name, Long id) {
//        Page<ApprovalEntity> approvalEntityPage = null;
//        ApprovalStatusDto approvalStatusDto = new ApprovalStatusDto();
//
//        ApprovalEntity approvalEntity = new ApprovalEntity();
//        if (subject == null || search == null) {
//            if (1L == id) {
//                approvalEntityPage = approvalRepository.findByApvFnlAndApprovalStatusEntity_Id(pageable, name, id);
//            } else if (subject.equals("apvTitle")) {
//                approvalEntityPage = approvalRepository.findByApvFnlAndApprovalStatusEntity_IdAndApvTitleContaining(pageable, name, search, id);
//            } else if (subject.equals("apvContent")) {
//                approvalEntityPage = approvalRepository.findByApvFnlAndApprovalStatusEntity_IdAndApvContentContaining(pageable, name, search, id);
//            }
////        } else {
//
//            if (2L == id || 3L == id) {
//                approvalEntityPage = approvalRepository.findByApvFnlAndApprovalStatusEntity_Id(pageable, name, id);
//            } else if (subject.equals("apvTitle")) {
//                approvalEntityPage = approvalRepository.findByApvFnlAndApprovalStatusEntity_IdAndApvTitleContaining(pageable, name, search, id);
//            } else if (subject.equals("apvContent")) {
//                approvalEntityPage = approvalRepository.findByApvFnlAndApprovalStatusEntity_IdAndApvContentContaining(pageable, name, search, id);
//            }
//        }
//        Page<ApprovalDto> approvalDtoPage = approvalEntityPage.map(ApprovalDto::toApvDtoList);
//
//        return approvalDtoPage;
//    }


    //내가 작성한 보고서
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
                = optionalApprovalEntity.get(); //2.  BoardEntity 를 받아와서

            return ApprovalDto.toApvDtoDetail(approvalEntity);
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
    public void apvUpdate(ApprovalDto approvalDto) throws IOException {
        ApprovalFileEntity approvalFileEntity = new ApprovalFileEntity();

        //파일 유지 포기 !!!
//        if (approvalDto.getApprovalFileEntityList().equals(approvalFileEntity.getApvOldFileName())) {
//            MultipartFile apvFile = approvalDto.getApvFile();
//            String fileOldName = apvFile.getOriginalFilename();
//            UUID uuid = UUID.randomUUID();
//            String fileNewName = uuid + "_" + fileOldName;
//            String savePath = "C:/codingStory_file/" + fileNewName;
//            apvFile.transferTo(new File(savePath));
//
//            ApprovalEntity approvalEntity = ApprovalEntity.toApvUpdateEntity1(approvalDto);
//            approvalRepository.save(approvalEntity);
//
//            ApprovalFileEntity approvalFileEntity2 = ApprovalFileEntity.builder()
//                .approvalEntity(approvalEntity)
//                .apvNewFileName(fileNewName)
//                .apvOldFileName(fileOldName)
//                .build();
//
//            Long fileId = approvalFileRepository.save(approvalFileEntity2).getId();
//            approvalFileRepository.findById(fileId).orElseThrow(() -> {
//                throw new IllegalArgumentException("파일등록 실패");
//            });

        Optional<ApprovalFileEntity> optionalApprovalFileEntity = approvalFileRepository.findByApprovalEntityId(approvalDto.getId());
        if (optionalApprovalFileEntity.isPresent()) {
            String fileNewName = optionalApprovalFileEntity.get().getApvNewFileName();
            String filePath = "C:/codingStory/" + fileNewName;
            File deleteFile = new File(filePath);
            if (deleteFile.exists()) {
                deleteFile.delete();
                System.out.println("파일을 삭제하였습니다");
            } else {
                System.out.println("파일이 존재하지않습니다");
            }
            approvalFileRepository.delete(optionalApprovalFileEntity.get());
        }
        MemberEntity memberEntity = MemberEntity.builder()
            .id(approvalDto.getMemberId()).build();
        approvalDto.setMemberEntity(memberEntity);
        if (approvalDto.getApvFile().isEmpty()) {
            ApprovalEntity approvalEntity = ApprovalEntity.toApvUpdateEntity0(approvalDto);
            approvalRepository.save(approvalEntity);
        } else if (!approvalDto.getApvFile().isEmpty()) {
            MultipartFile apvFile = approvalDto.getApvFile();
            String fileOldName = apvFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String fileNewName = uuid + "_" + fileOldName;
            String savePath = "C:/codingStory_file/" + fileNewName;
            apvFile.transferTo(new File(savePath));

            ApprovalEntity approvalEntity = ApprovalEntity.toApvUpdateEntity1(approvalDto);
            approvalRepository.save(approvalEntity);

            ApprovalFileEntity approvalFileEntity2 = ApprovalFileEntity.builder()
                .approvalEntity(approvalEntity)
                .apvNewFileName(fileNewName)
                .apvOldFileName(fileOldName)
                .build();

            Long fileId = approvalFileRepository.save(approvalFileEntity2).getId();
            approvalFileRepository.findById(fileId).orElseThrow(() -> {
                throw new IllegalArgumentException("파일등록 실패");
            });
        }
        //게시글 수정 확인
        approvalRepository.findById(approvalDto.getId()).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글 수정실패");
        });

    }

    @Override
    public void apvOk(ApprovalDto approvalDto) {
        ApprovalEntity approvalEntity = new ApprovalEntity();

        MemberEntity memberEntity = MemberEntity.builder()
            .id(approvalDto.getMemberId()).build();
        approvalDto.setMemberEntity(memberEntity);

        approvalEntity = ApprovalEntity.toApvOkEntity(approvalDto);

        approvalRepository.save(approvalEntity);
    }


}

