package com.atguigu.springbootweb.controller;

import com.atguigu.springbootweb.dao.DepartmentDao;
import com.atguigu.springbootweb.dao.EmployeeDao;
import com.atguigu.springbootweb.entities.Department;
import com.atguigu.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    //查询员工返回界面
    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees=employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //来到员工增加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //添加员工
    @PostMapping("/emp")
    public String addEmp(Employee employee){

        System.out.println("保存的员工信息:" +employee);
        employeeDao.save(employee);
        return ("redirect:/emps");

    }
    //来到员工编辑页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        //查出部门显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        //编辑结束,回到员工添加界面
        return "emp/add";
    }

     //员工修改
     @PutMapping("/emp")
     public String updateEmployee(Employee employee){
            System.out.println("修改的员工数据: "+employee);
            employeeDao.save(employee);
            return ("redirect:/emps");
    }

    @DeleteMapping("/emp/{id}")
    //员工删除
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return ("redirect:/emps");
    }
}
