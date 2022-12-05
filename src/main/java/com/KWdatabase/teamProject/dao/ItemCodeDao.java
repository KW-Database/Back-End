package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemCodeDao {
    public ItemCode getItemCode(String itemCode);

    public List<ItemCode> getItemCodeList();

    public List<ItemCode> getItemCodeListByName(String itemName);
    public void insertItemCode(@Param("item")ItemCode itemCode);
}
