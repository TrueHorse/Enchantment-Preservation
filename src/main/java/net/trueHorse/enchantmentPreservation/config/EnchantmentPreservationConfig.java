package net.trueHorse.enchantmentPreservation.config;

import net.fabricmc.loader.api.FabricLoader;
import net.trueHorse.enchantmentPreservation.EnchantmentPreservation;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnchantmentPreservationConfig {

    private static final Map<String,ConfigOption<?>> configs = new HashMap<>();
    private final static String MOD_CONFIG_DIR_NAME = FabricLoader.getInstance().getConfigDir() + "/enchantment_preservation";
    private final static File MOD_CONFIG_FILE = new File(MOD_CONFIG_DIR_NAME+"/enchantment_preservation.properties");

    public static void loadConfigs(){
        configs.put("enchantableWithoutStone",new ConfigOption<Boolean>("true","true","Is equipment without a stone enchantable?"));
        configs.put("enchantmentsPerStone",new ConfigOption<Integer>("3","3","How many enchantments a stone can hold."));
        configs.put("stonesPerEquip", new ConfigOption<Integer>("1","1","How many enchantment stones you can add to one equipment."));
        configs.put("brotherEdition", new ConfigOption<Boolean>("true","true","Should the addition my brother asked for, the \"Big Bui Stone\", be included and it's halves generated in end cities?"));


        if(MOD_CONFIG_FILE.exists()){
            try {
                Properties tmpProperties = new Properties();
                tmpProperties.load(new FileReader(MOD_CONFIG_FILE));
                tmpProperties.forEach((k,v)->configs.get(k).setVal((String) v));
            } catch (FileNotFoundException e) {
                EnchantmentPreservation.LOGGER.error("Config file was not found after existing. How?");
                e.printStackTrace();
            } catch (IOException e) {
                EnchantmentPreservation.LOGGER.error("Failed to read the actual config file.");
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
                EnchantmentPreservation.LOGGER.error("Config file could not be deleted.");
                EnchantmentPreservation.LOGGER.info(Arrays.toString(Thread.currentThread().getStackTrace()));
            }
        }

        try {
            MOD_CONFIG_FILE.createNewFile();

            FileWriter confWriter = new FileWriter(MOD_CONFIG_FILE);
            confWriter.write(getConfigContentAsString(configs));
            confWriter.close();
        } catch (IOException e) {
            EnchantmentPreservation.LOGGER.error("Creation of config file failed");
            e.printStackTrace();
        }
    }

    private static String getConfigContentAsString(Map<String, ConfigOption<?>> configs) {
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
            EnchantmentPreservation.LOGGER.error("Could not get config option "+key);
            EnchantmentPreservation.LOGGER.info(Arrays.toString(Thread.currentThread().getStackTrace()));
            return null;
        }
    }
}
