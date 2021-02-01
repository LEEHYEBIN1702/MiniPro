package co.micol.minipro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.Service;
import co.micol.minipro.member.service.Login;
import co.micol.minipro.member.service.LoginForm;
import co.micol.minipro.member.service.Logout;
import co.micol.minipro.member.service.MemberIdCheck;
import co.micol.minipro.member.service.MemberJoin;
import co.micol.minipro.member.service.MemberJoinForm;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Service> map = new HashMap<String, Service>();
	// String 요청명, Service = 실행할 Command

	public FrontController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
        map.put("/main.do", new MainService()); //메인화면 호출
        map.put("/loginForm.do", new LoginForm()); //로그인폼 호출
        map.put("/login.do", new Login()); //로그인 호출
        map.put("/logout.do", new Logout()); //로그아웃 호출
        
        map.put("/memberJoinForm.do", new MemberJoinForm()); //회원가입 폼
        map.put("/memberJoin.do", new MemberJoin()); //회원가입
        map.put("/idCheck.do", new MemberIdCheck()); //아이디 중복체크
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String contextPath = request.getContextPath(); //최상위 경로
		String uri = request.getRequestURI();
		String path = uri.substring(contextPath.length()); //실제 요청한 경로 확인
		
		Service service = map.get(path); //적절한 command를 찾는 부분
		String viewPage = service.run(request, response); //실행해서 결과를 돌려주는 페이지
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
