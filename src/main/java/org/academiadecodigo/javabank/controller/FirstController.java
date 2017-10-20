
package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RequestMapping("customer")
@Controller
public class FirstController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping (method = RequestMethod.GET, value = "/list")
    public String getCustomerList(Model model) {

        List<Customer> customers = customerService.getCustomerList();

        model.addAttribute("customers",customers);

        return "customerListTemplate";
    }

    @RequestMapping (method = RequestMethod.GET, path = "/{id}")
    public String getCustomer(Model model, @PathVariable Integer id) {

        model.addAttribute("customer",customerService.findById(id));

        model.addAttribute("accounts",customerService.findById(id).getAccounts());

        return "customerTemplate";
    }

    @RequestMapping (method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteCustomer(Model model, @PathVariable Integer id) {

        List<Customer> customers = customerService.getCustomerList();

        customerService.deleteCustomer(id);
        model.addAttribute("customers",customers);

        return "redirect:/javabank/customer/list";
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
