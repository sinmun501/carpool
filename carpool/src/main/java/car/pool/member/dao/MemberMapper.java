package car.pool.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import car.pool.member.domain.MemberCommand;

public interface MemberMapper {

	@Insert("INSERT INTO member(mem_id) VALUES (#{mem_id})")
	public void insert(MemberCommand member);
	
	@Insert("INSERT INTO member_detail (mem_id, mem_pw, mem_name, mem_gender, mem_phone, mem_email, mem_date, mem_image, mem_filename) "
			+ "VALUES (#{mem_id},#{mem_pw},#{mem_name},#{mem_gender},#{mem_phone},#{mem_email},sysdate,#{mem_image,jdbcType=BLOB},#{mem_filename,jdbcType=VARCHAR})")
	public void insertDetail(MemberCommand member);
	
	@Select("SELECT * FROM member m LEFT OUTER JOIN member_detail d "
			+ "on m.mem_id=d.mem_id "
			+ "where m.mem_id=#{mem_id}")
	public MemberCommand selectMember(String mem_id);
	
	@Update("UPDATE member_detail SET "
			+ "mem_pw=#{mem_pw},mem_name=#{mem_name},mem_gender=#{mem_gender},mem_phone=#{mem_phone},mem_email=#{mem_email},mem_image=#{mem_image,jdbcType=BLOB},mem_filename=#{mem_filename,jdbcType=VARCHAR}"
			+ "WHERE mem_id=#{mem_id}")
	public void update(MemberCommand member);
	public void delete(String id);
	public void deleteDetail(String id);
}
