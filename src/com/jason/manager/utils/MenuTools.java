package com.jason.manager.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jason.manager.resource.entity.MenuItem;
import com.jason.manager.resource.entity.TPower;

public class MenuTools {

	public static List<MenuItem> buildMenuItem(List<TPower> menu) {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();

		// 第一级按钮
		for (int i = menu.size() - 1; i >= 0; i--) {
			TPower power = menu.get(i);
			if (power.getParentId() == -1) {
				MenuItem menuItem = new MenuItem();
				menuItem.setPower(menu.remove(i));
				menuItems.add(menuItem);
			}
		}

		for (MenuItem menuItem : menuItems) {
			initSubMenu(menuItem, menu);
		}

		// 排序
		Collections.sort(menuItems, new Comparator<MenuItem>() {
			@Override
			public int compare(MenuItem o1, MenuItem o2) {
				return o1.getPower().getSort() - o2.getPower().getSort();
			}
		});

		for (MenuItem menuItem : menuItems) {
			sortMenuItem(menuItem);
		}

		return menuItems;
	}

	private static void sortMenuItem(MenuItem menuItem) {
		if (menuItem.getSubMenus().size() == 0) {
			return;
		}
		Collections.sort(menuItem.getSubMenus(), new Comparator<MenuItem>() {
			@Override
			public int compare(MenuItem o1, MenuItem o2) {
				return o1.getPower().getSort() - o2.getPower().getSort();
			}
		});
	}

	private static void initSubMenu(MenuItem menuItem, List<TPower> menu) {
		if (menu.size() == 0) {
			return;
		}
		for (int i = menu.size() - 1; i >= 0; i--) {
			if (i >= menu.size())
				continue;
			TPower power = menu.get(i);
			if (power.getParentId() == menuItem.getPower().getResourceId()) {
				MenuItem item = new MenuItem();
				item.setPower(menu.remove(i));
				menuItem.getSubMenus().add(item);
				initSubMenu(item, menu);
			}
		}
	}
}
