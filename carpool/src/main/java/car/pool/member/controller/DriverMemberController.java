package car.pool.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import car.pool.member.domain.DriverMemberCommand;
import car.pool.member.domain.MemberCommand;
import car.pool.member.service.DriverMemberService;
import car.pool.member.service.MemberService;

@Controller
public class DriverMemberController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private DriverMemberService driverMemberService;
	
	
	@ModelAttribute("driverCommand")
	public DriverMemberCommand initCommand(){
		return new DriverMemberCommand();
	}
	
	//운전자등록 폼
	@RequestMapping(value="/member/driverWrite.do", method=RequestMethod.GET)
	public String driverWriteForm(HttpSession session, Model model){
		
		String id = (String)session.getAttribute("user_id");
		
		DriverMemberCommand driverMember = new DriverMemberCommand();
		driverMember.setMem_id(id);
		
		model.addAttribute("driverCommand", driverMember);
		
		return "driverWrite";	
	}
	
	//운전자 등록
	@RequestMapping(value="/member/driverWrite.do", method=RequestMethod.POST)
	public String driverWrite(@ModelAttribute("driverCommand") @Valid DriverMemberCommand drivermemberCommand, BindingResult result){
		
		if(log.isDebugEnabled())
			log.debug("drivermemberCommand : " + drivermemberCommand);
		
		if(result.hasErrors())
			return "driverWrite";
		
		driverMemberService.insert(drivermemberCommand);
		
		return "redirect:/main/main.do";	
	}
	
}
