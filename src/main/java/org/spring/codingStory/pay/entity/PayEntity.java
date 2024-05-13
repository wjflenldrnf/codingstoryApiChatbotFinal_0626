package org.spring.codingStory.pay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.spring.codingStory.contraint.BaseTimeEntity;
import org.spring.codingStory.member.entity.MemberEntity;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "pay")
public class PayEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private String payMon;

    @Column(nullable = false)
    private String payBns;

    @Column(nullable = false)
    private LocalDate paymentDate;



}
