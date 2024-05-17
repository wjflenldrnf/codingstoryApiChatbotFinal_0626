package org.spring.codingStory.fullcalender.dto;

import lombok.*;
import org.spring.codingStory.member.entity.MemberEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullCalenderDto {

  private Integer id;

  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date start;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date end;

  private MemberEntity memberEntity;
}
