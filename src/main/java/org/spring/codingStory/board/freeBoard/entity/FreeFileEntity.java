package org.spring.codingStory.board.freeBoard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.board.employee.entity.EmployeeEntity;
import org.spring.codingStory.contraint.BaseTimeEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "freeBoard_file")
public class FreeFileEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_file_id")
    private Long id;

    @Column(nullable = false)
    private String freeNewFileName;

    @Column(nullable = false)
    private String freeOldFileName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "free_id")
    private FreeEntity freeEntity;


}
