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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import car.pool.member.domain.DriverMemberCommand;
import car.pool.member.domain.MemberCommand;
import car.pool.member.service.DriverMemberService;
import car.pool.member.service.MemberService;

@Controller
public class DriverMemberController {
//111
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private DriverMemberService driverMemberService;
	
	
	@ModelAttribute("driverCommand")
	public DriverMemberCommand initCommand(){
		return new DriverMemberCommand();
	}
	
	//�����ڵ�� ��
	@RequestMapping(value="/member/driverWrite.do", method=RequestMethod.GET)
	public String driverWriteForm(HttpSession session, Model model){
		
		String id = (String)session.getAttribute("user_id");
		
		DriverMemberCommand driverMember = new DriverMemberCommand();
		driverMember.setMem_id(id);
		
		model.addAttribute("driverCommand", driverMember);
		
		return "driverWrite";
	}
	
	//������ ���
	@RequestMapping(value="/member/driverWrite.do", method=RequestMethod.POST)
	public String driverWrite(@ModelAttribute("driverCommand") @Valid DriverMemberCommand drivermemberCommand, BindingResult result){
		
		if(log.isDebugEnabled())
			log.debug("drivermemberCommand : " + drivermemberCommand);
		
		if(result.hasErrors())
			return "driverWrite";
		
		driverMemberService.insert(drivermemberCommand);
		
		return "redirect:/main/main.do";
	}
	
	//������ ����Ȯ�� (��)
	@RequestMapping("/member/driverDetail.do")
	public String driverMypage(HttpSession session, Model model){ 

		//session���� id�� ������� 1���� �����͸� ������.
		String id = (String)session.getAttribute("user_id");

		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(id);

		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + driverMember);

		model.addAttribute("driverMember", driverMember);

		return "driverDetail";
	}
	
	// ���������� �̹��� ���
	@RequestMapping("/member/carimageView.do")
	public ModelAndView viewImage(@RequestParam("mem_id") String mem_id) {

		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(mem_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");   // imageView ��ü ȣ��
		mav.addObject("imageFile", driverMember.getCar_image());
		mav.addObject("filename", driverMember.getCar_filename());

		return mav;
	}
	
	
	//������ ���� ��
	@RequestMapping(value="/member/driverUpdate.do", method=RequestMethod.GET)
	public String driverUpdateForm(HttpSession session, Model model){ 

		String id = (String)session.getAttribute("user_id");

		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(id);
		model.addAttribute("driverCommand", driverMember);

		return "driverUpdate";
	}
	
	//������ ����
	@RequestMapping(value="/member/driverUpdate.do", method=RequestMethod.POST)
	public String update(@ModelAttribute("driverCommand") @Valid DriverMemberCommand drivermemberCommand, BindingResult result, HttpSession session){
		
		if(log.isDebugEnabled())
			log.debug("<<drivermemberCommand>> : " + drivermemberCommand);
		
		if(result.hasErrors()){
			return "driverUpdate";
		}
		
		String id = (String)session.getAttribute("user_id");
		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(id);
		
		//���۵� ������ ���� ���
		if(drivermemberCommand.getCar_upload().isEmpty()){
			//���� ���� ����
			drivermemberCommand.setCar_image(driverMember.getCar_image());
			drivermemberCommand.setCar_filename(driverMember.getCar_filename());
		}
		
		// ������ ���� ����. 
		driverMemberService.driverUpdate(drivermemberCommand);
		
		return "redirect:/member/driverDetail.do";
	}
	
}
