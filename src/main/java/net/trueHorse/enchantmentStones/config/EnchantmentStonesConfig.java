package net.trueHorse.enchantmentStones.config;

import net.fabricmc.loader.api.FabricLoader;
import net.trueHorse.enchantmentStones.EnchantmentStones;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnchantmentStonesConfig {

    private static final Map<String,ConfigOption> configs = new HashMap<>();
    private final static String MOD_CONFIG_DIR_NAME = FabricLoader.getInstance().getConfigDir() + "/enchantment_stones";
    private final static File MOD_CONFIG_FILE = new File(MOD_CONFIG_DIR_NAME+"/enchantment_stones.properties");

    public static void loadConfigs(){
        configs.put("enchantableWithoutStone",new ConfigOption<Boolean>("true","true","Is equipment without a stone enchantable?"));
        configs.put("enchantmentsPerStone",new ConfigOption<Integer>("3","3","How many enchantments a stone can hold."));
        configs.put("stonesPerEquip", new ConfigOption<Integer>("1","1","How many enchantment stones you can add to one equipment."));

        if(MOD_CONFIG_FILE.exists()){
            try {
                Properties tmpProperties = new Properties();
                tmpProperties.load(new FileReader(MOD_CONFIG_FILE));
                tmpProperties.forEach((k,v)->configs.get(k).setVal((String) v));
            } catch (FileNotFoundException e) {
                EnchantmentStones.LOGGER.error("Config file was not found after existing. How?");
                e.printStackTrace();
            } catch (IOException e) {
                EnchantmentStones.LOGGER.error("Failed to read the actual config file.");
                e.printStackTrace();
            }
        }else{
            createOrUpdateConfigFile();
        }
    }

    public static void createOrUpdateConfigFile() {
        if(!MOD_CONFIG_FILE.getParentFile().exists()){
            MOD_CONFIG_FILE.getParentFile().mkdirs();
        }

        if(MOD_CONFIG_FILE.exists()){
            boolean success = MOD_CONFIG_FILE.delete();
            if(!success) {
                EnchantmentStones.LOGGER.error("Config file could not be deleted.");
                EnchantmentStones.LOGGER.info(Arrays.toString(Thread.currentThread().getStackTrace()));
            }
        }

        try {
            MOD_CONFIG_FILE.createNewFile();

            FileWriter confWriter = new FileWriter(MOD_CONFIG_FILE);
            confWriter.write(getConfigContentAsString(configs));
            confWriter.close();
        } catch (IOException e) {
            EnchantmentStones.LOGGER.error("Creation of config file failed");
            e.printStackTrace();
        }
    }

    private static String getConfigContentAsString(Map<String, ConfigOption> configs) {
        StringBuilder configString = new StringBuilder();
        configs.forEach((k,v)->{
            configString.append("#").append(v.getDescription()).append("\n");
            configString.append(k).append("=").append(v.getVal()).append("\n");
        });
        return configString.toString();
    }

    public static String getVal(String key){
        String val = configs.get(key).getVal();
        if(val!=null){
            return val;
        }else{
            EnchantmentStones.LOGGER.error("Could not get config option "+key);
            EnchantmentStones.LOGGER.info(Arrays.toString(Thread.currentThread().getStackTrace()));
            return null;
        }
    }
}
