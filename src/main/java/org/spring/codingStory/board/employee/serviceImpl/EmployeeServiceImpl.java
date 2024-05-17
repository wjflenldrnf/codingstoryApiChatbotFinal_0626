package org.spring.codingStory.board.employee.serviceImpl;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import lombok.RequiredArgsConstructor;
import org.spring.codingStory.board.employee.dto.EmployeeDto;
import org.spring.codingStory.board.employee.dto.EmployeeFileDto;
import org.spring.codingStory.board.employee.entity.EmployeeEntity;
import org.spring.codingStory.board.employee.entity.EmployeeFileEntity;
import org.spring.codingStory.board.employee.repository.EmpFileRepository;
import org.spring.codingStory.board.employee.repository.EmployeeRepository;
import org.spring.codingStory.board.employee.serviceImpl.service.EmployeeService;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmpFileRepository empFileRepository;

    @Override
    public void empInsertFile(EmployeeDto empDto) throws IOException {
        if (empDto.getBoardFile().isEmpty()) {
            //파일 없는 경우
            empDto.setMemberEntity(MemberEntity.builder()
                    .id(empDto.getMemberId())
                    .build());
            EmployeeEntity empEntity = EmployeeEntity.toInsertEmpEntity(empDto);
            employeeRepository.save(empEntity);
        } else {
            ////////////////////////////////////파일이 실제 경로에 저장//////////////////////////////
            MultipartFile boardFile = empDto.getBoardFile();// 1. 파일을 가져온다. Dto
            // 이름 암호화 -> DB 저장, local에 저장 할 이름
            String oldFileName = boardFile.getOriginalFilename();// 원본파일 이름
            UUID uuid = UUID.randomUUID(); //random id -> 랜덤한 값을 추출하는 플래스
            String newFileName = uuid + "_" + oldFileName; // 저장파일이름 (보완)

            String filePath = "C:/CodingStory_file/" + newFileName; // 실제 파일 저장경로+이름
            //실제파일 저장 실행
            boardFile.transferTo(new File(filePath));//저장, 예외처리 -> 경로에 파일 저장

            // 1. 게시글 ->
            empDto.setMemberEntity(MemberEntity.builder()
                    .id(empDto.getMemberId())
                    .build());
            EmployeeEntity empEntity = EmployeeEntity.toInsertFileEmpEntity(empDto);
            Long id = employeeRepository.save(empEntity).getId();

            Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
            if (optionalEmployeeEntity.isPresent()) { // -> 게시글이 존재한다면
                //게시글 0
                EmployeeEntity empEntity1 = optionalEmployeeEntity.get(); //Entity

                //게시글이 저장되면 -> 파일을 Entity에 저장

                EmployeeFileDto fileDto = EmployeeFileDto.builder()
                        .empOldFileName(oldFileName)
                        .empNewFileName(newFileName)
                        .employeeEntity(empEntity1)
                        .build();

                EmployeeFileEntity fileEntity = EmployeeFileEntity.toInsertEmpFile(fileDto);

                empFileRepository.save(fileEntity);

            } else {
                throw new IllegalArgumentException("아이디가 없습니다");
            }
        }
    }

    @Override
    public void updateEmpHit(Long id) {
        employeeRepository.updateEmpHit(id);
    }
}
