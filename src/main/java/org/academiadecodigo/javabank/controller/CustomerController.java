package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountFactory accountFactory;

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "customer/list";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        return "customer/show";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String newCustomer (Model model) {
        model.addAttribute("customer",new Customer());
        return "customer/form";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    public String editCustomer (@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        return "customer/form";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public String addCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {

        Customer savedCustomer = customerService.save(customer);
        redirectAttributes.addFlashAttribute("add OK", "Added customer successfully!!!");
        // Instead of returning a rendered view to the browser,
        // a 302 redirect is sent to the browser, forcing showCustomer()
        // to execute after adding a new user
        return "redirect:/customer/" + savedCustomer.getId();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/addNewAccount/{id}"})
    public String addNewAccount(@PathVariable Integer id, @ModelAttribute("inputAccountType") AccountType accountType, RedirectAttributes redirectAttributes) {


        Customer customer = customerService.get(id);
        System.out.println("Account type is " + accountType);

        Account newAccount = accountFactory.createAccount(accountType);
        customer.addAccount(newAccount);
        customerService.save(customer);
        return "redirect:/customer/"+ customer.getId();
    }
}
