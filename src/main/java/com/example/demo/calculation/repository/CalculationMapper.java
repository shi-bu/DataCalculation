package com.example.demo.calculation.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.calculation.model.DateCalculation;

@Mapper
public interface CalculationMapper {

	public List<DateCalculation> selectAll();
	public void insert(DateCalculation date);
	public void update(DateCalculation date);
	public void delete(String dateId);
}
