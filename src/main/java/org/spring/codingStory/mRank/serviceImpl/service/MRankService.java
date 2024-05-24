package org.spring.codingStory.mRank.serviceImpl.service;

import org.spring.codingStory.mRank.dto.RankDto;
import org.spring.codingStory.mRank.entity.RankEntity;

import java.util.List;

public interface MRankService {
  List<RankDto> findRank();

}
