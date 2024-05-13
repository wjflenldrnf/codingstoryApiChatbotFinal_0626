package org.spring.codingStory.approval.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "apv_file")
public class ApprovalFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apv_file_id")
    private Long id;

    @Column(nullable = false)
    private String apvNewFileName;

    @Column(nullable = false)
    private String avpOldFileName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apv_id")
    private ApprovalEntity approvalEntity;

}
