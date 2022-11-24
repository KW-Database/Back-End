package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.Model.ItemInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemTimeConditionDao {
    public List<ItemTimeCondition> getItemTimeCondition(String itemCode);
    public void insertItemTimeCondition(@Param("condition") ItemTimeCondition itemTimeCondition);
    public void deleteAllTimeSise();
    public ItemTimeCondition getNewCondition(String itemCode);
    public List<ItemInfoResponseDto>getVolumeRank();
}
