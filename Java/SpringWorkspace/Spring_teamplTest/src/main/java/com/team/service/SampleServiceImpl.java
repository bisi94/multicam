package com.team.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.team.mapper.SampleMapper;

@Service
public class SampleServiceImpl implements SampleService {

	@Inject
	private SampleMapper sampleMapper;
	
	@Override
	public int getTableCount() {

		return this.sampleMapper.getTableCount();
	
	}

}
