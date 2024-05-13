package org.spring.codingStory.member.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "member_file")
public class MemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_file_id")
    private Long id;

    @Column(nullable = false)
    private String memberNewFileName;

    @Column(nullable = false)
    private String memberOldFileName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;



}
