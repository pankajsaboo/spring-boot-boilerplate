package increpe.order.mgmt.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import increpe.order.mgmt.repository.AttendanceRepository;
import increpe.order.mgmt.security.dto.RegistrationResponse;
import increpe.order.mgmt.security.mapper.CompanyMapper;
import increpe.order.mgmt.sp.dto.AttendanceDto;

@Service
public class AttendanceService {
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	public RegistrationResponse addNewAttendance(AttendanceDto aDto) {
		
		attendanceRepository.save(CompanyMapper.INSTANCE.convertToAttendance(aDto));
		
		return new RegistrationResponse("Attendance added successfully!");
	}

	public List<AttendanceDto> getAttendanceByMonth(String monthYear, Long salesPersonId) {
		
		int year = Integer.parseInt(monthYear.split(" ")[1]);
		
		Month month = Month.valueOf(monthYear.split(" ")[0]);
		
		LocalDate startDate = LocalDate.of(year,month,1);
		
		LocalDate endDate = LocalDate.of(year,month,startDate.lengthOfMonth());
		
		return CompanyMapper.INSTANCE.convertToAttendanceDtoList(
					attendanceRepository.findBySalesPersonId_idAndStartTimeBetween(salesPersonId, 
									LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX)));
	}
}
