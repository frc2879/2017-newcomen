package com.frc2879.newcomen;

import java.util.HashMap;

public class SettingsRegister {

	private HashMap<String, Object> settings;
	
	public SettingsRegister(HashMap<String, Object> defaults) {
		// TODO Auto-generated constructor stub
		this.settings = defaults;
	}
	
	public SettingsRegister() {
		settings = new HashMap<String, Object>();
	}
	
	public Object getObject(String name) {
		return settings.get(name);
	}
	
	public void put(String name, Object value) {
		settings.put(name, value);
	}
	
	public Object getOrDefault(String name, Object def) {
		return settings.getOrDefault(name, def);
	}
	
	public int getInt(String name, int def) {
		return (int) getOrDefault(name, def);
	}
	
	public double getDouble(String name, double def) {
		return (double) getOrDefault(name, def);
	}
	
	public String getString(String name, String def) {
        return (String) getOrDefault(name, def);
    }
	
	public boolean getBoolean(String name, boolean def) {
        return (boolean) getOrDefault(name, def);
    }

	public Object get(String name, Object def) {
        return getOrDefault(name, def);
    }
}
