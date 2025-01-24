package com.app.controller;

import java.util.List;

import com.app.dao.MenuDao;
import com.app.entity.Menu;

public class MenuController {

    public static boolean addMenu(Menu menu) {
        return MenuDao.addMenu(menu);
    }

    public static Menu getMenu(int id) {
        return MenuDao.getMenu(id);
    }

    public static List<Menu> getAllMenus() {
        return MenuDao.getAllMenus();
    }

    public static List<Menu> getAllMenusById(int id) {
        return MenuDao.getAllMenusById(id);
    }

    public static boolean updateMenu(Menu menu) {
        return MenuDao.updateMenu(menu);
    }

    public static boolean deleteMenu(int id) {
        return MenuDao.deleteMenu(id);
    }

}
