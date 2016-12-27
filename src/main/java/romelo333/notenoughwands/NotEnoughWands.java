package romelo333.notenoughwands;

import mcjty.lib.compat.CompatCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import romelo333.notenoughwands.proxy.CommonProxy;

import java.io.File;

@Mod(modid = NotEnoughWands.MODID, name="Not Enough Wands",
        dependencies =
                    "required-after:compatlayer@[" + NotEnoughWands.COMPATLAYER_VER + ",);" +
                    "after:Forge@[" + NotEnoughWands.MIN_FORGE10_VER + ",);" +
                    "after:forge@[" + NotEnoughWands.MIN_FORGE11_VER + ",)",
        acceptedMinecraftVersions = "[1.10,1.12)",
        version = NotEnoughWands.VERSION)
public class NotEnoughWands {
    public static final String MODID = "notenoughwands";
    public static final String VERSION = "1.5.3";
    public static final String MIN_FORGE10_VER = "12.18.1.2082";
    public static final String MIN_FORGE11_VER = "13.19.0.2176";
    public static final String COMPATLAYER_VER = "0.1.6";

    @SidedProxy(clientSide="romelo333.notenoughwands.proxy.ClientProxy", serverSide="romelo333.notenoughwands.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance("NotEnoughWands")
    public static NotEnoughWands instance;
    public static Logger logger;
    public static File mainConfigDir;
    public static File modConfigDir;
    public static Configuration config;

    public static CreativeTabs tabNew = new CompatCreativeTabs("NotEnoughWands") {
        @Override
        protected Item getItem() {
            return ModItems.teleportationWand;
        }
    };

    /**
     * Run before anything else. Read your config, create blocks, items, etc, and
     * register them with the GameRegistry.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        mainConfigDir = e.getModConfigurationDirectory();
        modConfigDir = new File(mainConfigDir.getPath());
        config = new Configuration(new File(modConfigDir, "notenoughwands.cfg"));
        proxy.preInit(e);

//        FMLInterModComms.sendMessage("Waila", "register", "mcjty.wailasupport.WailaCompatibility.load");
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
