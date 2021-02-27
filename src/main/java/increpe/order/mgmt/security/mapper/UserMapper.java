package increpe.order.mgmt.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import increpe.order.mgmt.model.User;
import increpe.order.mgmt.model.UserType;
import increpe.order.mgmt.security.dto.AuthenticatedUserDto;
import increpe.order.mgmt.security.dto.SalesPersonDto;
import increpe.order.mgmt.security.dto.UserTypeDto;

/**
 * 
 *
 * 
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	//User convertToUser(RegistrationRequest registrationRequest);

	AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

	User convertToUser(AuthenticatedUserDto authenticatedUserDto);
	
	AuthenticatedUserDto convertToAuthenticatedUserDto(SalesPersonDto salesPersonDto);
	
	UserTypeDto convertToUserTypeDto(UserType userType);
	
	UserType convertToUserType(UserTypeDto userTypeDto);

}
