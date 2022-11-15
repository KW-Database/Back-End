package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemCodeDao {
    public ItemCode getItemCode(@Param("item")ItemCode itemCode);
    public void insertItemCode(@Param("item")ItemCode itemCode);
}
