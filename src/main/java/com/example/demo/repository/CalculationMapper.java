package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.DateCalculation;

@Mapper
public interface CalculationMapper {

	public List<DateCalculation> selectAll();
	public DateCalculation selectOne(int id);
	public void insert(DateCalculation date);
	public void update(DateCalculation date);
	public void delete(int id);
}
