package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateCalculation;
import com.example.demo.repository.CalculationMapper;

@Service
public class CalculationService implements CalculationMapper {

	@Autowired
	private CalculationMapper calcMapper;

	@Override
	public List<DateCalculation> selectAll() {
		return calcMapper.selectAll();
	}
	
	@Override
	public DateCalculation selectOne(int id) {
		return calcMapper.selectOne(id);
	}

	@Override
	public void insert(DateCalculation date) {
		calcMapper.insert(date);
	}

	@Override
	public void update(DateCalculation date) {
		calcMapper.update(date);
	}

	@Override
	public void delete(int id) {
		calcMapper.delete(id);
	}
}
