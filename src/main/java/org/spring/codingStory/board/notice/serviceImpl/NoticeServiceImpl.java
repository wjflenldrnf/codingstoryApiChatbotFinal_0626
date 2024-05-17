package org.spring.codingStory.board.notice.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.spring.codingStory.board.notice.dto.NoticeDto;
import org.spring.codingStory.board.notice.dto.NoticeFileDto;
import org.spring.codingStory.board.notice.entity.NoticeEntity;
import org.spring.codingStory.board.notice.entity.NoticeFileEntity;
import org.spring.codingStory.board.notice.repository.NoticeFileRepository;
import org.spring.codingStory.board.notice.repository.NoticeRepository;
import org.spring.codingStory.board.notice.serviceImpl.service.NoticeService;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;


    @Override
    public void noticeInsertFile(NoticeDto noticeDto) throws IOException {
        if (noticeDto.getBoardFile().isEmpty()) {
            //파일 없는 경우
            noticeDto.setMemberEntity(MemberEntity.builder()
                    .id(noticeDto.getMemberId())
                    .build());
            NoticeEntity noticeEntity = NoticeEntity.toInsertNoticeEntity(noticeDto);
            noticeRepository.save(noticeEntity);
        } else {
            ////////////////////////////////////파일이 실제 경로에 저장//////////////////////////////
            MultipartFile boardFile = noticeDto.getBoardFile();// 1. 파일을 가져온다. Dto
            // 이름 암호화 -> DB 저장, local에 저장 할 이름
            String oldFileName = boardFile.getOriginalFilename();// 원본파일 이름
            UUID uuid = UUID.randomUUID(); //random id -> 랜덤한 값을 추출하는 플래스
            String newFileName = uuid + "_" + oldFileName; // 저장파일이름 (보완)

            String filePath = "C:/CodingStory_file/" + newFileName; // 실제 파일 저장경로+이름
            //실제파일 저장 실행
            boardFile.transferTo(new File(filePath));//저장, 예외처리 -> 경로에 파일 저장

            // 1. 게시글 ->
            noticeDto.setMemberEntity(MemberEntity.builder()
                    .id(noticeDto.getMemberId())
                    .build());
            NoticeEntity noticeEntity = NoticeEntity.toInsertFileNoticeEntity(noticeDto);
            Long id = noticeRepository.save(noticeEntity).getId();

            Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
            if (optionalNoticeEntity.isPresent()) { // -> 게시글이 존재한다면
                //게시글 0
                NoticeEntity noticeEntity1 = optionalNoticeEntity.get(); //Entity

                //게시글이 저장되면 -> 파일을 Entity에 저장

                NoticeFileDto fileDto = NoticeFileDto.builder()
                        .noticeOldFileName(oldFileName)
                        .noticeNewFileName(newFileName)
                        .noticeEntity(noticeEntity1)
                        .build();

                NoticeFileEntity fileEntity = NoticeFileEntity.toInsertNoticeFile(fileDto);

                noticeFileRepository.save(fileEntity);

            } else {
                throw new IllegalArgumentException("아이디가 없습니다");
            }
        }
    }

    @Override
    public void updateNoticeHit(Long id) {
        noticeRepository.updateNoticeHit(id);
    }


}
