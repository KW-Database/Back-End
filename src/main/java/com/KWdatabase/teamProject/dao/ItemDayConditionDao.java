package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemDayConditionDao {
    public List<ItemDayCondition> getItemDayCondition(String itemCode);
    public void insertItemDayCondition(@Param("condition") ItemDayCondition itemDayCondition);
    public ItemDayCondition getLatestCondition (String itemId);
}
