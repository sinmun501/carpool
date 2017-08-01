package car.pool.member.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import car.pool.member.domain.MemberCommand;
import car.pool.member.service.MemberService;

@Controller
public class MemberController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	//커맨드 객체 초기화 (커스텀 태그를 사용하기 위함.)
	@ModelAttribute("command")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	
	//회원가입 폼
	@RequestMapping(value="/member/write.do", method=RequestMethod.GET)
	public String writeForm(){
		return "memberWrite";	
	}
	//회원가입
	@RequestMapping(value="/member/write.do", method=RequestMethod.POST)
	public String writeSubmit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result){
		
		if(log.isDebugEnabled())
			log.debug("memberCommand : " + memberCommand);
		
		if(result.hasErrors())
			return writeForm();
		
		memberService.insert(memberCommand);
		
		return "redirect:/main/main.do";
	}
	
	
	//로그인 폼
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String loginForm(){
		return "memberLogin"; 
	}
	
	//로그인
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String login(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		
		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + memberCommand);
		
		// id와 passwd 필드만 체크!
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd"))
			return loginForm();
		
		// 로그인 체크(ID 또는 비밀번호 일치 여부 체크)
		try{
			MemberCommand member = memberService.selectMember(memberCommand.getMem_id());
			boolean check = false;
			
			if(member != null){
				//비밀번호 일치여부 체크
				check = member.isCheckedPasswd(memberCommand.getMem_pw());
			}
			
			if(check){
				//인증성공, 로그인 처리
				session.setAttribute("user_id", memberCommand.getMem_id());
				// auth를 session에 넣어두고, session에 넣어진 auth값이 2면 관리자가 활용가능한 메뉴들이 보여진다.
				session.setAttribute("user_auth", memberCommand.getMem_auth()); // 0은 탈퇴회원, 1은 일반회원, 2는 관리자회원 으로 auth까지 session에 넣어두면 관리자까지 확장가능.
			
				return "redirect:/main/main.do";
			}else{
				//인증실패
				throw new Exception();
			}
			
		}catch(Exception e){
			//인증실패로 폼 호출
			result.reject("invalidIdOrPassword");
			return loginForm();
		}
	}
	
	//로그인 시 ID 중복체크
	@RequestMapping("/member/confirmId.do")
	@ResponseBody 
	public Map<String, String> confirmId(@RequestParam("mem_id") String mem_id){
		
		/*
		 	JSON 문자열로 만듬. (JSON처리할 때는 tiles처리를 하면 안됨. map을 반환만 해주면 tiles를 사용하지않고 viewResolver쪽 order=2 차례로가서 처리된다.)
		*/
		if(log.isDebugEnabled())
			log.debug("<<mem_id>> : " + mem_id);
		
		Map<String, String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selectMember(mem_id);
		if(member != null){
			//아이디중복
			map.put("result", "idDuplicated");
		}else{
			//아이디 미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
	//로그아웃
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session){
		//로그아웃
		session.invalidate();
		return "redirect:/main/main.do";
	}
	
	//마이페이지 (상세)
	@RequestMapping("/member/mypage.do")
	public String mypage(HttpSession session, Model model){ 

		//session에서 id를 끄집어내서 1건의 데이터를 가져옴.
		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);
		
		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + member);
			
		model.addAttribute("member", member);

		return "memberMyPage";
	}
	
	// 마이페이지 이미지 출력
	@RequestMapping("/member/imageView.do")
	public ModelAndView viewImage(@RequestParam("mem_id") String mem_id) {

		MemberCommand member = memberService.selectMember(mem_id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");   // imageView 객체 호출
		mav.addObject("imageFile", member.getMem_image());
		mav.addObject("filename", member.getMem_filename());

		return mav;
	}
	
	//회원수정 폼
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public String updateForm(HttpSession session, Model model){ 
		
		//session에서 id를 끄집어내서 1건의 데이터를 가져옴.
		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);
		model.addAttribute("command", member);
		
		return "memberUpdate";
	}
	
	//회원수정
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public String update(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result){
		
		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + memberCommand);
		
		if(result.hasErrors())
			return "memberUpdate";
		
		// 회원정보 수정. 
		memberService.update(memberCommand);
		
		return "redirect:/member/mypage.do";
	}
	
	//회원탈퇴 폼
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String deleteForm(HttpSession session, Model model){
		
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = new MemberCommand();
		member.setMem_id(id);
		
		model.addAttribute("command", member);
		
		return "memberDelete";
	}
	
	@RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
	public String delete(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		
		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + memberCommand);
		
		if(result.hasFieldErrors("mem_pw"))
			return "memberDelete";
		
		try{
			MemberCommand member = memberService.selectMember(memberCommand.getMem_id());
			
			boolean check = false;
			
			if(member != null){
				//비밀번호 일치여부 체크
				check = member.isCheckedPasswd(memberCommand.getMem_pw());
			}
			
			if(check){
				//인증성공, 회원정보삭제
				memberService.delete(memberCommand.getMem_id());
				//로그아웃
				session.invalidate();
				return "redirect:/main/main.do";
			}else{
				//인증실패
				throw new Exception();
			}
		}catch(Exception e){
			result.rejectValue("mem_pw", "invalidPassword");
			return "memberDelete";
		}
	}
	
	
	
}
