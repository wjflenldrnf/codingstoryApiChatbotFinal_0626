package org.spring.codingStory.movieApi.movie.repository;


import org.spring.codingStory.movieApi.movie.entity.WeeklyMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeeklyMovieRepository extends JpaRepository<WeeklyMovieEntity, Long> {

    Optional<WeeklyMovieEntity> findByMovieCd(String movieCd);

}