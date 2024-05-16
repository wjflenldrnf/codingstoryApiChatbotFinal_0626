package org.spring.codingStory.board.freeBoard.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.spring.codingStory.board.freeBoard.dto.FreeDto;
import org.spring.codingStory.board.freeBoard.dto.FreeFileDto;
import org.spring.codingStory.board.freeBoard.entity.FreeEntity;
import org.spring.codingStory.board.freeBoard.entity.FreeFileEntity;
import org.spring.codingStory.board.freeBoard.repository.FreeFileRepository;
import org.spring.codingStory.board.freeBoard.repository.FreeRepository;
import org.spring.codingStory.board.freeBoard.serviceImpl.service.FreeService;
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
public class FreeServiceImpl implements FreeService {

    private final FreeRepository freeRepository;
    private final FreeFileRepository freeFileRepository;


    @Override
    public void freeInsertFile(FreeDto freeDto) throws IOException {
        if (freeDto.getBoardFile().isEmpty()) {
            //파일 없는 경우
            freeDto.setMemberEntity(MemberEntity.builder()
                    .id(freeDto.getMemberId())
                    .build());
            FreeEntity freeEntity = FreeEntity.toInsertFreeEntity(freeDto);
            freeRepository.save(freeEntity);
        } else {
            ////////////////////////////////////파일이 실제 경로에 저장//////////////////////////////
            MultipartFile boardFile = freeDto.getBoardFile();// 1. 파일을 가져온다. Dto
            // 이름 암호화 -> DB 저장, local에 저장 할 이름
            String oldFileName = boardFile.getOriginalFilename();// 원본파일 이름
            UUID uuid = UUID.randomUUID(); //random id -> 랜덤한 값을 추출하는 플래스
            String newFileName = uuid + "_" + oldFileName; // 저장파일이름 (보완)

            String filePath = "C:/CodingStory_file/" + newFileName; // 실제 파일 저장경로+이름
            //실제파일 저장 실행
            boardFile.transferTo(new File(filePath));//저장, 예외처리 -> 경로에 파일 저장

            // 1. 게시글 ->
            freeDto.setMemberEntity(MemberEntity.builder()
                    .id(freeDto.getMemberId())
                    .build());
            FreeEntity freeEntity = FreeEntity.toInsertFileFreeEntity(freeDto);
            Long id = freeRepository.save(freeEntity).getId();

            Optional<FreeEntity> optionalFreeEntity = freeRepository.findById(id);
            if (optionalFreeEntity.isPresent()) { // -> 게시글이 존재한다면
                //게시글 0
                FreeEntity freeEntity1 = optionalFreeEntity.get(); //Entity

                //게시글이 저장되면 -> 파일을 Entity에 저장

                FreeFileDto fileDto = FreeFileDto.builder()
                        .freeOldFileName(oldFileName)
                        .freeNewFileName(newFileName)
                        .freeEntity(freeEntity1)
                        .build();

                FreeFileEntity fileEntity = FreeFileEntity.toInsertFreeFile(fileDto);

                freeFileRepository.save(fileEntity);

            } else {
                throw new IllegalArgumentException("아이디가 없습니다");
            }
        }
    }

    @Override
    public void updateFreeHit(Long id) {
        freeRepository.updateFreeHit(id);
    }


}
