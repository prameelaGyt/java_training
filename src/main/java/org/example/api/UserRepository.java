package org.example.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;



@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_group (uid, gid) " +
            "SELECT :userId, :groupId " +
            "WHERE EXISTS (SELECT 1 FROM user WHERE uid = :userId) " +
            "AND EXISTS (SELECT 1 FROM grp WHERE gid = :groupId)",
            nativeQuery = true)
    int addUserToGroupIfExists(@Param("userId") int userId, @Param("groupId") int groupId);


    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<User> findAllWithSearch(@Param("searchTerm") String searchTerm, Pageable pageable);

}
