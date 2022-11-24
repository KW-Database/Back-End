package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.dto.LikedItemListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikedItemDao {
    public List<LikedItemListDto> getList(String id);
}
