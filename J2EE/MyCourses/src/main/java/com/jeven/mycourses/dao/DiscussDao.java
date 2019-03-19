package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by jeven on 2019/3/19.
 */
@Repository
@Table(name = "discuss")
public interface DiscussDao extends JpaRepository<Discuss,Integer> {
    List<Discuss> getDiscussesByCid(int cid);
}
