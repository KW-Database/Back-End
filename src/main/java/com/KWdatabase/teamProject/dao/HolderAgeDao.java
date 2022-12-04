package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.HolderAge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HolderAgeDao {
    public List<HolderAge> getHolderAgeList(String itemCode);
    public HolderAge getHolderAgeByAges(String itemCode, int ages);

    public void updateHolderAge(@Param("holder") HolderAge holderAge);

    public void insertHolderAge(@Param("holder") HolderAge holderAge);
}
