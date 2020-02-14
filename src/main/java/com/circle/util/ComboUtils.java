package com.circle.util;

import java.util.ArrayList;
import java.util.List;

public class ComboUtils {

	/**
	 * It is used to get array of option with selected value
	 * 
	 * @param names
	 * @param values
	 * @param selectedValue
	 * @return String
	 */
	public static String getOptionArray(List<String> names, List<String> values, String selectedValue) {
		int nameSize;
		int valueSize;
		StringBuilder optionString;
		nameSize = names.size();
		valueSize = values.size();

		if (nameSize != valueSize) {
			throw new IllegalArgumentException("Option names and values must have equal length");
		} else {
			optionString = new StringBuilder("<option value=\"-1\">Select</option>");
			for (int start = 0; start < nameSize; start++) {
				String value = values.get(start);
				String name = names.get(start);

				if (selectedValue.equalsIgnoreCase(value)) {
					optionString.append("<option value=\"" + value + "\" selected=\"selected\">" + name + "</option>");
				} else {
					optionString.append("<option value=\"" + value + "\">" + name + "</option>");
				}
			}
			return optionString.toString();
		}

	}

	public static String getOptionArrayForListBoxForView(List<String> names, List<String> values, String[] selectedValue) {
		int nameSize;
		int valueSize;

		StringBuilder optionString;
		nameSize = names.size();
		valueSize = values.size();

		if (nameSize != valueSize) {
			throw new IllegalArgumentException("Option names and values must have equal length");
		} else {
			optionString = new StringBuilder();
			for (int start = 0; start < nameSize; start++) {
				String value = values.get(start);
				String name = names.get(start);
				boolean flag = false;

				if (selectedValue != null) {

					for (int i = 0; i < selectedValue.length; i++) {

						if (selectedValue[i].equalsIgnoreCase(value)) {
							optionString.append("<option value=\"" + value + "\" selected=\"selected\">" + name + "</option>");
							flag = true;
						}
					}
				}
				if (flag == false) {

					optionString.append("<option value=\"" + value + "\">" + name + "</option>");

				}

			}
			return optionString.toString();
		}

	}

	public static String getOptionArrayForListBox(List<String> names, List<String> values, String selectedValue) {
		int nameSize;
		int valueSize;
		StringBuilder optionString;
		nameSize = names.size();
		valueSize = values.size();

		if (nameSize != valueSize) {
			throw new IllegalArgumentException("Option names and values must have equal length");
		} else {
			optionString = new StringBuilder();
			for (int start = 0; start < nameSize; start++) {
				String value = values.get(start);
				String name = names.get(start);

				if (selectedValue.equalsIgnoreCase(value)) {
					optionString.append("<option value=\"" + value + "\" selected=\"selected\">" + name + "</option>");
				} else {
					optionString.append("<option value=\"" + value + "\">" + name + "</option>");
				}
			}
			return optionString.toString();
		}

	}

	public static String getStatusOption(String selectedValue) {

		List<String> statusName = new ArrayList<String>();
		statusName.add("Active");
		statusName.add("Inactive");

		List<String> statusValues = new ArrayList<String>();
		statusValues.add("1");
		statusValues.add("0");

		return getOptionWithDefaultOption(statusName, statusValues, selectedValue, "Select");
	}

	
	public static String getOptionWithDefaultOption(List<String> names, List<String> values, String selectedValue, String defaultOption) {
		int nameSize;
		int valueSize;
		StringBuilder optionString;
		nameSize = names.size();
		valueSize = values.size();

		if (nameSize != valueSize) {
			throw new IllegalArgumentException("Option names and values must have equal length");
		} else {
			optionString = new StringBuilder("<option value=\"-1\">" + defaultOption + "</option>");
			for (int start = 0; start < nameSize; start++) {
				String value = values.get(start);
				String name = names.get(start);

				if (selectedValue.equalsIgnoreCase(value)) {
					optionString.append("<option value=\"" + value + "\" selected=\"selected\">" + name + "</option>");
				} else {
					optionString.append("<option value=\"" + value + "\">" + name + "</option>");
				}
			}
			return optionString.toString();
		}
	}

	public static String getMultipleOptions(List<String> names, List<String> values, List<String> selectedValue, String defaultOption) {

		int nameSize;
		int valueSize;
		int selectedValueSize;
		boolean flag = true;
		StringBuilder optionString;
		nameSize = names.size();
		valueSize = values.size();
		selectedValueSize = selectedValue.size();
		// logger.debug("selectedValueSize" + nameSize);
		if (nameSize != valueSize) {
			throw new IllegalArgumentException("Option names and values must have equal length");
		} else {
			optionString = new StringBuilder();
			for (int start = 0; start < nameSize; start++) {
				String value = values.get(start);
				String name = names.get(start);

				for (int i = 0; i < selectedValueSize; i++) {
					String Selected = selectedValue.get(i);
					if (Selected.equalsIgnoreCase(value)) {
						optionString.append("<option value=\"" + value + "\" selected=\"selected\">" + name + "</option>");

						flag = false;
					}
				}
				if (flag) {
					optionString.append("<option value=\"" + value + "\">" + name + "</option>");
				}
				flag = true;
			}
			return optionString.toString();
		}
	}

	public static String getMonthOption(String selectedValue) {

		List<String> statusName = new ArrayList<String>();
		statusName.add("01");
		statusName.add("02");
		statusName.add("03");
		statusName.add("04");
		statusName.add("05");
		statusName.add("06");
		statusName.add("07");
		statusName.add("08");
		statusName.add("09");
		statusName.add("10");
		statusName.add("11");
		statusName.add("12");

		List<String> statusValues = new ArrayList<String>();
		statusValues.add("01");
		statusValues.add("02");
		statusValues.add("03");
		statusValues.add("04");
		statusValues.add("05");
		statusValues.add("06");
		statusValues.add("07");
		statusValues.add("08");
		statusValues.add("09");
		statusValues.add("10");
		statusValues.add("11");
		statusValues.add("12");

		return getOptionWithDefaultOption(statusName, statusValues, selectedValue, "Month");
	}

	public static String getYearOption(String selectedValue) {

		List<String> statusName = new ArrayList<String>();
		statusName.add("2014");
		statusName.add("2015");
		statusName.add("2016");
		statusName.add("2017");
		statusName.add("2018");
		statusName.add("2019");
		statusName.add("2020");
		statusName.add("2021");
		statusName.add("2022");
		statusName.add("2023");
		statusName.add("2024");

		List<String> statusValues = new ArrayList<String>();
		statusValues.add("2014");
		statusValues.add("2015");
		statusValues.add("2016");
		statusValues.add("2017");
		statusValues.add("2018");
		statusValues.add("2019");
		statusValues.add("2020");
		statusValues.add("2021");
		statusValues.add("2022");
		statusValues.add("2023");
		statusValues.add("2024");

		return getOptionWithDefaultOption(statusName, statusValues, selectedValue, "Year");
	}

}

