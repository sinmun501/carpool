package car.pool.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import car.pool.member.dao.MemberMapper;
import car.pool.member.domain.MemberCommand;

@Service("MemberService")
public class MemberServiceImpl implements MemberService{

	@Resource
	MemberMapper memberMapper;
	
	@Override
	public void insert(MemberCommand member) {
		memberMapper.insert(member);
		memberMapper.insertDetail(member);
	}

	@Override
	public MemberCommand selectMember(String mem_id) {
		return memberMapper.selectMember(mem_id);
	}

	@Override
	public void update(MemberCommand member) {
		memberMapper.update(member);
	}

	@Override
	public void delete(String id) {
		memberMapper.delete(id);
		memberMapper.deleteDetail(id);
	}

}
