package increpe.order.mgmt.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import increpe.order.mgmt.model.Company;
import increpe.order.mgmt.model.CompanyType;
import increpe.order.mgmt.model.CompanyTypeRelation;
import increpe.order.mgmt.repository.CompanyTypeRelationRepository;
import increpe.order.mgmt.security.dto.CompanyDto;
import increpe.order.mgmt.security.dto.CompanyTypeDto;
import increpe.order.mgmt.security.dto.CompanyTypeRelationDto;
import increpe.order.mgmt.security.mapper.CompanyMapper;

@Service
public class CompanyTypeRelationService {

	@Autowired
	CompanyTypeRelationRepository companyTypeRelationRepository;

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyTypeService companyTypeService;

	
	public CompanyTypeRelation createRelation(CompanyTypeRelationDto companyTypeRelationDto) {
		
		CompanyTypeRelation relation = convertRelationDtoToRelation(companyTypeRelationDto);

		CompanyType companyType = companyTypeService.getCompanyType(relation.getCompanyTypeId().getId());
		
		if(Objects.isNull(relation.getCompanyId().getId())) {
			
			Company company = companyService.createCompany(relation.getCompanyId());
			
			relation.setCompanyId(company);
			relation.setCompanyTypeId(companyType);	
		}else {
			
			Company company = companyService.getCompany(relation.getCompanyId().getId());
			
			relation.setCompanyId(company);
			relation.setCompanyTypeId(companyType);
		}
		
		return companyTypeRelationRepository.save(relation);
	}
	
	public CompanyTypeRelationDto getRelation(Long id) {

		return companyTypeRelationRepository.existsById(id)
				? convertRelationToRelationDto(companyTypeRelationRepository.findById(id).get())
				: null;
	}

	
	public CompanyTypeRelationDto getRelationByCompany(CompanyDto companyDto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<CompanyTypeRelationDto> getRelationByCompanyType(CompanyTypeDto companyTypeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CompanyTypeRelation convertRelationDtoToRelation(CompanyTypeRelationDto companyTypeRelationDto) {

		return CompanyMapper.INSTANCE.convertToCompanyTypeRelation(companyTypeRelationDto);
	}

	
	public CompanyTypeRelationDto convertRelationToRelationDto(CompanyTypeRelation companyTypeRelation) {

		return CompanyMapper.INSTANCE.convertToCompanyTypeRelationDto(companyTypeRelation);
	}


}
