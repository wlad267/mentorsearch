package com.bluementors.mentor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    @Query("update Mentor set active= false where id= :uid")
    Mentor cancelMentorig(@Param("uid") Long uid);
}
