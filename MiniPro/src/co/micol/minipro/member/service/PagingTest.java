package co.micol.minipro.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.minipro.common.EmployeeVo;
import co.micol.minipro.common.Paging;
import co.micol.minipro.common.Service;
import co.micol.minipro.member.dao.MemberDao;

public class PagingTest implements Service {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		
		String pageNo = request.getParameter("pageNo");
		int pg = Integer.parseInt(pageNo);
		
		Paging paging = new Paging();
		
		paging.setPageNo(pg);
		paging.setPageSize(10);
		paging.setTotalCount(dao.getTotalCnt());
		System.out.println(paging);
		
		dao = new MemberDao(); // close();시켜버리면 연결이 끊기니까 다시 연결시켜주는 과정
		List<EmployeeVo> list = dao.getPagingList(pg);
		request.setAttribute("list", list);
		request.setAttribute("params", paging);
		
		return "views/main/main.jsp";
	}

}
