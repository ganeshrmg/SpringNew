package com.duediligence.service;


import org.springframework.stereotype.Service;

import com.duediligence.model.Duediligence;


public interface DueService {

	Duediligence getDueStatus(Long id);

}
