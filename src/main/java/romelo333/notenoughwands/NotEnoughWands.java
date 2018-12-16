package romelo333.notenoughwands;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class NotEnoughWands implements ModInitializer {
    public static final String MODID = "notenoughwands";
    public static final String VERSION = "1.7.1";
    public static final String MIN_FORGE11_VER = "13.19.0.2176";
    public static final String MIN_COFH_VER = "2.0.0";
    public static final String MIN_MCJTYLIB_VER = "3.0.0";

    public static Logger logger;
    public static File mainConfigDir;
    public static File modConfigDir;

    @Override
    public void onInitialize() {
        ModItems.init();
        ModBlocks.init();
        ModSounds.init();
    }

    // @todo fabric
//    public static Configuration config;
//    public static CreativeTabs tabNew = new CreativeTabs("NotEnoughWands") {
//        @Override
//        public ItemStack getTabIconItem() {
//            return new ItemStack(ModItems.teleportationWand);
//        }
//    };
}
