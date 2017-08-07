package car.pool.member.service;

import org.springframework.transaction.annotation.Transactional;

import car.pool.member.domain.MemberCommand;

@Transactional
public interface MemberService {

	public void insert(MemberCommand member);
	@Transactional(readOnly=true)
	public MemberCommand selectMember(String id);
	public void update(MemberCommand member);
	public void delete(String id);
	
	/*@Transactional(readOnly=true)
	public MemberCommand searchMemberId(String e_mail);*/
	
	
}
