package com.developmentappws.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentappws.app.ws.io.entity.AddressEntity;
import com.developmentappws.app.ws.io.entity.UserEntity;
import com.developmentappws.app.ws.io.repository.AddressRepository;
import com.developmentappws.app.ws.io.repository.UserRepository;
import com.developmentappws.app.ws.service.AddressService;
import com.developmentappws.app.ws.shared.dto.AddressDTO;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRespository;

	@Override
	public List<AddressDTO> getAddresses(String userId) {
		List<AddressDTO> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity==null) return returnValue;
		
		Iterable<AddressEntity> addresses = addressRespository.findAllByUserDetails(userEntity);
		for(AddressEntity addressEntity:addresses)
		{
			returnValue.add(modelMapper.map(addressEntity, AddressDTO.class));
		}
		return returnValue;
	}

	@Override
	public AddressDTO getAddress(String addressId) {
		AddressDTO returnValue = null;
		
		AddressEntity addressEntity = addressRespository.findByAddressId(addressId);
		if(addressEntity!=null) {
			returnValue = new ModelMapper().map(addressEntity, AddressDTO.class);
		}
		
		return returnValue;
	}

}
