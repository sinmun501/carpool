package car.pool.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import car.pool.member.dao.DriverMemberMapper;
import car.pool.member.domain.DriverMemberCommand;

@Service("DriverMemberService")
public class DriverMemberServiceImpl implements DriverMemberService{

	@Resource
	DriverMemberMapper driverMapper;
	
	@Override
	public void insert(DriverMemberCommand driverMember) {
		driverMember.setCar_seq(driverMapper.getSeq());
		driverMapper.insertCar(driverMember);
		driverMapper.insertDriver(driverMember);
		driverMapper.insert(driverMember);
	}

}
