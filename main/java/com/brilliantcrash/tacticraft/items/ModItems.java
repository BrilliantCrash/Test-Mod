package com.brilliantcrash.tacticraft.items;

import com.brilliantcrash.tacticraft.items.melee.BaseSword;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Evan on 2/12/2016.
 */
public final class ModItems {

    public static Item ammo_ninemm;
    public static Item nylon;
    public static Item ingotCopper;
    public static Item ingotLead;
    public static Item bullet;
    public static Item knife;
    public static Item.ToolMaterial MODIRON = EnumHelper.addToolMaterial("MODIRON", 2, 50, 6.0F, 2.5F, 15);

    public static void createItems() {
        GameRegistry.registerItem(ammo_ninemm = new BaseAmmo("ammo_ninemm"), "ammo_ninemm");
        GameRegistry.registerItem(nylon = new BaseItem("nylon"), "nylon");
        GameRegistry.registerItem(ingotCopper = new BaseItem("copper_ingot"), "copper_ingot");
        GameRegistry.registerItem(ingotLead = new BaseItem("lead_ingot"), "lead_ingot");
        GameRegistry.registerItem(bullet = new BaseAmmo("bullet"), "bullet");
        GameRegistry.registerItem(knife = new BaseSword("knife", MODIRON), "knife");
    }

}
