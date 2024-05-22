package org.spring.codingStory.department.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(nullable = false)
    private String dptName;

    @Column(nullable = false)
    private String location; // 부서 위치 필드 추가


    @Column(nullable = false)
    private int memberCount;


    // 소속 인원 수를 반환하는 메서드
    public int countMembers() {
        return memberEntityList != null ? memberEntityList.size() : 0;
    }


    @JsonIgnore // ajax시 순환참조 방지
    @OneToMany(mappedBy = "departmentEntity"
            , fetch = FetchType.LAZY
            , cascade = CascadeType.REMOVE)
    private List<MemberEntity> memberEntityList;


}
