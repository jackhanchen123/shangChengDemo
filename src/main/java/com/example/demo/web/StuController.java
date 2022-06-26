package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;
import com.example.demo.service.ICardService;
import com.example.demo.service.IShoppingService;
import com.example.demo.util.MD5;

@RestController
@RequestMapping("/card")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class StuController {
	@Autowired
	private ICardService cardService;
	@Autowired
	private IShoppingService shoppingService;

	@RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean verifyLogin(@RequestBody Userinfo userinfo, HttpSession session) {
		boolean rt = false;
		Userinfo user = cardService.selectByNameAndPwd(userinfo.getUserLogin(), userinfo.getUserPasswd());
		if (user != null) {
			session.setAttribute("user", user);
			rt = true;
		}
		return rt;
	}

	@RequestMapping(path = "/username", method = { RequestMethod.GET, RequestMethod.POST })
	public String getUserName(HttpSession session) {
		Userinfo user = (Userinfo) session.getAttribute("user");
		if (user != null) {
			return user.getUserName();
		}
		return null;
	}

	@RequestMapping(path = "/checkoldpasswd", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean checkOldPasswd(String old, String first, String second, HttpSession session) {
		Userinfo user = (Userinfo) session.getAttribute("user");
		System.out.println("文本框的原密码：" + old);
		System.out.println("已登录的用户密码：" + user.getUserPasswd());
		boolean b = user.getUserPasswd().equals(MD5.enctypeMD5("haha" + old));
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(path = "/confirmpasswd", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean confirmPasswd(String first, String second, HttpSession session) {
		System.out.println("第一个密码：" + first);
		System.out.println("第二个密码：" + second);
		boolean rt = false;
		if (first.equals(second)) {

			Userinfo user = (Userinfo) session.getAttribute("user");
			user.setUserPasswd(second);
			boolean b = cardService.updateByPrimaryKeySelective(user);
			if (b) {// 修改成功
				rt = true;
				return rt;
			}
			return rt;
		}

		return rt;

	}

	@RequestMapping(path = "/quit", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean userQuit(HttpSession session) {
		session.invalidate();
		return true;
	}

	@RequestMapping(path = "/liststu", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Student> listAllStu(HttpSession session) {
		return cardService.listAllStu();
	}

	@RequestMapping(path = "/querystu", method = { RequestMethod.GET, RequestMethod.POST })
	public Student queryStudent(Integer stuId) {
		return cardService.queryStuById(stuId);
	}

	@RequestMapping(path = "/savestu", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean savaStudent(@RequestBody Student student) {
		return cardService.updateStudent(student);
	}

	@RequestMapping(path = "/deletestu", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean deleteStudent(Integer stuId) {
		System.out.println("传来的删除Id是：" + stuId);
		boolean b = cardService.deleteStudent(stuId);// 暂时返回true
		if (b) {
			System.out.println("删除ok");
		}
		return b;
	}

	@RequestMapping(path = "/addstu", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean addStudent(@RequestBody Student student) {
		System.out.println("增加的对象student：" + student);
		boolean b = cardService.addStudent(student);
		return b;
	}

	@RequestMapping(path = "/deleteallstu", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Student> deleteAllStudent(@RequestBody Student[] stulist) {
		Integer[] ids = new Integer[stulist.length];
		for (int i = 0; i < stulist.length; i++) {
			ids[i] = stulist[i].getStuId();
		}
//		for (Integer id : ids) {
//			System.out.println("学生id是："+id);
//		}

		boolean b = cardService.deleteAllStu(ids);

		return cardService.listAllStu();
	}

	@RequestMapping(path = "/query", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Student> queryStudent(String content) {
		// System.out.println(content);
		return cardService.listStuByQueryContent(content);
	}

	@RequestMapping(path = "/stulogin", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean stuLogin(@RequestBody Student stu, HttpSession session) {
		// System.out.println("stu"+stu);
		Student loginStudent = shoppingService.queryByStu(stu);
		// System.out.println("控制器查出的Stu:"+loginStudent);
		boolean b = false;
		if (loginStudent != null) {// 登录成功
			session.setAttribute("student", loginStudent);
			b = true;
		}
		return b;
	}

	@RequestMapping(path = "/stuname", method = { RequestMethod.GET, RequestMethod.POST })
	public Student getStuName(HttpSession session) {
		Student stu = (Student) session.getAttribute("student");
		if (stu != null) {
			return stu;
		}
		return null;
	}

	@RequestMapping(path = "/checkStuOldPasswd", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean checkStuPasswd(String old, HttpSession session) {
		Student stu = (Student) session.getAttribute("student");
		//System.out.println("文本框的原密码：" + old);
		//System.out.println("作用域中stu:" + stu);
		boolean b = stu.getStuPasswd().equals(MD5.enctypeMD5("haha" + old));
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(path = "/submitStuPasswd", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean submitStuPasswd(String newpasswd, HttpSession session) {
		System.out.println("新密码是：" + newpasswd);
		Student stu = (Student) session.getAttribute("student");
		stu.setStuPasswd(MD5.enctypeMD5("haha" + newpasswd));
		boolean b = cardService.updateStudent(stu);
		return b;

	}

}
