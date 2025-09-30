package jp.co.sss.crud.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.service.SearchAllEmployeesService;
import jp.co.sss.crud.service.SearchForEmployeesByDepartmentService;
import jp.co.sss.crud.service.SearchForEmployeesByEmpIdService;
import jp.co.sss.crud.service.SearchForEmployeesByEmpNameService;

@Controller
public class ListController {

	@Autowired
	SearchAllEmployeesService searchAllEmployeesService;

	@Autowired
	SearchForEmployeesByEmpNameService searchForEmployeesByEmpNameService;

	@Autowired
	SearchForEmployeesByDepartmentService searchForEmployeesByDepartmentService;
	
	@Autowired
	SearchForEmployeesByEmpIdService SearchForEmployeesByEmpIdService;

	/**
	 * 社員情報を全件検索した結果を出力
	 *
	 * @param model モデル
	 * @return 遷移先のビュー
	 * @throws ParseException 
	 */
	@Autowired
	SearchAllEmployeesService  SearchAll;
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String findAll(Model model) {
	
		List<EmployeeBean> allEmployeeList = null;
		
		allEmployeeList = SearchAll.execute();
		//TODO SearchAllEmployeesService完成後にコメントを外す
		//		allEmployeeList = searchAllEmployeesService.execute();

		model.addAttribute("employees", allEmployeeList);
		return "list/list";
	}

	/**
	 * 社員情報を社員名検索した結果を出力
	 *
	 * @param empName 検索対象の社員名
	 * @param model モデル
	 * @return 遷移先のビュー
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/list/empName", method = RequestMethod.GET)
	public String findByEmpName(String empName, Model model) {

		List<EmployeeBean> searchByEmpNameList = searchForEmployeesByEmpNameService.execute(empName);

		//TODO SearchForEmployeesByEmpNameService完成後にコメントを外す
		//		searchByEmpNameList = searchForEmployeesByEmpNameService.execute(empName);

		model.addAttribute("employees", searchByEmpNameList);
		return "list/list";
	}

	/**
	 * 社員情報を部署ID検索した結果を出力
	 *
	 * @param deptId 検索対象の部署ID
	 * @param model モデル
	 * @return 選先のビュー
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/list/deptId", method = RequestMethod.GET)
	public String findByDeptId(Integer deptId, Model model) {

		List<EmployeeBean> searchByDepartmentList = searchForEmployeesByDepartmentService.execute(deptId);

		//TODO SearchForEmployeesByDepartmentService完成後にコメントを外す
		//		searchByDepartmentList=searchForEmployeesByDepartmentService.execute(deptId);

		model.addAttribute("employees", searchByDepartmentList);
		return "list/list";
	}
	
	/*
	 * 社員情報を社員ID検索した結果を出力
	 */
	@RequestMapping(path = "/list/empId", method = RequestMethod.GET)
	public String findByempId(String strempId, Model model) {
		
		Integer empId;//NULLが入るためにIntger型にする。String型とInteger型の箱を用意する。
		if (strempId == null || strempId.isEmpty()) { //入ってきた値がNULLまたは空文字だった場合
			empId = null; //tureの場合NULLで値を返す→109行目に行く
		}else if (strempId.matches("\\d+")) { //falseだった場合、正規表現か判定する
			empId = Integer.valueOf(strempId); //数値だった場合はvalueOf()を使って、Intger型に変換して代入→109行目
		}else {
			empId = 0;//aなどの文字列が入力されたときに０に代入させて検索しに行かせる→112行目に行く
		}
			if(empId == null) {
				return "Redirect:/list"; //NULLだったら社員一覧を出す
		}else {
			EmployeeBean searchByempId = SearchForEmployeesByEmpIdService.execute(empId);
			model.addAttribute("employees", searchByempId);
			return "list/list";	
		}
	}
}

