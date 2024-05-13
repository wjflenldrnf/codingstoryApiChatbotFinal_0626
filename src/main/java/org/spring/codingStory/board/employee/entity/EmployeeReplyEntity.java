package org.spring.codingStory.board.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.contraint.BaseTimeEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "emp_reply")
public class EmployeeReplyEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_reply_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String empReplyWriter;

    @Column(nullable = false, length = 500)
    private String empReplyContent;

    @JsonIgnore // ajax시 순환0참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;


}
