package org.spring.codingStory.member.dto;

import lombok.*;
import org.spring.codingStory.department.entity.DepartmentEntity;
import org.spring.codingStory.mRank.entity.RankEntity;
import org.spring.codingStory.member.entity.MemberFileEntity;
import org.spring.codingStory.member.role.Role;
import org.spring.codingStory.pay.entity.PayEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {

    private Long id;

    private String userEmail;

    private String userPw;

    private String name;

    private String department;

    private String mRank;

    private String address;

    private String phoneNumber;

    private Role role;

    private String memberFileName;

    private MultipartFile memberFile;

    private String memberNewFileName;

    private String memberOldFileName;

    private int memberAttachFile;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private DepartmentEntity departmentEntity;

    private RankEntity rankEntity;

    private List<PayEntity> PayEntityList;

    private List<MemberFileEntity> memberFileEntityList;


}
