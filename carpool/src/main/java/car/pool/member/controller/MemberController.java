package car.pool.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import car.pool.member.domain.DriverMemberCommand;
import car.pool.member.domain.MemberCommand;
import car.pool.member.email.Email;
import car.pool.member.email.EmailSender;
import car.pool.member.service.DriverMemberService;
import car.pool.member.service.MemberService;

@Controller
public class MemberController {

	private Logger log = Logger.getLogger(this.getClass());
	//dsdsadzz 343
	@Resource
	private MemberService memberService;
	
	@Resource
	private DriverMemberService driverMemberService;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private Email email;
	
	//Ŀ�ǵ� ��ü �ʱ�ȭ (Ŀ���� �±׸� ����ϱ� ����.)
	@ModelAttribute("command")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	
	//������� 
	@RequestMapping(value="/member/agree.do", method=RequestMethod.GET)
	public String agreeForm(){
		
		return "memberAgree";
	}
	
	
	//ȸ������ ��
	@RequestMapping(value="/member/write.do", method=RequestMethod.GET)
	public String writeForm(){
		
		return "memberWrite";	
	}
	//ȸ������
	@RequestMapping(value="/member/write.do", method=RequestMethod.POST)
	public String writeSubmit(@ModelAttribute("command") MemberCommand memberCommand){
		
		if(log.isDebugEnabled())
			log.debug("memberCommand : " + memberCommand);
		
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
	public String login(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		
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
	public Map<String, String> confirmId(@RequestParam("mem_id") String mem_id){
		
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
	public String logout(HttpSession session){
		//�α׾ƿ�
		session.invalidate();
		return "redirect:/main/main.do";
	}
	
	/*//ID ã�� ��(����ã��)
	@RequestMapping(value="/member/search.do", method=RequestMethod.GET)
	public String searchIdForm(){
		
		return "memberSearchID";
	}
	
	//ID ã��
	@RequestMapping(value="/member/search.do", method=RequestMethod.POST)
	public ModelAndView sendEmailActionId(@RequestParam("mem_name") String name, @RequestParam("mem_email") String e_mail) throws Exception {

		if(log.isDebugEnabled()){
			log.debug("<<name>> : " + name);
			log.debug("<<e_mail>> : " + e_mail);
		}
		
		ModelAndView mav;
		MemberCommand member = memberService.searchMemberId(e_mail);
		String id=member.getMem_id();
				
        if(id!=null) {
            email.setContent(name +"���� ���̵�� "+id+" �Դϴ�.");
            email.setReceiver(e_mail);
            email.setSubject("[CarPool] ���̵� ã�� �����Դϴ�.");
            emailSender.SendEmail(email);
            mav= new ModelAndView("redirect:/member/login.do");
            return mav;
        }else {
            mav=new ModelAndView("redirect:/member/logout.do");
            return mav;
        }
    }*/
	
	//��й�ȣã�� ��
	@RequestMapping(value="/member/searchPw.do", method=RequestMethod.GET)
	public ModelAndView searchPwForm(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberSearchPW");	
		return mav;
	}
	
	//��й�ȣã�� 
	@RequestMapping(value="/member/searchPwAlert.do")
	@ResponseBody
	public Map<String, Object> sendEmailAlert (@RequestParam("mem_id") String id, @RequestParam("mem_email") String e_mail) throws Exception {
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberCommand member = memberService.selectMember(id);

		
		int mem_auth = 1;
		
		if(member != null){
			mem_auth = member.getMem_auth();
		}else{
			mem_auth = 0;
		}
		
		if(mem_auth == 1 || mem_auth == 2){
			String pw = member.getMem_pw();
			
			if(member.getMem_email().equals(e_mail)){
				if(pw!=null) {
		            email.setContent(id +"���� ��й�ȣ�� "+pw+" �Դϴ�.");
		            email.setReceiver(e_mail);
		            email.setSubject("[CarPool] ��й�ȣ ã�� �����Դϴ�.");
		            emailSender.SendEmail(email);
		        }
			}else{
				mem_auth = 3;
			}
		}
		
		mapJson.put("mem_auth", mem_auth);
		
		return mapJson;
    }

	
	//���������� (��)
	@RequestMapping("/member/mypage.do")
	public String mypage(HttpSession session, Model model){ 

		//session���� id�� ������� 1���� �����͸� ������.
		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);
		DriverMemberCommand driverMember = driverMemberService.driverSelectMember(id);
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
			log.debug("<<driverMember>> : " + driverMember);
		}
			
		model.addAttribute("member", member);
		model.addAttribute("driverMember", driverMember);

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
	
	//ȸ������ ��
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public String updateForm(HttpSession session, Model model){ 
		
		//session���� id�� ������� 1���� �����͸� ������.
		String id = (String)session.getAttribute("user_id");

		MemberCommand member = memberService.selectMember(id);
		model.addAttribute("command", member);
		
		String[] phone = member.getMem_phone().split("-");
		model.addAttribute("phone", phone);

		String[] email = member.getMem_email().split("@");
		model.addAttribute("email", email);
		
		return "memberUpdate";
	}
	
	//ȸ������
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public String update(@ModelAttribute("command") MemberCommand memberCommand, HttpSession session){
		
		if(log.isDebugEnabled())
			log.debug("<<memberCommand>> : " + memberCommand);
		
		String id = (String)session.getAttribute("user_id");
		MemberCommand member = memberService.selectMember(id);
		
		//���۵� ������ ���� ���
		if(memberCommand.getUpload().isEmpty()){
			//���� ���� ����
			memberCommand.setMem_image(member.getMem_image());
			memberCommand.setMem_filename(member.getMem_filename());
		}
		
		// ȸ������ ����. 
		memberService.update(memberCommand);
		
		return "redirect:/member/mypage.do";
	}
	
	//ȸ��Ż�� ��
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
				//��й�ȣ ��ġ���� üũ
				check = member.isCheckedPasswd(memberCommand.getMem_pw());
			}
			
			if(check){
				//��������, ȸ����������
				memberService.delete(memberCommand.getMem_id());
				//�α׾ƿ�
				session.invalidate();
				return "redirect:/main/main.do";
			}else{
				//��������
				throw new Exception();
			}
		}catch(Exception e){
			result.rejectValue("mem_pw", "invalidPassword");
			return "memberDelete";
		}
	}
	
	
	
}
