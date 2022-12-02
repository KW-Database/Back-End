package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Shareholder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShareholderDao {
    public List<Shareholder> getShareholderList(String itemCode);

    public void insertShareholderData(@Param("sh")Shareholder shareholder);

    public void updateShareholderData(@Param("sh")Shareholder shareholder,@Param("ch")Shareholder changes);


}
