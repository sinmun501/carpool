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
	
	//운전자 정보확인 (상세)
	@RequestMapping("/member/driverDetail.do")
	public String driverMypage(HttpSession session, Model model){ 

		//session에서 id를 끄집어내서 1건의 데이터를 가져옴.
		String id = (String)session.getAttribute("user_id");

		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(id);

		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + driverMember);

		model.addAttribute("driverMember", driverMember);

		return "driverDetail";
	}
	
	// 마이페이지 이미지 출력
	@RequestMapping("/member/carimageView.do")
	public ModelAndView viewImage(@RequestParam("mem_id") String mem_id) {

		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(mem_id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");   // imageView 객체 호출
		mav.addObject("imageFile", driverMember.getCar_image());
		mav.addObject("filename", driverMember.getCar_filename());

		return mav;
	}
	
	
	//운전자 수정 폼
	@RequestMapping(value="/member/driverUpdate.do", method=RequestMethod.GET)
	public String driverUpdateForm(HttpSession session, Model model){ 

		String id = (String)session.getAttribute("user_id");

		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(id);
		model.addAttribute("driverCommand", driverMember);

		return "driverUpdate";
	}
	
	//운전자 수정
	@RequestMapping(value="/member/driverUpdate.do", method=RequestMethod.POST)
	public String update(@ModelAttribute("driverCommand") @Valid DriverMemberCommand drivermemberCommand, BindingResult result, HttpSession session){
		
		if(log.isDebugEnabled())
			log.debug("<<drivermemberCommand>> : " + drivermemberCommand);
		
		if(result.hasErrors()){
			return "driverUpdate";
		}
		
		String id = (String)session.getAttribute("user_id");
		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(id);
		
		//전송된 파일이 없을 경우
		if(drivermemberCommand.getCar_upload().isEmpty()){
			//기존 정보 셋팅
			drivermemberCommand.setCar_image(driverMember.getCar_image());
			drivermemberCommand.setCar_filename(driverMember.getCar_filename());
		}
		
		// 운전자 정보 수정. 
		driverMemberService.driverUpdate(drivermemberCommand);
		
		return "redirect:/member/driverDetail.do";
	}
	
}
