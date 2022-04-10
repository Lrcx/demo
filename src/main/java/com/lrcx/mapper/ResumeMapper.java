package com.lrcx.mapper;

import com.lrcx.pojo.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResumeMapper {
    List<Resume> queryAllResume();
    int insertResume(Resume resume);
    Resume queryResume(String name);
    int deleteResume(String name);
    int editResume(String oldname,String newname);
}
