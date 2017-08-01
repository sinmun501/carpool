package car.pool.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import car.pool.member.domain.DriverMemberCommand;

public interface DriverMemberMapper {

	@Select("SELECT car_seq.nextval FROM DUAL")
	public int getSeq();
	
	@Insert("INSERT INTO car (car_seq,car_image,car_filename,car_registration_num,car_model) "
			+ "VALUES (#{car_seq},#{car_image,jdbcType=BLOB},#{car_filename,jdbcType=VARCHAR},#{car_registration_num},#{car_model})")
	public void insertCar(DriverMemberCommand driverMember);
	
	@Insert("INSERT INTO driver (driver_seq,driver_license_num,driver_bank,driver_account,driver_can_animal,driver_can_smoking,driver_can_charge,driver_date,mem_id,car_seq)"
			+ " VALUES (driver_seq.nextval,#{driver_license_num},#{driver_bank},#{driver_account},#{driver_can_animal},#{driver_can_smoking},#{driver_can_charge},sysdate,#{mem_id},#{car_seq})")
	public void insertDriver(DriverMemberCommand driverMember);
	
	@Insert("UPDATE member set mem_driver=2 WHERE mem_id=#{mem_id}")
	public void insert(DriverMemberCommand driverMember);
	
	
}
