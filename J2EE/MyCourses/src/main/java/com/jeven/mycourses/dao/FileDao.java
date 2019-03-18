package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by jeven on 2019/3/18.
 */
@Repository
@Table(name = "files")
public interface FileDao extends JpaRepository<File,Integer> {
    List<File> getFilesByCidAndType(int cid,int type);
}
