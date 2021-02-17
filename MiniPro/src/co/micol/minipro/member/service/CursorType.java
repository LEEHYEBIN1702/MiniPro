package co.micol.minipro.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.EmployeeVo;
import co.micol.minipro.common.Service;
import co.micol.minipro.member.dao.MemberDao;

public class CursorType implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// cursor.do
		 MemberDao dao = new MemberDao();
		 EmployeeVo resultVo = dao.getSalaryInfo(101, 19100);
		 request.setAttribute("vo", resultVo); 
		return "views/main/main.jsp";
	}

}
