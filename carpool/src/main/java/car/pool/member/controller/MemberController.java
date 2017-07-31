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
	
	//Ŀ�ǵ� ��ü �ʱ�ȭ (Ŀ���� �±׸� ����ϱ� ����.)
	@ModelAttribute("command")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	
	//ȸ������ ��
	@RequestMapping(value="/member/write.do", method=RequestMethod.GET)
	public String writeForm(){
		return "memberWrite";	
	}
	//ȸ������
	@RequestMapping(value="/member/write.do", method=RequestMethod.POST)
	public String writeSubmit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result){
		
		if(log.isDebugEnabled())
			log.debug("memberCommand : " + memberCommand);
		
		if(result.hasErrors())
			return writeForm();
		
		memberService.insert(memberCommand);
		
		return "redirect:/main/main.do";
	}
	
	
	//�α��� ��
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String loginForm(){
		return "memberLogin"; 
	}
	
	//�α���
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		
		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + memberCommand);
		
		// id�� passwd �ʵ常 üũ!
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd"))
			return loginForm();
		
		// �α��� üũ(ID �Ǵ� ��й�ȣ ��ġ ���� üũ)
		try{
			MemberCommand member = memberService.selectMember(memberCommand.getMem_id());
			boolean check = false;
			
			if(member != null){
				//��й�ȣ ��ġ���� üũ
				check = member.isCheckedPasswd(memberCommand.getMem_pw());
			}
			
			if(check){
				//��������, �α��� ó��
				session.setAttribute("user_id", memberCommand.getMem_id());
				// auth�� session�� �־�ΰ�, session�� �־��� auth���� 2�� �����ڰ� Ȱ�밡���� �޴����� ��������.
				session.setAttribute("user_auth", memberCommand.getMem_auth()); // 0�� Ż��ȸ��, 1�� �Ϲ�ȸ��, 2�� ������ȸ�� ���� auth���� session�� �־�θ� �����ڱ��� Ȯ�尡��.
			
				return "redirect:/main/main.do";
			}else{
				//��������
				throw new Exception();
			}
			
		}catch(Exception e){
			//�������з� �� ȣ��
			result.reject("invalidIdOrPassword");
			return loginForm();
		}
	}
	
	//�α��� �� ID �ߺ�üũ
	@RequestMapping("/member/confirmId.do")
	@ResponseBody 
	public Map<String, String> process(@RequestParam("mem_id") String mem_id){
		
		/*
		 	JSON ���ڿ��� ����. (JSONó���� ���� tilesó���� �ϸ� �ȵ�. map�� ��ȯ�� ���ָ� tiles�� ��������ʰ� viewResolver�� order=2 ���ʷΰ��� ó���ȴ�.)
		*/
		if(log.isDebugEnabled())
			log.debug("<<mem_id>> : " + mem_id);
		
		Map<String, String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selectMember(mem_id);
		if(member != null){
			//���̵��ߺ�
			map.put("result", "idDuplicated");
		}else{
			//���̵� ���ߺ�
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
	//�α׾ƿ�
	@RequestMapping("/member/logout.do")
	public String process(HttpSession session){
		//�α׾ƿ�
		session.invalidate();
		return "redirect:/main/main.do";
	}
	
	//���������� (��)
	@RequestMapping("/member/mypage.do")
	public String form(HttpSession session, Model model){ 

		//session���� id�� ������� 1���� �����͸� ������.
		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);
		
		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + member);
			
		model.addAttribute("member", member);

		return "memberMyPage";
	}
	
	// ���������� �̹��� ���
	@RequestMapping("/member/imageView.do")
	public ModelAndView viewImage(@RequestParam("mem_id") String mem_id) {

		MemberCommand member = memberService.selectMember(mem_id);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");   // imageView ��ü ȣ��
		mav.addObject("imageFile", member.getMem_image());
		mav.addObject("filename", member.getMem_filename());

		return mav;
	}
	
}
