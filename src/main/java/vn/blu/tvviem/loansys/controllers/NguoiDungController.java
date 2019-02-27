package vn.blu.tvviem.loansys.controllers;

import org.springframework.beans.factory.annotation.Autowired;

public class NguoiDungController {
    /*@Autowired
    private UserService userService;*/

    /*//@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public NguoiDung create(@RequestBody NguoiDungDto user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
    public NguoiDung deleteUser(@PathVariable(value = "id") long id){
        userService.delete(id);
        return new NguoiDung(id);
    }*/
}
