package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Holdings;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HoldingsDao {
    public List<Holdings> getHoldings(String id);
    public Holdings findHolding(String id, String itemCode);
    public int getHoldingNumber(String id , String itemCode);
    public void deleteHoldings(String id, String itemCode);
}
