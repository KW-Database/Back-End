package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemTimeConditionDao {
    public ItemTimeCondition getItemTimeCondition(@Param("condition") ItemTimeCondition itemTimeCondition);
    public void insertItemTimeCondition(@Param("condition") ItemTimeCondition itemTimeCondition);
    public ItemTimeCondition getNewCondition(String itemCode);
}
