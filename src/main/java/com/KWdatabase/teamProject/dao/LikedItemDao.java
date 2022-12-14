package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.LikedItemListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikedItemDao {
    public List<LikedItemListDto> getList(String id);
    public void deleteLikedItem(String id, String itemCode);
    public void insertLikedItem(String id, String itemCode);
    public void decreaseLikedItem(String itemCode);
    public void increaseLikedItem(String itemCode);

}
