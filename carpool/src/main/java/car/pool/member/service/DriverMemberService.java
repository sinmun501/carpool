package car.pool.member.service;

import org.springframework.transaction.annotation.Transactional;

import car.pool.member.domain.DriverMemberCommand;

@Transactional
public interface DriverMemberService {

	public void insert(DriverMemberCommand driverMember);
	@Transactional(readOnly=true)
	public DriverMemberCommand driverSelectMember(String mem_id);
	public void driverUpdate(DriverMemberCommand driverMember);
}
