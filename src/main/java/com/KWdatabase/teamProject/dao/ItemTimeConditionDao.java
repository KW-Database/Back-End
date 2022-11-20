package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemTimeConditionDao {
    public List<ItemTimeCondition> getItemTimeCondition(String itemCode);
    public void insertItemTimeCondition(@Param("condition") ItemTimeCondition itemTimeCondition);

    public void deleteAllTimeSise();
}
