package org.spring.codingStory.fullcalender.dto;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullCalenderDto {

  private Integer id;

  private String content;

  private Date start;

  private Date end;
}
