package com.electro.bikeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@SuppressWarnings(['UnnecessaryReturnKeyword', 'DuplicateStringLiteral'])
class WebController {

    //NON-USERS PAGES
    @RequestMapping('/about')
    String about() {
        return '/about'
    }

    @RequestMapping('/contact')
    String contact() {
        return '/contact'
    }

    @RequestMapping('/')
    String home() {
        return '/home'
    }

    @RequestMapping('/login')
    String login() {
        return '/login'
    }

    //OTHER PAGES
    @RequestMapping('/changeVOStatus')
    String changeVOStatus() {
        return '/other/changeVOStatus'
    }

    @RequestMapping('/createEmployee')
    String createEmployee() {
        return '/other/createEmployee'
    }

    @RequestMapping('/createRetailOrder')
    String createRetailOrder() {
        return '/other/createRetailOrder'
    }

    @RequestMapping('/inventory')
    String inventory() {
        return '/other/inventory'
    }

    @RequestMapping('/requestTime')
    String requestTime() {
        return '/other/requestTime'
    }

    @RequestMapping('/updateEmployee')
    String updateEmployee() {
        return '/other/updateEmployee'
    }

    @RequestMapping('/vendorOrder')
    String vendorOrder() {
        return '/other/vendorOrder'
    }

    @RequestMapping('/vendorOrdersCurrent')
    String vendorOrdersCurrent() {
        return '/other/vendorOrdersCurrent'
    }

    //EMPLOYEE PAGES
    @RequestMapping('/employee')
    String employee() {
        return '/employee/employee'
    }

    @RequestMapping('/employeeBikeRepair')
    String employeeBikeRepair() {
        return '/employee/employeeBikeRepair'
    }

    @RequestMapping('/employeeContact')
    String employeeContact() {
        return '/employee/employeeContact'
    }

    @RequestMapping('/employeeHelp')
    String employeeHelp() {
        return '/employee/employeeHelp'
    }

    @RequestMapping('/employeeDisplayInventory')
    String employeeDisplayInventory() {
        return '/employee/employeeDisplayInventory'
    }

    @RequestMapping('/employeeVendorHomePage')
    String employeeVendorHomePage() {
        return '/employee/employeeVendorHomePage'
    }

    //OWNER PAGES
    @RequestMapping('/bikeRepair')
    String bikeRepair() {
        return '/owner/bikeRepair'
    }

    @RequestMapping('/employeeHome')
    String employeeHome() {
        return '/owner/employeeHome'
    }

    @RequestMapping('/help')
    String help() {
        return '/owner/help'
    }

    @RequestMapping('/displayInventory')
    String displayInventory() {
        return '/owner/displayInventory'
    }

    @RequestMapping('/managersAndEmployees')
    String managersAndEmployees() {
        return '/owner/managersAndEmployees'
    }

    @RequestMapping('/owner')
    String owner() {
        return '/owner/owner'
    }

    @RequestMapping('/ownerContact')
    String ownerContact() {
        return '/owner/ownerContact'
    }

    @RequestMapping('/payroll')
    String payroll() {
        return '/owner/payroll'
    }

    @RequestMapping('/salesDiscounts')
    String salesDiscounts() {
        return '/owner/salesDiscounts'
    }

    @RequestMapping('/vendorHomePage')
    String vendorHomePage() {
        return '/owner/vendorHomePage'
    }

    //MANAGER PAGES
    @RequestMapping('/manager')
    String manager() {
        return '/manager/manager'
    }

    @RequestMapping('/managerBikeRepair')
    String managerBikeRepair() {
        return '/manager/managerBikeRepair'
    }

    @RequestMapping('/managerContact')
    String managerContact() {
        return '/manager/managerContact'
    }

    @RequestMapping('/managerEmployeeHome')
    String managerEmployeeHome() {
        return '/manager/managerEmployeeHome'
    }

    @RequestMapping('/managerHelp')
    String managerHelp() {
        return '/manager/managerHelp'
    }

    @RequestMapping('/managerDisplayInventory')
    String managerDisplayInventory() {
        return '/manager/managerDisplayInventory'
    }

    @RequestMapping('/managerSalesDiscounts')
    String managerSalesDiscounts() {
        return '/manager/managerSalesDiscounts'
    }

    @RequestMapping('/managerVendorHomePage')
    String managerVendorHomePage() {
        return '/manager/managerVendorHomePage'
    }

    //BOOKKEEPER PAGES
    @RequestMapping('/bookkeeper')
    String bookkeeper() {
        return '/bookkeeper/bookkeeper'
    }

    @RequestMapping('/bookkeeperContact')
    String bookkeeperContact() {
        return '/bookkeeper/bookkeeperContact'
    }

    @RequestMapping('/bookkeeperHelp')
    String bookkeeperHelp() {
        return '/bookkeeper/bookkeeperHelp'
    }

    @RequestMapping('/employeeInfo')
    String employeeInfo() {
        return '/bookkeeper/employeeInfo'
    }

    @RequestMapping('/bookkeeperPayroll')
    String bookkeeperPayroll() {
        return '/bookkeeper/bookkeeperPayroll'
    }


}
