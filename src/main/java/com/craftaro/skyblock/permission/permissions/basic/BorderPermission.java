package com.craftaro.skyblock.permission.permissions.basic;

import com.craftaro.third_party.com.cryptomorin.xseries.XMaterial;
import com.craftaro.skyblock.permission.BasicPermission;
import com.craftaro.skyblock.permission.PermissionType;

public class BorderPermission extends BasicPermission {
    public BorderPermission() {
        super("Border", XMaterial.BEACON, PermissionType.OPERATOR);
    }
}
