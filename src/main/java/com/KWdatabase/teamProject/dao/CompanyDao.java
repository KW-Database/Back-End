package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Company;
import com.KWdatabase.teamProject.Model.PublicDateRankDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyDao {

    public Company getCompany(@Param("com") Company company);
    void insertCompany(@Param("com")Company company);

    void updateCompany(@Param("com")Company company);

    public List<PublicDateRankDto> getPublicDateList();

    public List<Company> getCompanyList();

    public void deleteCompany(String itemCode);

}
