package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.Model.ItemCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemConditionDao {
    public ItemCondition getItemCondition(@Param("condition")ItemCondition itemCondition);
    public void insertItemCondition(@Param("condition")ItemCondition itemCondition);
}
