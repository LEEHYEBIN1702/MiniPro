package co.micol.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.Service;

public class MemberJoinForm implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 회원가입 창을 누르면 회원가입 할 수 있는 창(폼)이 먼저 떠야 함. 
		// 폼을 호출해준다.
		return "views/member/memberJoinForm.jsp";
	}

}
