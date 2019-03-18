package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.FileDao;
import com.jeven.mycourses.domain.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeven on 2019/3/18.
 */
@Service
public class FileService {
    @Autowired
    private FileDao fileDao;

    public List<File> getFilesByCidAndType(int cid,int type){
        return fileDao.getFilesByCidAndType(cid,type);
    }
}
