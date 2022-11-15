package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyDao {

    Company getCompany(@Param("com") Company company);
    void insertCompany(@Param("com")Company company);
}
