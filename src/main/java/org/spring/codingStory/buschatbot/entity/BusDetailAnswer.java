package org.spring.codingStory.buschatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.codingStory.buschatbot.dto.BusDetailAnswerDto;

import javax.persistence.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_detail_answer")
@Entity
public class BusDetailAnswer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long no;

  private String content;

  private String keyword;    //name

  public BusDetailAnswer keyword(String keyword) {
    this.keyword=keyword;
    return this;
  }

  public BusDetailAnswerDto toAnswerDetailDTO() {
    return BusDetailAnswerDto.builder()
            .no(no).content(content).keyword(keyword)
            .build();
  }
}
