package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemDayConditionDao {
    public ItemDayCondition getItemDayCondition(@Param("condition") ItemDayCondition itemDayCondition);
    public void insertItemDayCondition(@Param("condition") ItemDayCondition itemDayCondition);
}
